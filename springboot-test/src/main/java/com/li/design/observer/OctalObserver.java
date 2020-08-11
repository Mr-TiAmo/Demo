package com.li.design.observer;

/**
 * @program: Demo
 * @description: 观察者对象
 * @author: li
 * @create: 2020-08-01 09:41
 **/
public class OctalObserver extends Observer {

    /**
     * 绑定 被观察者对象，并向被观察者添加 当前观察者
     * @param o
     */
    public OctalObserver(ObserverAble o) {
        this.observerAble = o;
        this.observerAble.registerObserver(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String 八进制 : "  + Integer.toOctalString( this.observerAble.message));
    }
}
