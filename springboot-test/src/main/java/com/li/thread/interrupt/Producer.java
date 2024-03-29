package com.li.thread.interrupt;

import java.util.concurrent.BlockingQueue;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-06-02 15:09
 **/
public class Producer implements Runnable {
    public volatile boolean canceled = false;

    BlockingQueue storage;

    public Producer(BlockingQueue storage) {

        this.storage = storage;

    }

    @Override

    public void run() {

        int num = 0;

        try {

            while (num <= 100000 && !canceled) {

                if (num % 50 == 0) {

                    storage.put(num);

                    System.out.println(num + "是50的倍数,被放到仓库中了。");

                }

                num++;

            }

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            System.out.println("生产者结束运行");

        }

    }

}
