package com.li.thread.pool;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-04-18 14:20
 **/
public class ThreadFactoryDemo {
    public static void main(String[] args) throws Exception{
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNamePrefix("线程名称前缀") //设置线程名前缀
                .setPriority(5) //设置线程优先级 默认5
                .setDaemon(true) //设置是否守护线程 如果将 新建的线程都设置成守护线程，当主线程退出后，将会强制销毁线程池
//                .setThreadFactory() //设置用于创建基础线程的线程工厂
//                .setUncaughtExceptionHandler() //设置未捕获异常的处理方式
                .build();
    }
}
