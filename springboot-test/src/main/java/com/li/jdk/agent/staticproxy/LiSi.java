package com.li.jdk.agent.staticproxy;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2021-09-17 10:38
 **/
public class LiSi implements Person{
    @Override
    public void print() {
        System.out.println("li si ...");
    }
}
