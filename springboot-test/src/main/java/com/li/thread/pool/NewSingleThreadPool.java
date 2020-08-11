package com.li.thread.pool;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-04-18 13:53
 **/
public class NewSingleThreadPool {
    public static void main(String[] args) throws Exception{
        //单个线程池
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("单个线程池").build();
        ThreadPoolExecutor singleThreadExecutor =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory);
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++) {
            singleThreadExecutor.execute(getThread(i, countDownLatch));
        }
        singleThreadExecutor.shutdown();
        countDownLatch.await();
        System.out.println("运行时间" + (System.currentTimeMillis() - start));
    }
    private static Runnable getThread(final int i, CountDownLatch countDownLatch) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    System.out.println(i + Thread.currentThread().getName());
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
