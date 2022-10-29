package com.yang.apipassenger.aspect;

import com.alibaba.fastjson.JSON;
import com.yang.apipassenger.entity.GrayStrategy;
import com.yang.apipassenger.gray.PassengerContext;
//import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import com.yang.apipassenger.service.IGrayStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class RequestAspect {

    @Autowired
    private IGrayStrategyService grayStrategyService;

    @Pointcut("execution(* com.yang.apipassenger.controller..*Controller*.*(..))")
    public void anyMethod(){}

    /**
     * token校验aop
     * @param joinPoint
     */
    @Before(value = "anyMethod()")
    public void before(JoinPoint joinPoint){
        HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        String authorization = request.getHeader("Authorization");
        //todo 校验token

        //todo 根据token解析出当前用户userId
        String userId = request.getHeader("userId");;
        //todo 根据userId查询用户规则表，确认用户应该调用的服务信息
        GrayStrategy grayStrategy = grayStrategyService.getByUserId(userId);
        if (Objects.isNull(grayStrategy)){
            throw new RuntimeException("user not exit");
        }
        log.info("get grayStrategy by userId,res:{}", JSON.toJSONString(grayStrategy));
        //从数据库中获取版本号信息
        Integer version = grayStrategy.getVersion();
        //将用户信息放到ThreadLocal中
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("version",version);
        PassengerContext.set(map);

        //todo 方案二：也可以使用jmnarloch框架来实现灰度策略
//        if (version.equals("v1")){
//            RibbonFilterContextHolder.getCurrentContext().add("version","v1");
//        }

    }


}
