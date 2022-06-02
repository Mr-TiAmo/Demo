package com.li.thread.lock.pattern;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-06-02 17:41
 **/
public class Producer implements Runnable {

    private MyBlockingQueue storage;

    public Producer(MyBlockingQueue storage) {

        this.storage = storage;

    }

    @Override

    public void run() {

        for (int i = 0; i < 100; i++) {

            try {

                storage.put();

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }
}
