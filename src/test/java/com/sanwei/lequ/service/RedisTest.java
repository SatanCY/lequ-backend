package com.sanwei.lequ.service;
import java.util.Date;

import com.sanwei.lequ.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @Author：SatanCY
 * @Date：2024/9/18 10:56
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //增
        valueOperations.set("sanweiString","sanwei");
        valueOperations.set("sanweiInt",1);
        valueOperations.set("sanweiDouble",2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("sanwei");
        valueOperations.set("sanweiUser",user);

        //查
        Object obj = valueOperations.get("sanweiString");
        Assertions.assertTrue("sanwei".equals((String) obj));
        obj = valueOperations.get("sanweiInt");
        Assertions.assertTrue(1 == (Integer) obj);
        obj = valueOperations.get("sanweiDouble");
        Assertions.assertTrue(2.0 == (Double) obj);
        System.out.println(valueOperations.get("sanweiUser"));

        //删
//        redisTemplate.delete("sanweuString");

    }

}
