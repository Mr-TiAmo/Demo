package com.li.thread.pool;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-04-18 13:50
 **/
public class NewFixedThreadPool {
    public static void main(String[] args) throws Exception{
        //固定线程池
        long start = System.currentTimeMillis();
//        int i1 = Runtime.getRuntime().availableProcessors();//获得可用的处理器个数 *2 +1 设置为
        CountDownLatch countDownLatch = new CountDownLatch(20);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("固定线程池").build();
        ThreadPoolExecutor fixedThreadPool =
                new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            fixedThreadPool.execute(getThread(i, countDownLatch));
        }
        fixedThreadPool.shutdown();
        countDownLatch.await();


        System.out.println("运行时间" + (System.currentTimeMillis() - start));
    }
    private static Runnable getThread(final int i, CountDownLatch countDownLatch) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(i + Thread.currentThread().getName());
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
