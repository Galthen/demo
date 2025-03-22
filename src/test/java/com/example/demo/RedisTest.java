package com.example.demo;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * 测试 Redis
 *
 * @author chenyang
 * @date 2025/03/22
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set("myName", "chenyang");

        System.out.println(ops.get("myName"));
        Assert.equals("chenyang", ops.get("myName"));

        ops.getOperations().delete("myName");
    }

}
