package com.sanwei.lequ.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import com.sanwei.lequ.mapper.UserMapper;
import com.sanwei.lequ.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

/**
 * @Author：SatanCY
 * @Date：2024/9/17 16:19
 */
@SpringBootTest
public class InsertUsersTest {


    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    //线程设置
//    new ThreadPoolExecutor()

    /**
     * 批量插入数据
     *
     * 1000条数据测试
     * 普通循环插入： 1614ms
     * 批量插入：100  568ms
     */
    @Test
    public void insertUsersByMapper() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 1000;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("假数据");
            user.setUserAccount("fakeData");
            user.setAvatarUrl("http://sjahvsafc.hd-bkt.clouddn.com/logo.jpg");
            user.setGender(0);
            user.setUserPassword("12345678");
            user.setPhone("12345678");
            user.setEmail("123456@qq.com");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlanetCode("4444");
            user.setTags("Java");
//            userMapper.insert(user);
            userList.add(user);
        }
        userService.saveBatch(userList,100);
        stopWatch.stop();
        System.out.println("插入" + INSERT_NUM + "条数据的时间是：" + stopWatch.getTotalTimeMillis());
    }

    /**
     * 并发批量插入数据
     * 100000条数据
     * 1000耗时：2704ms
     * 2000耗时：2769ms
     * 5000耗时：3873ms
     * 10000耗时：2987ms
     * 20000耗时：3915ms
     */
    @Test
    public void insertUsersByBatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 100000;
        final int batchSize = 1000;
        int j = 0;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (int i = 0; i < INSERT_NUM/batchSize; i++) {
            List<User> userList = new ArrayList<>();
            while (true) {
                j++;
                User user = new User();
                user.setUsername("假数据");
                user.setUserAccount("fakeData");
                user.setAvatarUrl("http://sjahvsafc.hd-bkt.clouddn.com/logo.jpg");
                user.setGender(0);
                user.setUserPassword("12345678");
                user.setPhone("12345678");
                user.setEmail("123456@qq.com");
                user.setUserStatus(0);
                user.setUserRole(0);
                user.setPlanetCode("4444");
                user.setTags("Java");
                userList.add(user);
                if (j % batchSize == 0) {
                    break;
                }
            }
            //异步执行
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//                System.out.println("ThreadName: " + Thread.currentThread().getName());
                userService.saveBatch(userList,batchSize);
            });
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        stopWatch.stop();
        System.out.println("插入" + INSERT_NUM + "条数据的时间是：" + stopWatch.getTotalTimeMillis());
    }
}
