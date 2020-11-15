package com.li.thread.volatiles;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-08-07 11:37
 **/
public class VarTest {

    private static volatile boolean flag = true;

    public static void main(String[] args) throws Exception {
        VarTest varTest = new VarTest();
        new Thread(varTest::test1).start();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        flag = false;

    }


    public void test1() {
        System.out.println("start");
        while (flag) {
        }
        System.out.println("end");
    }

}
