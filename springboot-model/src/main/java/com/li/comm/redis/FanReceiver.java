package com.li.comm.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.CountDownLatch;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-07-13 13:38
 **/
public class FanReceiver {
    private CountDownLatch latch;

    public FanReceiver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    public synchronized void receiveMessageList(String message) {
        //这里是收到通道的消息之后执行的方法
        try {
            String fan = (String)redisTemplate.opsForList().leftPop("fan");
            System.out.println("List 监听到消息："+message);
            System.out.println("fan redis 取到的 userId ：" + fan);
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
