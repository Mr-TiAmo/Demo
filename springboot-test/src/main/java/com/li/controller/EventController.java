package com.li.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.li.comm.aop.DataSource;
import com.li.comm.constant.DataSourceType;
import com.li.entity.Order;
import com.li.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot-rabbitmq
 * @description:
 * @author: 栗翱
 * @create: 2020-05-06 15:22
 **/
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/test")
    @ApiOperation(value = "test", notes = "test")
    @DataSource(value = DataSourceType.db1)
    public String test2(@RequestParam("id") Integer id) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getId, id);
        Order order = orderService.getOne(queryWrapper);
        return order.getName();
    }

    @GetMapping("/eventCompara")
    @ApiOperation(value = "事务事件测试对照", notes = "Event")
    public void eventCompara() throws Exception {
        orderService.eventCompara();
    }

    @GetMapping("/transactionalNoticeEvent")
    @ApiOperation(value = "事务事件测试", notes = "Event")
    public void transactionalNoticeEvent() throws Exception {
        orderService.testTransactionalEvent();
    }

    @GetMapping("/noticeEvent")
    @ApiOperation(value = "事件测试", notes = "Event")
    public void noticeEvent() throws Exception {
        orderService.testEvent();
    }


    @GetMapping("/aysnTransactionalEvent")
    @ApiOperation(value = "异步事件测试", notes = "Event")
    public void aysnTransactionalEvent() throws Exception {
        orderService.aysnTransactionalEvent();
    }



//    @GetMapping("/getUserListByUserId")
//    @ApiOperation(value = "根据userId查询neo4j 子节点", notes = "neo4j")
//    public void getUserListByUserId() throws Exception {
//        // 查询所有
//        List<UserInfo> childList = userInfoRepository.findChildList(73999385108L);
//        System.out.println(childList);
//
//        //添加两个用户的上下级关系
//        userInfoRepository.addSuperior(100000L, 100001L);
//
//        UserInfo userInfo = userInfoRepository.updateUserLevelByUserId(100000L, 1);
//        System.out.println(userInfo);
//
//    }

}
