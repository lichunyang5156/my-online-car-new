package com.yang.apipassenger.gray;


import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GrayRibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
        return new GrayRule();
    }

}
