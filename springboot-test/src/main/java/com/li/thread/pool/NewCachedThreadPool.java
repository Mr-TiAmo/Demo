package com.li.thread.pool;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-04-18 13:56
 **/
public class NewCachedThreadPool {

    public static void main(String[] args) throws Exception{
        //缓存线程池
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("缓存线程池").build();
        ThreadPoolExecutor cachedThreadPool =
                new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory);
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 核心线程 是否允许超时关闭
        cachedThreadPool.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 20; i++) {
            cachedThreadPool.execute(getThread(i, countDownLatch));
        }
        cachedThreadPool.shutdown();
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
