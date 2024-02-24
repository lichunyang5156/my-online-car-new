package com.yang.patternDesignServer.template;

import org.springframework.stereotype.Service;

@Service
public class MySqlDBHandler extends AbstractDBHandler{
    @Override
    protected void buildSQL() {
        System.out.println("构建mysql SQL语句完成");
    }
}
