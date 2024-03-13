package com.yang.patternDesignServer.ImportTest;

import com.yang.patternDesignServer.ImportTest.beans.TestBeanA;
import com.yang.patternDesignServer.ImportTest.beans.TestBeanB;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    //使用 BeanNameGenerator自动生成beanName,与手动指定BeanName二选一
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestBeanA.class);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        String beanName = importBeanNameGenerator.generateBeanName(beanDefinition, registry);
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
    //手动指定beanName,与自动生成BeanName二选一
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition testBeanA = new RootBeanDefinition(TestBeanA.class);
        registry.registerBeanDefinition("testBeanA",testBeanA);

        RootBeanDefinition testBeanB = new RootBeanDefinition(TestBeanB.class);
        registry.registerBeanDefinition("testBeanB",testBeanB);
    }
}
