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
     * 有业务在实体提交过后进行某些或者一系列业务操作，通过spring的aop机制进行后置业务的处理
     * 事务提交成功后，才会异步执行后续操作；事务失败， 不会执行异步操作
     * 事务提交成功，异步操作异常，已提交事务不会回滚！！！！
     * 异步事件发生在 事务提交生效之后
     * @throws Exception
     */
    void aysnTransactionalEvent() throws Exception;

    /**
     *
     * @throws Exception
     */
    void testRedis() throws Exception;
}
