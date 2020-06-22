package com.li.thread.test;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: parking_open_api_12.26
 * @description: CyclicBarrier 循环栅栏，一般放在CyclicBarrier.await()线程中，
*                  当CyclicBarrier的值增加到指定值前，多个线程一起阻塞， 到达指定值之后，不同线程运行CyclicBarrier.await()后的业务
 *                  当CyclicBarrier的值增加到指定值之后，会循环从0再次增加大指定值
 * @author: 栗翱
 * @create: 2020-05-21 14:36
 **/
public class CyclicBarrierTest {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor fiexdPool = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        // 需要同步的线程数量
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        for (int i = 0; i < 20; i++) {
            fiexdPool.execute(getThread(i, cyclicBarrier));
        }

    }
    public static Runnable getThread(final int i, CyclicBarrier cyclicBarrier) {
        return new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                cyclicBarrier.await();
                System.out.println("Thread.currentThread().getName()");
                Thread.sleep(100);
            }
        };
    }
}
