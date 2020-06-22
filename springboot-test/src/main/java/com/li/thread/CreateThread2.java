package com.li.thread;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-01-07 13:21
 **/
public class CreateThread2 implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 50; i++){
            System.out.println(Thread.currentThread().getName() + " is running " + i );
        }
    }

    public static void main(String[] args) {
        CreateThread2 thread = new CreateThread2();
        CreateThread2 thread1 = new CreateThread2();
        Thread th1 = new Thread(thread, "线程一");
        Thread th2 = new Thread(thread,"线程二");
        th1.start();
        th2.start();
    }
}
