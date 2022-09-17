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
/**
 * 注解的意思是proxyBeanMethods配置类是用来指定@Bean注解标注的方法是否使用代理
 *     默认是true使用代理，直接从IOC容器之中取得对象
 *     如果设置为false,也就是不使用注解，每次调用@Bean标注的方法获取到的对象和IOC容器中的都不一样，是一个新的对象
  */

@Configuration()
@ComponentScan("com.li.spring.aop.demo.bean")
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAspectJAutoProxy()
public class AOPConfig {

    @Bean
    public LogAspects getLogAspects() {
        return new LogAspects();
    }
}
