package com.li.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @program: demo
 * @description:  继承RecursiveAction， 无返回值
 * @author: 栗翱
 * @create: 2020-06-19 16:30
 **/
public class RecursiveActionDemo extends RecursiveAction {
    int[] array;
    int number;
    /**
     * 大于阈值，开启fork/join
     */
    int threshold = 50;
    /**
     * 开始索引
     */
    int start;
    /**
     * 数组长度
     */
    int end;

    public RecursiveActionDemo(int[] array, int number, int start, int end) {
        this.array = array;
        this.number = number;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < threshold) {
            computeDirectly();
        } else {
            int middle = (end + start) / 2;
            RecursiveActionDemo subTask1 = new RecursiveActionDemo(array, number, start, middle);
            RecursiveActionDemo subTask2 = new RecursiveActionDemo(array, number, middle, end);
            invokeAll(subTask1, subTask2);
        }
    }

    /**
     * 假设要对一个很大的数字数组进行变换，转换只需要将数组中的每个元素乘以指定的数字
     */
    protected void computeDirectly() {
        for (int i = start; i < end; i++) {
            array[i] = array[i] * number;
        }
    }


    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }


        RecursiveActionDemo transform = new RecursiveActionDemo(array, 10, 0, 100);
        //方法1  invoke方法会调用 重写的RecursiveAction的compute()
//        transform.invoke();
        //方法2
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(transform);

        for (int i : array) {
            System.out.println(i);
        }
    }
}
