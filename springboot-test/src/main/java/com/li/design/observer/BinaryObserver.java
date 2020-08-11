package com.li.design.observer;

/**
 * @program: Demo
 * @description: 观察者对象
 * @author: li
 * @create: 2020-08-01 09:40
 **/
public class BinaryObserver extends Observer {

    /**
     * 绑定 被观察者对象，并向被观察者添加 当前观察者
     * @param o
     */
    public BinaryObserver(ObserverAble o) {
        this.observerAble = o;
        this.observerAble.registerObserver(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String 二进制 : "  + Integer.toBinaryString( this.observerAble.message));
    }
}
