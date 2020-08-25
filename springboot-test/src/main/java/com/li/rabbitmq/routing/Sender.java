package com.li.rabbitmq.routing;

import com.alibaba.fastjson.JSONObject;
import com.li.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 还要在 rabbitmq 控制台配置exchange和queue，并绑定
     * 加绑定在控制台的exchange和queues哪一块都可以
     */
    public void send(){
        Random random = new Random();
        Order order = new Order();
        order.setId(1 + random.nextInt(100));
        order.setName("test1");
        order.setMessageId("mq-test");
        String message = JSONObject.toJSONString(order);
        rabbitTemplate.convertAndSend("vinsuan.exchange.test","vinsuan.routingKey.test", message);
//        amqpTemplate.convertAndSend("vinsuan.exchange.test","vinsuan.routingKey.test", message);
        System.out.println("发送数据:" +  message);
    }


}
