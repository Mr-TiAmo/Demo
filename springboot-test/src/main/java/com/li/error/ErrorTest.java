package com.li.error;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-07-26 09:37
 **/
public class ErrorTest {

    /**
     * 模拟内存不足 OutOfMemoryError: Java heap space
     * jvm 配置
     * -Xms200m
     * -Xmx500m
     * -Xmn300m
     * -XX:+PrintGCDetails
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=C:\Users\Administrator\Desktop\1\test\oom/heapdump.hprof
     * @param args
     */
//    public static void main(String[] args) {
//        ArrayList<Object> arrayList = new ArrayList<>();
//        while (true) {
//            byte[] bytes = new byte[1024];
//            arrayList.add(bytes);
//        }
//    }

    /**
     * 模拟内存不足 OutOfMemoryError: GC overhead limit exceeded
     * java.lang.OutOfMemoryError: GC overhead limit exceeded 错误只在连续多次 GC 都只回收了不到 2% 的极端情况下才会抛出。
     * 假如不抛出 GC overhead limit 错误会发生什么情况呢？那就是 GC 清理的这么点内存很快会再次填满，迫使 GC 再次执行。
     * 这样就形成恶性循环，CPU 使用率一直是 100%，而 GC 却没有任何成果
     * -Xmx12m
     * -XX:+UseParallelGC
     * -XX:+PrintGCDetails
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=C:\Users\Administrator\Desktop\1\test\oom/heapdump.hprof
     *
     * @param args
     */
//    public static void main(String[] args) {
//        Map map = System.getProperties();
//        Random r = new Random();
//        while (true) {
//            map.put(r.nextInt(), "value");
//        }
//    }


    /**
     * 使用jstack 分析死锁
     * @param args
     */
//    public static void main(String[] args) {
//        Lock lock1 = new ReentrantLock();
//        Lock lock2 = new ReentrantLock();
//
//
//        Thread t1 = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    lock1.lock();
//                    System.out.println(Thread.currentThread().getName() + " get the lock1");
//                    Thread.sleep(1000);
//                    lock2.lock();
//                    System.out.println(Thread.currentThread().getName() + " get the lock2");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        Thread t2 = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    lock2.lock();
//                    System.out.println(Thread.currentThread().getName() + " get the lock2");
//                    Thread.sleep(1000);
//                    lock1.lock();
//                    System.out.println(Thread.currentThread().getName() + " get the lock1");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        //设置线程名字，方便分析堆栈信息
//        t1.setName("mythread-jay");
//        t2.setName("mythread-tianluo");
//        t1.start();
//        t2.start();
//    }

    /**
     * 模拟cpu 100%
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Task task1 = new Task();
        Task task2 = new Task();
        pool.execute(task1);
        pool.execute(task2);

    }
    public static Object lock = new Object();
    static class Task implements Runnable{

        public void run() {
            synchronized (lock){
                long sum = 0L;
                while (true){
                    sum += 1;
                }
            }
        }
    }

}
