package com.li.design.proxy;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-07-30 14:05
 **/
public class Black implements Color {

    @Override
    public void print() {
        System.out.println("Black");
    }

    @Override
    public String test() {
        return "test";
    }
}
