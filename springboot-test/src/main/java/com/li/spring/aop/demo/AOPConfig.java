package com.li.spring.aop.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2022-07-30 08:52
 **/
@Configuration
@ComponentScan("com.li.spring.aop.demo.bean")
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableAspectJAutoProxy()
public class AOPConfig {

    @Bean
    public LogAspects getLogAspects() {
        return new LogAspects();
    }
}
