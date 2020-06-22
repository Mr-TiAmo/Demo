package com.li.thread.demo;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-05-27 16:06
 **/
public class Rabbit extends Animal {

    public Rabbit() {
        setName("兔子");
    }

    @Override
    public void running() {
        int dis = 5;
        length -= dis;
        System.out.println("兔子跑了" + dis + "米，距离终点还有" + length + "米");
        if (length <= 0) {
            length = 0;
            System.out.println("兔子获得了胜利");
            // 给回调对象赋值，让乌龟不要再跑了
            if (calltoback != null) {
                calltoback.win();
            }

        }
        try {
            // 每20米休息一次,休息时间是1秒
            if ((2000 - length) % 20 == 0) {
                sleep(1000);
            } else {
                //每0.1秒跑5米
                sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
