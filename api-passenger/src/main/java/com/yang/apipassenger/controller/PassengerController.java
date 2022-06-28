package com.yang.apipassenger.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @PostMapping("/getPassengerInfo")
    public String getPassengerInfo(){
        return "my name is lcy";
    }
}
