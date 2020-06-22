package com.li.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @program: springboot-demo
 * @description:  通过 实现 ApplicationContextAware 接口
 *                  底层调用 ApplicationContextAwareProcessor往当前组件中 注入IOC容器
 * @author: 栗翱
 * @create: 2020-06-19 23:00
 **/
@Component
public class Cat implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = this.applicationContext;
    }
}
