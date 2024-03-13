package com.yang.patternDesignServer.ImportTest;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DBConfig {

    private String dbUrl;
}
