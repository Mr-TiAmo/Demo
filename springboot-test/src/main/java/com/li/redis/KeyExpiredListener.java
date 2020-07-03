package com.li.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @program: Demo
 * @description: redis key 过期监听器   继承 KeyExpirationEventMessageListener 重写 onMessage()
 * @author: 栗翱
 * @create: 2020-07-01 13:39
 **/
@Component
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //当key 过期的 时候回触发该方法 处理业务
        System.out.println("当key 过期的 时候回触发该方法 处理业务...");
        System.out.println("key：" + new String(message.getBody()));
        System.out.println(new String(message.getChannel()));
        System.out.println(new String(pattern));
        super.onMessage(message, pattern);
    }
}
