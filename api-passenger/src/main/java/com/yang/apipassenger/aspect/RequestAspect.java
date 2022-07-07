package com.yang.apipassenger.aspect;

import com.yang.apipassenger.gray.RibbonParameters;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class RequestAspect {

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
        String userId="";
        //todo 根据userId查询用户规则表，确认用户应该调用的服务信息
        String version ="";
//        version=queryDB
        //将用户信息放到ThreadLocal中
        Map<String,String> map=new HashMap<>();
        map.put("userId",userId);
        map.put("version",version);
        RibbonParameters.set(map);
    }


}
