package com.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.entity.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 栗翱
 * @since 2020-06-17
 */
public interface OrderService extends IService<Order> {
    void testTransactionalEvent() throws Exception;
    void testEvent() throws Exception;
    void testRedis() throws Exception;
}
