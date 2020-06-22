package com.li.thread.pool;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-04-18 14:00
 **/
public class NewScheduledThreadPool {
    public static void main(String[] args) throws Exception{
        //定时线程池
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("定时线程池").build();
//        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(getThread(1, countDownLatch), 5, 1, TimeUnit.SECONDS);
        countDownLatch.await();
        System.out.println("运行时间" + (System.currentTimeMillis() - start));
    }
    private static Runnable getThread(final int i, CountDownLatch countDownLatch) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(i + Thread.currentThread().getName());
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
