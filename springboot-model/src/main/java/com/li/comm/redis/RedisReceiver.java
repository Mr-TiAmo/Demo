package com.li.comm.redis;

import org.springframework.stereotype.Component;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-07-01 14:56
 **/
@Component
public class RedisReceiver {
    /**  receiveMessageA() receiveMessageB() 匹配 redis config 中的 MessageListenerAdapter 消息监听适配器 **/

    public void receiveMessageA(String message) {
        //这里是收到通道的消息之后执行的方法
        System.out.println("A监听到消息："+message);
    }
    public void receiveMessageB(String message) {
        //这里是收到通道的消息之后执行的方法
        System.out.println("B监听到消息："+message);
    }
}
