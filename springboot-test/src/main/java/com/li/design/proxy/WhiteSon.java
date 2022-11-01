package com.li.design.proxy;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-08-10 15:13
 **/
public class WhiteSon extends White{
    @Override
    public void print() {
        super.print();
        System.out.println("WhiteSon print");
    }

    @Override
    public String test() {
        super.test();
        System.out.println("WhiteSon test");
        return "WhiteSon test";
    }
}
