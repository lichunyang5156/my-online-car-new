package com.yang.apipassenger.service.impl;

import com.yang.apipassenger.annotation.DistributedLock;
import com.yang.apipassenger.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    @DistributedLock(value = "redisLockRegistry",time = 10)
    public String grabOrder(int orderId, int driverId) {

        System.out.println("司机:" + driverId + " 执行抢单逻辑");
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        boolean b = orderService.grab(orderId, driverId);
//        if (b) {
//            System.out.println("司机:" + driverId + " 抢单成功");
//        } else {
//            System.out.println("司机:" + driverId + " 抢单失败");
//        }

        return null;
    }
}
