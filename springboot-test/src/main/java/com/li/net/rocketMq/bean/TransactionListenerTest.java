package com.li.net.rocketMq.bean;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @program: Demo
 * @description: 事务消息监听器
 * @author: 栗翱
 * @create: 2022-08-06 16:07
 **/
public class
TransactionListenerTest implements TransactionListener {
    /**
     * 执行本地事务
     * @param msg Half(prepare) message
     * @param arg Custom business parameter
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        Order order = JSON.parseObject(msg.getBody(), Order.class);
        try {
            if (true) {// 本地事务执行成功
                // 提交事务
                return LocalTransactionState.COMMIT_MESSAGE;
            } else {
                // 返回事务状态未知，rocketmq 默认15次重试回查 后回滚
                return LocalTransactionState.UNKNOW;
            }
        } catch (Exception e) {
            // 回滚事务
            return LocalTransactionState.ROLLBACK_MESSAGE;

        }
    }

    /**
     * 执行消息事务
     * @param msg Check message
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Order order = JSON.parseObject(msg.getBody(), Order.class);
        // 根据 order 信息查询 是否落库
        if (true) {// 本地事务执行成功
            // 提交事务
            return LocalTransactionState.COMMIT_MESSAGE;
        } else {
            // 返回事务状态未知，rocketmq 默认15次重试回查 后回滚
            return LocalTransactionState.UNKNOW;
        }
    }
}
