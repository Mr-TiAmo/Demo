package com.li.comm.event;

import org.springframework.context.ApplicationEvent;

/**
 * @program: demo
 * @description:  事件
 * @author: 栗翱
 * @create: 2020-06-17 10:49
 **/
public class TransactionalNoticeEventObj extends ApplicationEvent {
    private Integer type ;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public TransactionalNoticeEventObj(Object source, Integer type) {
        super(source);
        this.type = type;
    }
}
