package com.li.jdk.agent.jdk;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2021-09-16 16:01
 **/
public class Cat implements Animal{
    @Override
    public void eat() {
        System.out.println("cat eat...");
    }

    @Override
    public void run() {
        System.out.println("cat run...");
    }
}
