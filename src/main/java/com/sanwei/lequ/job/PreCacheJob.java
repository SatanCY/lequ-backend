package com.sanwei.lequ.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sanwei.lequ.model.domain.User;
import com.sanwei.lequ.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存预热任务
 *
 * @Author：SatanCY
 * @Date：2024/9/18 14:51
 */
@Component
@Slf4j
public class PreCacheJob {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    // 重点用户
    private List<Long> mainUserList = Arrays.asList(1L);

    // 每天执行，预热推荐用户
    @Scheduled(cron = "0 12 1 * * *")   //自己设置时间测试
    public void doCacheRecommendUser() {
        RLock lock = redissonClient.getLock("sanwei:precachejob:docache:lock");
        try {
            //只有一个线程能获取到锁
            if (lock.tryLock(0,-1, TimeUnit.MILLISECONDS)) {
                System.out.println("getLock: " + Thread.currentThread().getId());
                //查数据库
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                Page<User> userPage = userService.page(new Page<>(1,20),queryWrapper);
                String redisKey = String.format("sanwei:user:recommend:%s:%s",mainUserList,1);
                ValueOperations valueOperations = redisTemplate.opsForValue();
                //写缓存,60s过期
                try {
                    valueOperations.set(redisKey,userPage,60000, TimeUnit.MILLISECONDS);
                } catch (Exception e){
                    log.error("redis set key error",e);
                }
            }
        } catch (InterruptedException e) {
            log.error("doCacheRecommendUser error", e);
        } finally {
            // 只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                System.out.println("unlock: " + Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }

}
