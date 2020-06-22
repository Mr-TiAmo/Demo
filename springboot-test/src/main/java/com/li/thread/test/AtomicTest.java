package com.li.thread.test;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-05-23 17:05
 **/
public class AtomicTest extends Thread{
    private Integer num = 0;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Integer integer = 1;
        ThreadPoolExecutor fiexdPool = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

        for (int i = 0; i < 10000; i++) {
            fiexdPool.execute(getThread(i, atomicInteger));
        }
        HashMap<Integer, Integer> map = new HashMap(4,0.75F);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        map.put(5,5);
        fiexdPool.shutdown();
        System.out.println(atomicInteger);
    }


    private static Runnable getThread(final int i, AtomicInteger atomicInteger) {
        return () -> {
            atomicInteger.incrementAndGet();
            System.out.println(Thread.currentThread().getName());
        };
    }

    @Override
    public void run() {
        this.num ++;
    }
}
