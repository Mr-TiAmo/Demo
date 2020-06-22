package com.li.thread;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-01-07 13:18
 **/
public class CreateThread1 extends Thread{
    @Override
    public void run() {
        for(int i = 0; i < 50; i++){
            System.out.println(Thread.currentThread().getName() + " is running " + i );
        }
    }

    public static void main(String[] args) {
        CreateThread1 thread = new CreateThread1();
        thread.start();
    }
}
