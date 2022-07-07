package com.yang.apipassenger;

import com.yang.apipassenger.gray.GrayRibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RibbonClient(name = "sms-service",configuration = GrayRibbonConfiguration.class)//针对sms-service服务使用GrayRibbonConfiguration配置类
public class ApiPassengerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPassengerApplication.class, args);
    }

}
