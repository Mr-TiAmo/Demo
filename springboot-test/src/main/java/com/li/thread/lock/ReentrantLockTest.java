package com.li.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-08-15 13:28
 **/
public class ReentrantLockTest {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception{

        lock.lock();
        new Thread(() -> {
            lock.lock();
            lock.unlock();
        },"test").start();
        lock.unlock();
    }
}
