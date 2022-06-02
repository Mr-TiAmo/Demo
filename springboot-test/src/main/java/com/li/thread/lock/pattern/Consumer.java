package com.li.thread.lock.pattern;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-06-02 17:41
 **/
public class Consumer implements Runnable {

    private MyBlockingQueue storage;

    public Consumer(MyBlockingQueue storage) {

        this.storage = storage;

    }

    @Override

    public void run() {

        for (int i = 0; i < 100; i++) {

            try {

                storage.take();

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }
}
