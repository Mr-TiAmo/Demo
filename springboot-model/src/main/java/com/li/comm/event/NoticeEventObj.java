package com.li.comm.event;

import org.springframework.context.ApplicationEvent;

/**
 * @program: demo
 * @description: 需要继承 ApplicationEvent类
 * @author: 栗翱
 * @create: 2020-06-18 10:24
 **/
public class NoticeEventObj extends ApplicationEvent {

    private Integer type ;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public NoticeEventObj(Object source, Integer type) {
        super(source);
        this.type = type;
    }
}
