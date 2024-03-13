package com.yang.patternDesignServer.ImportTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MyImportBeanDefinitionRegistrar.class)
public class MyConfig {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(MyConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String bean:beanDefinitionNames){
            System.out.println(bean);
        }
    }
}
