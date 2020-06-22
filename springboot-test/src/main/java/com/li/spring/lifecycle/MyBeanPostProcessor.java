package com.li.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @program: springboot-demo
 * @description:  后置处理器，在初始化前后进行处理操作
 * @author: 栗翱
 * @create: 2020-06-18 22:20
 **/
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization >>>>>>>>" + bean + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization >>>>>>>>" + bean + beanName);
        return bean;
    }
}
