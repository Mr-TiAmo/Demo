package com.li.thread.demo;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-05-27 16:04
 **/
public abstract class Animal extends Thread {
    // 比赛长度
    public int length = 100;

    public abstract void running();

    @Override
    public void run() {
        super.run();
        while (length > 0) {
            running();
        }
    }


    // 2.创建接口对象
    public Calltoback calltoback;

    // 在需要回调数据的地方（两个子类需要），声明一个接口
    public static interface Calltoback {
        public void win();
    }


    public static void main(String[] args) {
        // 实例化乌龟和兔子
        Tortoise tortoise = new Tortoise();
        Rabbit rabbit = new Rabbit();
        // 回调方法的使用，谁先调用calltoback方法，另一个就不跑了
        LetOneStop letOneStop1 = new LetOneStop(tortoise);
        // 让兔子的回调方法里面存在乌龟对象的值，可以把乌龟stop
        rabbit.calltoback = letOneStop1;
        LetOneStop letOneStop2 = new LetOneStop(rabbit);
        // 让乌龟的回调方法里面存在兔子对象的值，可以把兔子stop
        tortoise.calltoback = letOneStop2;
        // 开始跑
        tortoise.start();
        rabbit.start();

    }
}
