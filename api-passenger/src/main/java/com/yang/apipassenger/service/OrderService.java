package com.yang.apipassenger.service;

import com.yang.apipassenger.annotation.DistributedLock;

public interface OrderService {
    @DistributedLock(value = "redisLockRegistry",time = 10)
    String grabOrder(int orderId, int driverId);
}
