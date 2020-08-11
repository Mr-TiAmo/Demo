package com.li.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Demo
 * @description:  具体的被观察者
 * @author: li
 * @create: 2020-08-01 09:31
 **/
public class ObserverAble implements InterfaceObserver {

    /**
     * 存储 观察者对象
     */
    private List<Observer> observers = new ArrayList<>();
    protected Integer message;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyAllObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * 向 注册的被观察者发送信息
     * @param s
     */
    public void setInfo(Integer s) {
        this.message = s;
        System.out.println("被观察者发送消息： " + s);
        //消息更新，通知所有观察者
        notifyAllObserver();
    }
}
