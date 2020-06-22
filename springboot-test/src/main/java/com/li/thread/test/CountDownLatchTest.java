package com.li.thread.test;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: parking_open_api_12.26
 * @description:  CountDownLatch 通过AQS的共享锁机制实现的，因此它的核心属性只有一个sync
 *                  当指定个线程运行后，才运行CountDownLatch.await()之后的业务
 *                  CountDownLatch如果放到每个线程 中 设置的值减少到 0之后，后面的线程不会运行
 * @author: 栗翱
 * @create: 2020-05-21 14:30
 **/
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception{
        ThreadPoolExecutor fiexdPool = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        CountDownLatch downLatch = new CountDownLatch(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            fiexdPool.execute(getThread(i, downLatch));
        }
        fiexdPool.shutdown();
        downLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("运行时间" + (end - start));

    }
    private static Runnable getThread(final int i, CountDownLatch downLatch) {
        return new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(100);
                downLatch.countDown();
            }
        };
    }


}
