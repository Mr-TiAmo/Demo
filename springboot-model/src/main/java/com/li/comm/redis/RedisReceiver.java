package com.li.comm.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-07-01 14:56
 **/
@Component
public class RedisReceiver {


    @Autowired
    private RedisTemplate redisTemplate;
    /**  receiveMessageA() receiveMessageB() 匹配 redis config 中的 MessageListenerAdapter 消息监听适配器 **/

    public void receiveMessageA(String message) {
        //这里是收到通道的消息之后执行的方法
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("A监听到消息："+message);
    }
    public void receiveMessageB(String message) {
        //这里是收到通道的消息之后执行的方法
        System.out.println("B监听到消息："+message);
    }

    public void listenerAdapterBList1(String message) {
        //这里是收到通道的消息之后执行的方法
        try {
            System.out.println("listenerAdapterBList1");
            String fan = (String)redisTemplate.opsForList().leftPop("fan");
            System.out.println("List 监听到消息："+message);
            System.out.println("fan redis 取到的 userId ：" + fan);
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
