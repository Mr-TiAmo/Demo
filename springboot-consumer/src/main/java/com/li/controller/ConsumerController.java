package com.li.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.li.comm.event.TransactionalNoticeEventObj;
import com.li.entity.Order;
import com.li.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-17 11:10
 **/
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private OrderService orderService;
    @Resource
    private ApplicationEventPublisher publisher;

    @GetMapping("/evenTest")
    @ApiOperation(value = "事务事件测试", notes = "event")
    public String test1(@RequestParam("id") Integer id) {
        if (null == id) {
            return "";
        }
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getId, id);
        Order order = orderService.getOne(queryWrapper);
        return order.getName();
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询所有", notes = "查询所有")
    public void test2() {
        List<Order> order = orderService.list();
        System.out.println(order);
    }

}
