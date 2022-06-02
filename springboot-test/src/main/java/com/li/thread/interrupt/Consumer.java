package com.li.thread.interrupt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-06-02 15:09
 **/
public class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {

        this.storage = storage;

    }

    public boolean needMoreNums() {

        if (Math.random() > 0.97) {

            return false;

        }

        return true;

    }

    /**
     * 下面来看下 main 函数，首先创建了生产者/消费者共用的仓库 BlockingQueue storage，仓库容量是 8，并且建立生产者并将生产者放入线程后启动线程，
     * 启动后进行 500 毫秒的休眠，休眠时间保障生产者有足够的时间把仓库塞满，而仓库达到容量后就不会再继续往里塞，这时生产者会阻塞，500 毫秒后消费者也被创建出来，
     * 并判断是否需要使用更多的数字，然后每次消费后休眠 100 毫秒，这样的业务逻辑是有可能出现在实际生产中的。
     *
     * 当消费者不再需要数据，就会将 canceled 的标记位设置为 true，理论上此时生产者会跳出 while 循环，并打印输出“生产者运行结束”。
     *
     * 然而结果却不是我们想象的那样，尽管已经把 canceled 设置成 true，但生产者仍然没有停止，这是因为在这种情况下，生产者在执行 storage.put(num) 时发生阻塞，
     * 在它被叫醒之前是没有办法进入下一次循环判断 canceled 的值的，所以在这种情况下用 volatile 是没有办法让生产者停下来的，相反如果用 interrupt 语句来中断，
     * 即使生产者处于阻塞状态，仍然能够感受到中断信号，并做响应处理。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(8);

        Producer producer = new Producer(storage);

        Thread producerThread = new Thread(producer);

        producerThread.start();

        Thread.sleep(500);

        Consumer consumer = new Consumer(storage);

        while (consumer.needMoreNums()) {

            System.out.println(consumer.storage.take() + "被消费了");

            Thread.sleep(100);

        }

        System.out.println("消费者不需要更多数据了。");

        //一旦消费不需要更多数据了，我们应该让生产者也停下来，但是实际情况却停不下来

        producer.canceled = true;

        System.out.println(producer.canceled);

    }

}
