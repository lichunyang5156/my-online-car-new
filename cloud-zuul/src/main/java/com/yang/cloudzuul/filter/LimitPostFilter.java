package com.yang.cloudzuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

public class LimitPostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    private static final RateLimiter RATE_LIMITER=RateLimiter.create(2);//2:表示每秒2个，如果是0.1，则表示10秒1个

    @Override
    public Object run() throws ZuulException {
        System.out.println("后面的过滤器");
        return null;
    }
}
