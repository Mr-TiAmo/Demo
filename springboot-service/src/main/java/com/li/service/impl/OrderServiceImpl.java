package com.li.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.comm.event.NoticeEventObj;
import com.li.comm.event.TransactionalNoticeEventObj;
import com.li.comm.utils.OkHttpUtil;
import com.li.entity.Order;
import com.li.mapper.OrderMapper;
import com.li.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 栗翱
 * @since 2020-06-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private
    ApplicationContext applicationContext;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.READ_COMMITTED)
    public void testTransactionalEvent() throws Exception{
        Order order = new Order();
        order.setName("王五");
        order.setMessageId("111");
        baseMapper.insert(order);
        applicationContext.publishEvent(new TransactionalNoticeEventObj(1111,order.getId()));
        Thread.sleep(10000);
        System.out.println("休眠结束");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.READ_COMMITTED)
    public void eventCompara() throws Exception {
        Order order = new Order();
        order.setName("展昭");
        order.setMessageId("111");
        baseMapper.insert(order);
        if (Objects.nonNull(order.getId())) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", order.getId());
            String s = OkHttpUtil.get("http://localhost:8002/consumer/evenTest", map);
            System.out.println("调取信息" + s);
        }
        Thread.sleep(10000);
        System.out.println("休眠结束");
    }

    @Override
    public void testEvent() throws Exception {
        Order order = new Order();
        order.setName("李四");
        order.setMessageId("111");
        baseMapper.insert(order);
        applicationContext.publishEvent(new NoticeEventObj(1111,order.getId()));
        Thread.sleep(10000);
        System.out.println("休眠结束");
    }

    @Override
    public void testRedis() throws Exception {
        redisTemplate.opsForValue().set("test", "hello world");
        String value = String.valueOf(redisTemplate.opsForValue().get("test"));
        System.out.println(value);
    }

    @Override
    public void aysnTransactionalEvent() throws Exception {
        Order order = new Order();
        order.setName("马六");
        order.setMessageId("111");
        baseMapper.insert(order);
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    super.afterCommit();
                }
            });
        }
    }
}
