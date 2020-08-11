package com.li.thread;

import java.util.concurrent.FutureTask;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-01-07 13:27
 **/
public class CreateThread3 {
    public static void main(String[] args) {

//        CallableThread callableThread = new CallableThread();
//        FutureTask<String> futureTask = new FutureTask<>(callableThread);
//        Thread thread = new Thread(futureTask);
//        thread.start();

//        try {
//            System.out.println(futureTask.get());
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        FutureTask<Integer> FutureTask = new FutureTask<>(() -> {
            int i = 0;
            for (; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " is running " + i);
            }
            return i;
        });

        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量i ： + i");
            if (i == 20) {
                new Thread(FutureTask, "有返回值的线程").start();
            }
        }

        try {
            System.out.println(FutureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
