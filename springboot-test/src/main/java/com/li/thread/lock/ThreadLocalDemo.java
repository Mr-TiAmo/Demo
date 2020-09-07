package com.li.thread.lock;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-08-11 11:38
 **/
public class ThreadLocalDemo {
    public final int i;

    public ThreadLocalDemo(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public static final ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        protected String initialValue() {
            return "-1";
        }
    };

    public static void main(String[] args) {

//        System.out.println( LocalDateTime.now().getDayOfWeek().getValue());
//
//        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo(1);
//        System.out.println("i : " + threadLocalDemo.i);
//
//        threadLocal.set("main");
//        threadLocal.set("1");
//
//        new Thread(() -> {
//            threadLocal.set("Thread1");
//        }, "Thread1").start();
//
//        new Thread(() -> {
//            threadLocal.set("Thread2");
//            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
//        }, "Thread2").start();
//
//        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());

//        new AnnotationConfigApplicationContext()
//        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    }
}