package com.li.thread.lock.pattern;

/**
 * @program: demo
 * @description: 用 wait/notify 实现生产者消费者模式
 * @author: li
 * @create: 2022-06-02 17:40
 **/
public class WaitStyle {
    public static void main(String[] args) {

        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(10);

        Producer producer = new Producer(myBlockingQueue);

        Consumer consumer = new Consumer(myBlockingQueue);

        new Thread(producer).start();

        new Thread(consumer).start();

    }
}
