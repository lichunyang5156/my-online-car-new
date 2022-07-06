package com.yang.apipassenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/getPassengerInfo")
    public String getPassengerInfo(){
        return "my name is lcy";
    }

    @GetMapping("/call")
    public String testCall(){
        return restTemplate.getForObject("http://service-sms/test/sms-test",String.class);
    }
}
