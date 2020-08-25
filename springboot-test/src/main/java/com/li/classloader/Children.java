package com.li.classloader;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-08-24 10:52
 **/
public class Children extends Parent{

    public static String name = "tony";
    public static final String id;

    public final String age = "10";

    private String address;

    static {
        id = "1";
        System.out.println("Children 的静态代码块");
    }

    public Children() {
        super();
        System.out.println("Children 的构造方法");
    }

    public Children(String address) {
        super(address);
        this.address = address;
        System.out.println("Children 的有参构造方法");
    }

    public static void main(String[] args) {
        /**
         * Parent 的静态代码块
         * Children 的静态代码块
         * tony
         * 访问static修饰的变量 不会触发构造方法
         */
//        System.out.println(Children.name);
        /**
         * Parent 的静态代码块
         * Children 的静态代码块
         * 1
         */
//        System.out.println(Children.id);

//        Children children = new Children();
        /**
         * Parent 的静态代码块
         * Children 的静态代码块
         * Parent 的构造方法
         * Children 的构造方法
         * 10
         */
//        System.out.println(children.age);
        /**
         * Parent 的静态代码块
         * Children 的静态代码块
         * Parent 的有参构造方法
         * Children 的有参构造方法
         * 杭州
         */
//        Children children = new Children("杭州");
//        System.out.println(children.address);
    }
}
