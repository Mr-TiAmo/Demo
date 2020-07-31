package com.li.comm.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.comm.redis.FanReceiver;
import com.li.comm.redis.RedisReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;


/**
 * @author Administrator
 */
@Slf4j
@Configuration
public class RedisConfiguration {
    /** redis  序列化 **/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //首先解决key的序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);

        //解决value的序列化方式
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper =  new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /** redis 监听设置    在 redis安装包下 redis.conf 配置文件里面找到 # notify-keyspace-events Ex 去掉注释，然后重启redis
     * 以 keyspace 为前缀的频道被称为键空间通知(key-space nnotification),而以 keyevent 为前缀的频道则被称为键事件通知(key-event notification).
     * **/
    @Bean
    public ChannelTopic expiredTopic() {
        // 选择0号数据库
        return new ChannelTopic("__keyevent@0__:expired");
//        return new ChannelTopic("__keyspace@0__:sampleKey  expired");
    }

//    @Bean
//    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
//        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
//        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
//        return redisMessageListenerContainer;
//    }


    /** redis  消息发布订阅 **/
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapterA,
                                            MessageListenerAdapter listenerAdapterB,
                                            MessageListenerAdapter fanReceiverAdapter,
                                            MessageListenerAdapter listenerAdapterBList1) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        //可以添加多个 messageListener
        container.addMessageListener(listenerAdapterA, new PatternTopic("index"));
        container.addMessageListener(fanReceiverAdapter, new PatternTopic("fans"));
        container.addMessageListener(listenerAdapterB, new PatternTopic("home"));
        container.addMessageListener(listenerAdapterBList1, new PatternTopic("index"));

        return container;
    }


    @Bean
    MessageListenerAdapter listenerAdapterA(RedisReceiver redisReceiver) {
        System.out.println("消息适配器A进来了--- 匹配redisReceiver中的receiveMessageA() ");
        return new MessageListenerAdapter(redisReceiver, "receiveMessageA");
    }

    @Bean
    MessageListenerAdapter listenerAdapterB(RedisReceiver redisReceiver) {
        System.out.println("消息适配器B进来了--- 匹配redisReceiver中的receiveMessageB() ");
        return new MessageListenerAdapter(redisReceiver, "receiveMessageB");
    }

    @Bean
    MessageListenerAdapter listenerAdapterBList1(RedisReceiver redisReceiver) {
        System.out.println("消息适配器B进来了--- 匹配redisReceiver中的listenerAdapterBList1() ");
        return new MessageListenerAdapter(redisReceiver, "listenerAdapterBList1");
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    @Bean
    FanReceiver fanReceiver(CountDownLatch latch) {
        return new FanReceiver(latch);
    }

    @Bean
    MessageListenerAdapter fanReceiverAdapter(FanReceiver fanReceiver) {
        return new MessageListenerAdapter(fanReceiver, "receiveMessageList");
    }
}
