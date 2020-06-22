package com.li.producer.controller;

import com.li.entity.Order;
import com.li.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-16 18:21
 **/
@RestController
@RequestMapping("/producer")
@Slf4j
public class ProducerController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/test1")
    public void test1() throws Exception{
        Order order = new Order();
        order.setId(1);
        order.setName("张三");
        order.setMessageId("111");
        orderService.save(order);

        Thread.sleep(5000);
    }
}
