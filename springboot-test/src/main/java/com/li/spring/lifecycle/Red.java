package com.li.spring.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-18 21:42
 **/
public class Red {


    @Autowired
    private ApplicationContext applicationContext;

    public Red() {
        System.out.println("Red bean constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("Red bean init....");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Red bean destroy....");
    }

}
