package com.li.design.observer;

/**
 * @program: Demo
 * @description:  一个抽象主题，它把所有对观察者对象的引用保存在一个集合中，每个主题都可以有任意数量的观察者。
 *                  抽象主题提供一个接口，可以增加和删除观察者角色。一般用一个抽象类和接口来实现
 * @author: li
 * @create: 2020-08-01 09:18
 **/
public interface InterfaceObserver {

    /**
     * 添加观察者
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 移除观察者
     * @param o
     */
    void removeObserver(Observer o);

    /**
     * 通知所有观察者对象
     */
    void notifyAllObserver();
}
