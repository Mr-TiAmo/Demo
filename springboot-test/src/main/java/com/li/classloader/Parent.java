package com.li.classloader;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-08-24 10:49
 **/
public class Parent {

    public static String name = "tony";

    public final String age = "10";

    private String address;

    static {
        System.out.println("Parent 的静态代码块");
    }


    public Parent() {
        System.out.println("Parent 的构造方法");
    }

    public Parent(String address) {
        this.address = address;
        System.out.println("Parent 的有参构造方法");
    }
}
