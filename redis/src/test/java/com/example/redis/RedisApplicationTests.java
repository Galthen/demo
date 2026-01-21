package com.example.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testDistributedLock() throws InterruptedException {
        RLock lock = redissonClient.getLock("test-lock");

        // 不设置 leaseTime 锁在 Redis 中的过期时间
        boolean isLocked = lock.tryLock(10, TimeUnit.SECONDS);

        if (isLocked) {
            try {
                System.out.println(">>> 成功获取锁，开始执行业务逻辑...");
                Thread.sleep(5000);
            } finally {
                lock.unlock();
                System.out.println(">>> 业务结束，锁已释放");
            }
        }
        Assertions.assertFalse(lock.isLocked(), "锁应该已经被释放");
    }

    @Test
    public void testAtomicLong() {
        RAtomicLong atomicLong = redissonClient.getAtomicLong("test-counter");
        atomicLong.set(100);

        long newValue = atomicLong.incrementAndGet();
        Assertions.assertEquals(101, newValue);
        System.out.println(">>> 当前计数器值: " + newValue);

        atomicLong.delete();
    }

    @Test
    public void testBloomFilter() {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("user-whitelist");
        bloomFilter.tryInit(10000L, 0.03);

        bloomFilter.add("user1");
        bloomFilter.add("user2");

        Assertions.assertTrue(bloomFilter.contains("user1"));
        Assertions.assertFalse(bloomFilter.contains("user3"));

        bloomFilter.delete();
    }

}