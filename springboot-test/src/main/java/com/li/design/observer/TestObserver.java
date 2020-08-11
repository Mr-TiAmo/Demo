package com.li.design.observer;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-08-01 09:42
 **/
public class TestObserver {
    public static void main(String[] args) {
        ObserverAble observerAble = new ObserverAble();
        //向被观察者 注册 观察者对象
        BinaryObserver binaryObserver = new BinaryObserver(observerAble);
        OctalObserver octalObserver = new OctalObserver(observerAble);
        //向 所有注册的观察者 发送信息
        observerAble.setInfo(10);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //移除 观察者
        observerAble.removeObserver(binaryObserver);
        observerAble.setInfo(15);

    }

}
