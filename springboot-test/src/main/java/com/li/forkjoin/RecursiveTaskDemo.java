package com.li.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @program: demo
 * @description: 继承RecursiveAction， 可以获取返回值
 * @author: 栗翱
 * @create: 2020-06-19 17:46
 **/
public class RecursiveTaskDemo extends RecursiveTask<Integer> {


    @Override
    protected Integer compute() {
        return null;
    }
}
