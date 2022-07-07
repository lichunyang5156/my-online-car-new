package com.yang.servicesms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Value("${server.port}")
    private String port;

    @GetMapping("send")
    public String sendSms(){
        return "success";
    }

    @GetMapping("sms-test")
    public String test(){
        return "当前server端口："+port;
    }
}
