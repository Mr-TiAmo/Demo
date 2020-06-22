package com.li.comm.event;

import com.li.comm.utils.OkHttpUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-18 10:36
 **/
@Component
public class NoticeEventListener implements ApplicationListener<NoticeEventObj> {
    /**
     * 实现 ApplicationListener 当 NoticeEventObj 发布事件时触发该方法
     * @Async 可以异步
     * @param noticeEventObj
     */
    @Async
    @Override
    public void onApplicationEvent(NoticeEventObj noticeEventObj) {
        System.out.println("type" + noticeEventObj.getType());
        System.out.println("source" + noticeEventObj.getSource());
        if (Objects.nonNull(noticeEventObj.getType())) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", noticeEventObj.getType());
            String s = OkHttpUtil.get("http://localhost:8002/consumer/evenTest", map);
            System.out.println("调取信息" + s);
        }
    }
}
