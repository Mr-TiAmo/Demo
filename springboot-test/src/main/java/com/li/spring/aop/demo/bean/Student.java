package com.li.spring.aop.demo.bean;

import org.springframework.stereotype.Component;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2022-07-30 08:57
 **/
@Component
public class Student{

    public void run() {
        System.out.println("Student run ...");
    }

    public void put() {
        System.out.println("Student put ...");
    }
}
