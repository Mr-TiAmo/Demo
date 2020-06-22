package com.li.thread;

import java.util.concurrent.Callable;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-01-07 13:28
 **/
public class CallableThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        for(int i = 0; i < 50; i++){
            System.out.println(Thread.currentThread().getName() + " is running " + i );
        }
        return "线程执行完成";
    }
}
