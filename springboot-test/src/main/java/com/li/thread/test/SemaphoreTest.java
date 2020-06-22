package com.li.thread.test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: parking_open_api_12.26
 * @description:    Semaphore 信号量， Semaphore.acquire(count) 当前线程需要指定个信号量才能执行，否则阻塞
 *                                    Semaphore.release(count)  释放当前线程占用的信号量
 *                                    每一个线程每一次acquire(count) ，Semaphore总的信号量就会减少count
 * @author: 栗翱
 * @create: 2020-05-23 15:41
 **/
public class SemaphoreTest extends Thread{
    //信号量
    private volatile  Semaphore se;
    // 申请信号量的大小
    private int count;

    SemaphoreTest(Semaphore se, int count) {
        this.se = se;
        this.count = count;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        Semaphore se = new Semaphore(10);
        poolExecutor.execute(new SemaphoreTest(se, 3));
        poolExecutor.execute(new SemaphoreTest(se, 5));
        poolExecutor.execute(new SemaphoreTest(se, 5));


    }

    @Override
    public void run() {
        try {
            // 从信号量中获取count个许可
            se.acquire(count);
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
            // 释放给定数目的许可，将其返回到信号量。
            se.release(count);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
