package com.example.dailyTestServer.Service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedissonLockService {
    @Autowired
    private RedissonClient redissonClient;

    public void testLock(){
        RLock lock = redissonClient.getLock("lockKey");

        // 尝试获取锁,最多等待100秒,上锁以后10秒自动释放
        try {
            boolean acquired = lock.tryLock(100, 10, TimeUnit.SECONDS);
            // 此代码默认 设置key 超时时间30秒，过10秒，再延时
            lock.lock();
            if (acquired) {
                // 执行临界区代码
                System.out.println("Executing critical operation...");
            } else {
                // 无法获取锁,做对应的处理
                System.out.println("Unable to acquire lock.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
