package com.yang.cloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yang.cloudzuul.dao.CommonGrayRuleDaoCustom;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class GrayFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Resource
    private CommonGrayRuleDaoCustom commonGrayRuleDaoCustom;

    @Override
    public Object run() throws ZuulException {

        //通过zuul上下文获取请求参数
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        int userId = Integer.parseInt(request.getHeader("userId"));
        // 根据用户id 查 规则  查库 v1,meata

        // 金丝雀
        if (userId == 1){
            //框架， 实现通过 metadata 进行灰度路由
            RibbonFilterContextHolder.getCurrentContext().add("version","v1");
            // 普通用户
        }else if (userId == 2){
            RibbonFilterContextHolder.getCurrentContext().add("version","v2");
        }



        return null;
    }
}
