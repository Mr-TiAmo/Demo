package com.li.thread.volatiles;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-09-03 17:18
 **/
public class ThreadLocalTest extends ThreadLocal {

    public static void main(String[] args) throws NoSuchFieldException {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1);
        System.out.println(threadLocal.get());
        Field nextHashCode = Unsafe.class.getDeclaredField("nextHashCode");
        nextHashCode.setAccessible(true);
        System.out.println(nextHashCode);

        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        threadLocal1.set("2");
        System.out.println(threadLocal.get());
        System.out.println(threadLocal1.get());
        System.out.println(threadLocal);
    }
}
