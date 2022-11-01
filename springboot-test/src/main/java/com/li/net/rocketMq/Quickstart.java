package com.li.net.rocketMq;

import com.alibaba.fastjson.JSON;
import com.li.net.rocketMq.bean.Order;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2022-08-06 09:48
 **/
public class Quickstart {

    public static void main(String[] args) throws Exception {
        //实例化生产者 指定生产组名称
        DefaultMQProducer producer = new DefaultMQProducer("order_product_group_name");
        producer.setNamesrvAddr("192.168.179.129:9876");
        //初始化
        producer.start();
        //发送单条消息
        Message message = new Message("TOPIC_TEST", "TOPIC_TEST".getBytes());
        SendResult sendResult = producer.send(message);
        //　输出结果
        System.out.printf("%s%n", sendResult);

        //　发送带 Key 的消息
        message = new Message("TOPIC_TEST", null, "ODS2020072615490001", "{\"id\":1, \"orderNo\":\"ODS2020072615490001\",\"buyerId\":1,\"sellerId\":1  }".getBytes());
        sendResult = producer.send(message);
        //　输出结果
        System.out.printf("%s%n", sendResult);
        //　批量发送
        List<Message> msgs = new ArrayList<>();
        msgs.add(new Message("TOPIC_TEST", null, "ODS2020072615490002", "{\"id\":2, \"orderNo\":\"ODS2020072615490002\",\"buyerId\":1,\"sellerId\":3  }".getBytes()));
        msgs.add(new Message("TOPIC_TEST", null, "ODS2020072615490003", "{\"id\":4, \"orderNo\":\"ODS2020072615490003\",\"buyerId\":2,\"sellerId\":4  }".getBytes()));
        sendResult = producer.send(msgs);
        System.out.printf("%s%n", sendResult);
        //　使用完毕后，关闭消息发送者
        producer.shutdown();
    }

    /**
     * 异步发送消息
     *
     * @throws Exception
     */
    public void syncsSend() throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("order_product_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        try {
            //初始化
            producer.start();
            Message message = new Message("TOPIC_TEST", "hello rocketmq".getBytes());

            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功");
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println("发送失败");
                    e.printStackTrace();
                    //　消息发送失败，可以在这里做补偿，例如将消息存储到数据库，定时重试
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            //消息发送失败，可以在这里做补偿，例如将消息存储到数据库，定时重试。
        } finally {
            Thread.sleep(3000);
            //　使用完毕后，关闭消息发送者
            // 基于 Spring Boot 的应用，在消息发送的时候并不会调用 shutdown 方法，而是等到 spring 容器停止
            producer.shutdown();
        }

    }

    /**
     * 实现顺序消费队列
     * 通过实现 MessageQueueSelector.select 方法
     * 在消息发送的时候如果将同一个订单号的不同的消息发送到同一个队列，这样在消费的时候，我们就能按照顺序进行处理
     * 消息发送时如果使用了 MessageQueueSelector，那消息发送的重试机制将失效，即 RocketMQ 客户端并不会重试，
     * 消息发送的高可用需要由业务方来保证，典型的就是消息发送失败后存在数据库中，然后定时调度，最终将消息发送到 MQ
     * @throws Exception
     */
    public void orderSend() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("order_product_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        try {
            //初始化
            producer.start();
            Order order = new Order();
            order.setOrderId("123");
            order.setOrderName("商品1");
            order.setOrderStatus(100);
            order.setOrderPrice(BigDecimal.valueOf(199));
            //这里为了方便查找消息，在构建消息的时候，使用订单编号为 key，这样可以通过订单编号查询消息。
            Message message = new Message("TOPIC_TEST", null, order.getOrderId(), JSON.toJSONString(order).getBytes());

            producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object key) {
                    //根据订单编号的 hashcode 进行队列选择
                    if (list == null || list.isEmpty()) {
                        return null;
                    }
                    int index = Math.abs(key.hashCode()) % list.size();
                    return list.get(Math.max(index, 0));
                }
            }, order.getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
            //消息发送失败，可以在这里做补偿，例如将消息存储到数据库，定时重试。
        } finally {
            Thread.sleep(3000);
            //　使用完毕后，关闭消息发送者
            // 基于 Spring Boot 的应用，在消息发送的时候并不会调用 shutdown 方法，而是等到 spring 容器停止
            producer.shutdown();
        }
    }

    /**
     * 发送消息时指定 key，设置多个key, 需要通过空格隔开
     * 可以通过 queryMsgByKey 进行查询后，还可以通过 RocketMQ-Console 进行消息查询
     *
     * @throws Exception
     */
    public void sendByKey() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("order_product_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        try {
            //初始化
            producer.start();
            // 设置多个key, 需要通过空格隔开
            Message message = new Message("TOPIC_TEST", null, "key",  "hello rocketmq".getBytes());
//            Message message = new Message("TOPIC_TEST", null, "key1 key2 key3",  "hello rocketmq".getBytes());

            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功");
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println("发送失败");
                    e.printStackTrace();
                    //　消息发送失败，可以在这里做补偿，例如将消息存储到数据库，定时重试
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            //消息发送失败，可以在这里做补偿，例如将消息存储到数据库，定时重试。
        } finally {
            Thread.sleep(3000);
            //　使用完毕后，关闭消息发送者
            // 基于 Spring Boot 的应用，在消息发送的时候并不会调用 shutdown 方法，而是等到 spring 容器停止
            producer.shutdown();
        }

    }

    /**
     * RocketMQ Tag 使用场景
     * RocketMQ 可以为 Topic 设置 Tag（标签），这样消费端可以对 Topic 中的消息基于 Tag 进行过滤，即选择性的对 Topic 中的消息进行处理
     *
     * 场景：
     * 例如一个订单的全生命流程：创建订单、待支付、支付完成、商家审核，商家发货、买家发货，订单每一个状态的变更都会向主题 order_topic 发送消息
     * 但不同下游系统只关注订单流中某几个阶段的消息，并不是需要处理所有消息
     *      活动模块，只要用户下单并成功支付，就发放一张优惠券；
 *          物流模块，只需关注订单审核通过后，就需要创建物流信息，选择供应商
     *
     *  故会创建两个消费组 order_topic_activity_consumer、order_topic_logistics_consumer，但这些消费组又无需处理全部消息，这个时候 Tag 机制就派上用场了。
     *
     * 在消息发送时，例如创建订单时，发送的消息时，设置 Tag 为 c，而支付成功时创建的消息为 w。然后各个场景的消费者按需要订阅 Topic 时指定 Tag
     *
     * @throws Exception
     */
    public void sendByTag() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("order_product_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");

        try {
            producer.start();

            Order order = new Order();
            order.setOrderId("123");
            order.setOrderName("商品1");
            order.setOrderStatus(100);
            order.setOrderPrice(BigDecimal.valueOf(100));

            Message createMsg = new Message("TOPIC_TEST", "c", order.getOrderId(), JSON.toJSONString(order).getBytes());
            System.out.printf("%s%n", producer.send(createMsg));


            order.setOrderStatus(200);
            Message payMsg = new Message("TOPIC_TEST", "w", order.getOrderId(), JSON.toJSONString(order).getBytes());
            System.out.printf("%s%n", producer.send(payMsg));

        } catch (Exception e) {
            e.printStackTrace();
            //消息发送失败，可以在这里做补偿，例如将消息存储到数据库，定时重试。
        } finally {
            Thread.sleep(3000);
            //　使用完毕后，关闭消息发送者
            // 基于 Spring Boot 的应用，在消息发送的时候并不会调用 shutdown 方法，而是等到 spring 容器停止
            producer.shutdown();
        }
    }

    /**
     * 根据自定 top、tag进行消息消费
     * @throws Exception
     */
    public void consumerByTag() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_consumer_group_name");

        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TOPIC_TEST", "c");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // 处理业务新消息...
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.printf("Consumer Started.%n");
    }

    /**
     * 顺序消费
     * 局部顺序消费：
     * 全局顺序消费：所有发到mq的消息都被顺序消费，类似数据库中的binlog，需要严格保证全局操作的顺序性
     *
     * 假设订单A的消息为A1，A2，A3，发送顺序也如此。订单B的消息为B1，B2，B3，A订单消息先发送，B订单消息后发送
     * A1，A2，A3，B1，B2，B3是全局顺序消息，严重降低了系统的并发度
     * A1，B1，A2，A3，B2，B3是局部顺序消息
     */

    /**
     *  局部顺序消费
     *
     *  实现关键点：
     *      消息顺序发送：多线程发送的消息无法保证有序性，因此，针对同一个业务编号(如同一笔订单)的消息需要保证在一个线程内顺序发送，在上一个消息发送成功后，在进行下一个消息的发送。
     *                  对应到mq中，消息发送方法就得使用同步发送，异步发送无法保证顺序性
     *      消息顺序存储：mq的topic下会存在多个queue，要保证消息顺序存储，需要同一个业务编号的消息被发送到同一个queue中，因此，需要使用MessageQueueSelector来选择要发送的queue，
     *                  即对业务编号进行hash，根据队列数量对hash值取余，将消息发送到一个queue中
     *      消息顺序消费：要保证消息顺序消费，同一个queue就只能被一个消费者消费， 因此对broker中的消息队列加锁， 即同一时刻，一个消费队列只能被一个消费者中的一个线程消费
     *
     */
    public void partOrder() {

    }
}
