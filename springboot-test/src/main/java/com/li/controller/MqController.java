package com.li.controller;

import com.li.rabbitmq.ack.AckSender;
import com.li.rabbitmq.delay.DelaySender;
import com.li.rabbitmq.routing.Sender;
import com.li.rabbitmq.topic.ErrorSender;
import com.li.rabbitmq.topic.InfoSender;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2021-04-15 15:37
 **/
@RestController
@RequestMapping("/mq")
public class MqController {
    @Autowired
    private Sender sender;
    @Autowired
    private ErrorSender errorSender;
    @Autowired
    private InfoSender infoSender;
    @Autowired
    private AckSender ackSender;
    @Autowired
    private DelaySender delaySender;

    @GetMapping("/aaa")
    @ApiOperation(value = "mq普通消息测试", notes = "rabbitmq")
    public void test1() throws Exception {
        IntStream.range(0,5).forEach(i -> {
            sender.send();
        });
    }

    @GetMapping("/topic")
    @ApiOperation(value = "mq路由消息测试", notes = "rabbitmq")
    public void topic() throws Exception {
        errorSender.send();
        infoSender.send();
    }

    @GetMapping("/ack")
    @ApiOperation(value = "mq消息ack测试", notes = "rabbitmq")
    public void ack() throws Exception {
        ackSender.send();
    }

    @GetMapping("/delay")
    @ApiOperation(value = "mq延时DXL测试", notes = "rabbitmq")
    public void delay() throws Exception {
        delaySender.send();
    }
}
