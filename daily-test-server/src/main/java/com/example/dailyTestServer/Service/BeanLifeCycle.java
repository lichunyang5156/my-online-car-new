package com.example.dailyTestServer.Service;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@Data
@Slf4j
@ToString
@Accessors(chain = true)
public class BeanLifeCycle implements InitializingBean, BeanNameAware, BeanFactoryAware, EnvironmentAware, ApplicationContextAware {
    @Value("${prop:hello}")
    private String prop ;
    private String beanName;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private Environment environment;
    public BeanLifeCycle() {
        log.info("#################BeanLifeCycle 实例化");
    }
    public void init() {
        log.info("#################BeanLifeCycle 调用init-mthod 初始化");
    }
    public void destroy() {
        log.info("#################BeanLifeCycle 销毁");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("#################BeanLifeCycle 调用afterPropertiesSet方法, 查看属性值prop：[{}],已经被赋值", prop);
        log.info("#################BeanLifeCycle 调用afterPropertiesSet 初始化");
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("@@@@@@@@@@@@@@@@@@ beanFactory: [{}]", beanFactory);
    }
    @Override
    public void setBeanName(String beanName) {
        log.info("@@@@@@@@@@@@@@@@@@ beanName: [{}]", beanName);
        this.beanName = beanName;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("@@@@@@@@@@@@@@@@@@ applicationContext: [{}]", applicationContext);
        this.applicationContext = applicationContext;
    }
    @Override
    public void setEnvironment(Environment environment) {
        log.info("@@@@@@@@@@@@@@@@@@ environment: [{}]", environment);
        this.environment = environment;
    }
}