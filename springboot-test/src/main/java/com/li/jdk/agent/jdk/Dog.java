package com.li.jdk.agent.jdk;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2021-09-16 16:01
 **/
public class Dog implements Animal, Run{
    @Override
    public void eat() {
        System.out.println("dog eat...");
    }

    @Override
    public void run() {
        System.out.println("dog run...");
    }

    @Override
    public void print() {
        System.out.println("dog print...");
    }
}
