package com.li.jdk.overReadAndoverLoad.bean;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-08-11 17:43
 **/
public class Cat extends Animal{
    @Override
    public void run() {
        System.out.println("Cat run");
    }
}
