package com.li.spring.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-18 00:05
 **/
@Configuration
public class FactoryBeanConfig {

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
        //FactoryBean 获取的是调用getObject()方法 创建的bean对象
        //常用于spring整合其他框架
        Object bean = context.getBean("colorFactoryBean");
        System.out.println(bean.getClass());
        // 通过在ID前面加 & 获取FactoryBean对象
        Object colorFactoryBean = context.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());
    }
}
