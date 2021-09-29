package com.li.jdk.agent.staticproxy;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2021-09-17 10:37
 **/
public class ZhangSan implements Person{
    @Override
    public void print() {
        System.out.println("zhang san ...");
    }
}
