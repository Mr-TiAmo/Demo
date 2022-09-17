package com.li.net.rocketMq.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2022-08-06 14:48
 **/
@Data
public class Order {
    private String orderId;
    private String orderName;
    private Integer orderStatus;
    private BigDecimal orderPrice;

}
