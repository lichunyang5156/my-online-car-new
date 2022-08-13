package com.yang.servicesms.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LimitFilter implements Filter {

    private static final RateLimiter RATE_LIMITER=RateLimiter.create(1);//2:表示每秒2个，如果是0.1，则表示10秒1个

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //限流的业务逻辑
//        if (RATE_LIMITER.acquire())//阻塞
        if (RATE_LIMITER.tryAcquire()){//非阻塞

            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            servletResponse.setCharacterEncoding("utf-8");
            servletResponse.setContentType("text/html; charset=utf-8");
            PrintWriter printWriter=null;
            printWriter=servletResponse.getWriter();
            printWriter.write("限流了");
            printWriter.close();
        }
    }
}
