package com.li.spring.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-18 22:07
 **/
@Component
public class Dog implements InitializingBean, DisposableBean {

    public Dog() {
        System.out.println("Red constructor");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Dog bean init....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Dog bean destroy....");
    }
}
