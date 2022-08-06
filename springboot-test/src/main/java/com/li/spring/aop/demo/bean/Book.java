package com.li.spring.aop.demo.bean;

import org.springframework.stereotype.Component;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2022-07-30 08:55
 **/
@Component
public class Book implements Print{
    @Override
    public void run() {
        System.out.println("Book run ...");
    }

    @Override
    public void put() {
        System.out.println("Book put ...");
    }

    @Override
    public Integer add(Integer a) {
//        a = a/0;
        return ++a;
    }
}
