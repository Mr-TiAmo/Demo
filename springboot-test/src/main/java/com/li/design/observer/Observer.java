package com.li.design.observer;


/**
 * @program: Demo
 * @description:  抽象 观察者对象
 * @author: li
 * @create: 2020-08-01 09:31
 **/
public abstract class Observer {

    /**
     * 持有 被观察者对象
     */
    protected ObserverAble observerAble;

    /**
     * 当被观察者调用 notifyAllObserver()方法时，观察者的update()方法会被回调
     */
    public abstract void update();
}
