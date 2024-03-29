package com.yang.cloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RibbonFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        //获取请求过来的url
        RequestContext currentContext=RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        //获取请求url
        String remoteAddr = request.getRequestURI();

        //做映射
        if (remoteAddr.contains("/test31")){
            //将老地址映射到新地址
            currentContext.set(FilterConstants.SERVICE_ID_KEY,"service-sms");
            currentContext.set(FilterConstants.REQUEST_URI_KEY,"test/test3");
        }

        return null;
    }
}
