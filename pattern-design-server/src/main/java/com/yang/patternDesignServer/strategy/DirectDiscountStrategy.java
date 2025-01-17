package com.yang.patternDesignServer.strategy;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class DirectDiscountStrategy implements DiscountStrategy{
    @Override
    public void doDiscount() {
        System.out.println("使用直接打折的优惠策略");
    }
}
