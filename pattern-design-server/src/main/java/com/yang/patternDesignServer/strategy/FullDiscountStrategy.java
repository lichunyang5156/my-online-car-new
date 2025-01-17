package com.yang.patternDesignServer.strategy;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class FullDiscountStrategy implements DiscountStrategy{
    @Override
    public void doDiscount() {
        System.out.println("采用满200减50的优惠策略");
    }
}
