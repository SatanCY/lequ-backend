package com.sanwei.lequ.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：SatanCY
 * @Date：2024/9/20 14:55
 */
@SpringBootTest
public class RedissonTest {

    @Resource
    private RedissonClient redissonClient;

    @Test
    void test() {
        List<String> list = new ArrayList<>();
        list.add("sanwei");
        System.out.println("list:" + list.get(0));

//        list.remove(0);

        RList<Object> rList = redissonClient.getList("test-list");
        rList.add("sanwei");
        System.out.println("rList:" + rList.get(0));
//        rList.remove(0);

        // map
        Map<String, Integer> map = new HashMap<>();
        map.put("sanwei", 10);
        System.out.println("map:" + map.get("sanwei"));

        RMap<String, Integer> rMap = redissonClient.getMap("test-map");
        map.put("sanwei",10);
        System.out.println("rMap:" + map.get("sanwei"));
    }

}
