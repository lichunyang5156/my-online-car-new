package com.yang.patternDesignServer.strategy;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(100)
public class DiscountStrategyFactory implements ApplicationContextAware {

    private static Map<String,DiscountStrategy> beansOfType=new HashMap<>();

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext= applicationContext;
    }

    /**
     * 初始化BeanMap
     */
    @PostConstruct
    public void initStrategy(){
        beansOfType = applicationContext.getBeansOfType(DiscountStrategy.class);
    }

    public DiscountStrategy getDiscountStrategy(String strategyName){
        return beansOfType.get(strategyName);
    }
}
