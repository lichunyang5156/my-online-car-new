package com.yang.cloudzuul.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class SentinelService {

    /**
     *
     * @return
     */
    @SentinelResource(value = "SentinelService.success",blockHandler = "fail")//value一般是服务名+方法名
    public String success(){
        System.out.println("success 正常请求");
        return "success";
    }

    public String fail(BlockException e){
        System.out.println("阻塞");
        return "fail";
    }
}
