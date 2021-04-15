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
    /**
     * event 对照组
     * @throws Exception
     */
    void eventCompara() throws Exception;

    /**
     * 注解@TransactionalEventListener
     * @throws Exception
     */
    void testTransactionalEvent() throws Exception;


    /**
     * 事件发布监听
     * @throws Exception
     */
    void testEvent() throws Exception;

    /**
     * TransactionSynchronizationManager 使用
     * @throws Exception
     */
    void aysnTransactionalEvent() throws Exception;

    /**
     *
     * @throws Exception
     */
    void testRedis() throws Exception;
}
