package com.yang.patternDesignServer.strategy;

import org.springframework.stereotype.Component;

@Component
public class DiscountStrategyContext {

    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public void handleDiscount(){
        discountStrategy.doDiscount();
    }
}
