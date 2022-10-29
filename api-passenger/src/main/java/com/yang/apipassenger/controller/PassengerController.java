package com.yang.apipassenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private RestTemplate restTemplate;  //restTemplate

    @PostMapping("/getPassengerInfo")
    public String getPassengerInfo(){
        return "my name is lcy";
    }

    @GetMapping("/call")
    public String testCall(){
        try {
            return restTemplate.getForObject("http://service-sms/sms/sms-test",String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
