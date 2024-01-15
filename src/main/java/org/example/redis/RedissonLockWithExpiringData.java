package org.example.redis;

import org.redisson.Redisson;
import org.redisson.api.MapOptions;
import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class RedissonLockWithExpiringData {
    public static void main(String[] args) throws InterruptedException {
        // 创建 Redisson 客户端
        RedissonClient redisson = Redisson.create();

        // 获取锁
        RLock lock = redisson.getLock("mylock");
        lock.tryLock(100,TimeUnit.MILLISECONDS);
        lock.lock();
        lock.isHeldByCurrentThread();
        Class cast = Class.class.getClass().cast("");

        // 设置附加数据并指定过期时间
        RMapCache<String, String> dataMap = redisson.getMapCache("mydata");

        dataMap.put("key1", "value1", lock.remainTimeToLive(), TimeUnit.MILLISECONDS);


        TimeUnit.MINUTES.sleep(1000);
        new Thread(() -> {
            RMapCache<String, String> dataMap2 = redisson.getMapCache("mydata");
            String key1 = dataMap2.get("key1");
            System.out.println(key1);

        }).start();

        // 释放锁
        lock.unlock();
        // 关闭 Redisson 客户端
        redisson.shutdown();
    }
}


