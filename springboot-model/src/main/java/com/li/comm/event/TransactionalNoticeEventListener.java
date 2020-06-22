package com.li.comm.event;

import com.li.comm.utils.OkHttpUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: demo
 * @description: 事件消费者
 * @author: 栗翱
 * @create: 2020-06-17 10:51
 **/
@Component
public class TransactionalNoticeEventListener {

    /**
     * 监听事件，默认被监听方需要加@Transactional 才能触发监听
     * 通过设置@TransactionalEventListener(fallbackExecution = true) 无事务的情况下也可以监听
     * 不能够异步事件，因为 需要被被监听的调用者提交事务才能触发
     * @param eventObj
     */
    @TransactionalEventListener()
    public void onApplicationEvent(TransactionalNoticeEventObj eventObj) {
        System.out.println("type" + eventObj.getType());
        System.out.println("source" + eventObj.getSource());
        if (Objects.nonNull(eventObj.getType())) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", eventObj.getType());
            String s = OkHttpUtil.get("http://localhost:8002/consumer/evenTest", map);
            System.out.println("调取信息" + s);
        }

    }

}
