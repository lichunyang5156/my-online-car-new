package com.yang.cloudzuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

public class LimitFilter extends ZuulFilter {
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
        return true;
    }

    private static final RateLimiter RATE_LIMITER=RateLimiter.create(2);//2:表示每秒2个，如果是0.1，则表示10秒1个

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();

        if (RATE_LIMITER.tryAcquire()){

            return null;
        }else {
            currentContext.set("limit",false);//需要手动根据此处配置，设置后面自定义的Filter不执行
            currentContext.setSendZuulResponse(false);//设置false，表示不执行RibbonRouteFilter
            currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
            return null;
        }
    }
}
