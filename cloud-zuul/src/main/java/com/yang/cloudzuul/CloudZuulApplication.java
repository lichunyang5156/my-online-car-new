package com.yang.cloudzuul;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
@RestController
public class CloudZuulApplication {

    public static void main(String[] args) {
        init();
        SpringApplication.run(CloudZuulApplication.class, args);
    }

    @GetMapping("/test")
    public String test(){
        return "LCY";
    }


    //生成令牌（规则
    private static void init(){
        //所有限流规则的合集
        List<FlowRule> rules=new ArrayList<>();

        FlowRule rule=new FlowRule();
        rule.setRefResource("HelloWorld");//限流的资源
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);//限流的规则，根据QPS限流
        rule.setCount(2);//限流的值
        rules.add(rule);

        FlowRule rule2=new FlowRule();
        rule2.setRefResource("SentinelService.success");//限流的资源
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);//限流的规则，根据QPS限流
        rule2.setCount(2);//限流的值
        rules.add(rule2);

        FlowRuleManager.loadRules(rules);

    }

    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }

}
