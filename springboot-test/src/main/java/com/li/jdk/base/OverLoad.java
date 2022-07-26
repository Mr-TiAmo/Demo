package com.li.jdk.base;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-02-16 17:51
 **/
public class OverLoad {


    public void sayHello(Human human) {
        System.out.println("Human hello");
    }

    public void sayHello(Man man) {
        System.out.println("Man hello");
    }

    public void sayHello(WoMan woMan) {
        System.out.println("WoMan hello");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woMan = new WoMan();
        OverLoad overLoad = new OverLoad();
        overLoad.sayHello(man);
        overLoad.sayHello(woMan);
    }
}
