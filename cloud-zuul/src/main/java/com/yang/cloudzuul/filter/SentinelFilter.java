package com.yang.cloudzuul.filter;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class SentinelFilter extends ZuulFilter {
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

    @Override
    public Object run() throws ZuulException {
        //限流的业务逻辑（使用令牌）
        Entry entry=null;
        try {
            entry= SphU.entry("HelloWorld");//要保护的资源
            //业务逻辑
            System.out.println("正常请求");
        } catch (BlockException e) {
            e.printStackTrace();
            System.out.println("阻塞住了");
            //自己测试去
            RequestContext.getCurrentContext().setSendZuulResponse(false);
        }finally {
            if (entry!=null){
                entry.exit();
            }

        }
        return null;
    }
}
