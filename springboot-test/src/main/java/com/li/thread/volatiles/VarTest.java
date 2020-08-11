package com.li.thread.volatiles;

import java.util.concurrent.locks.Lock;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-08-07 11:37
 **/
public class VarTest {

    private static boolean flag = false;

    public static void main(String[] args) {
        VarTest varTest = new VarTest();
        varTest.test1();
        varTest.test();
    }


    public void test1() {
        Thread thread = new Thread(() -> {
            while (!flag) {
                System.out.println("false");
            }
        });
        thread.start();
    }

    public void test() {
        Thread thread = new Thread(() -> {
            System.out.println("true");
            flag = true;
        });
        thread.start();
    }
}
