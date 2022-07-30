package com.li.thread.lock.pattern;

import java.util.LinkedList;

/**
 * @program: demo
 * @description: 用 wait/notify 实现生产者消费者模式
 * @author: li
 * @create: 2022-06-02 17:39
 **/
public class MyBlockingQueue {
    private int maxSize;

    private LinkedList<Object> storage;

    public MyBlockingQueue(int size) {

        this.maxSize = size;

        storage = new LinkedList<>();

    }

    public synchronized void put() throws InterruptedException {

        while (storage.size() == maxSize) {

            wait();

        }

        storage.add(new Object());

        notifyAll();

    }

    public synchronized void take() throws InterruptedException {

        while (storage.size() == 0) {

            wait();

        }

        System.out.println(storage.remove());

        notifyAll();

    }

}
