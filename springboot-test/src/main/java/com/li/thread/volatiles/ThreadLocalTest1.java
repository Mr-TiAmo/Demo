package com.li.thread.volatiles;

import com.li.spring.bean.Car;
import com.li.spring.bean.Color;
import com.li.spring.bean.Person;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-06-07 16:21
 **/
public class ThreadLocalTest1 {
    private static ThreadLocal<Car> threadLocal1 = new ThreadLocal<>();
    private static ThreadLocal<Person> threadLocal2 = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            threadLocal1.set(new Car("张三"));
            threadLocal2.set(new Person("红色"));
            System.out.println("-----Thread1----");
            System.out.println("-----Thread1----" + threadLocal1.get());
            System.out.println("-----Thread1----" + threadLocal2.get());
        }).start();
        new Thread(() -> {
            threadLocal1.set(new Car("李四"));
            threadLocal2.set(new Person("蓝色"));
            System.out.println("-----Thread2----");
            System.out.println("-----Thread2----" + threadLocal1.get());
            System.out.println("-----Thread2----" + threadLocal2.get());
        }).start();

        Thread.sleep(1000);
        System.out.println("-----main----");
        System.out.println("-----main----" + threadLocal1.get());
        System.out.println("-----main----" + threadLocal2.get());
    }
}
