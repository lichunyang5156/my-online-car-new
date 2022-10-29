package com.yang.apipassenger.controller;

import com.alibaba.fastjson.JSON;
import com.yang.apipassenger.entity.GrayStrategy;
import com.yang.apipassenger.service.IGrayStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grayStrategy")
public class GrayStrategyController {


    @Autowired
    private IGrayStrategyService grayStrategyService;

    @GetMapping("get")
    public String getStrategy(){
        GrayStrategy one=grayStrategyService.getByUserId("1");
        return JSON.toJSONString(one);
    }
}
