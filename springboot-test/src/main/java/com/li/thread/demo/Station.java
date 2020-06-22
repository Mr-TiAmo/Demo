package com.li.thread.demo;

/**
 * @program: parking_open_api_12.26
 * @description: 站台售票，其他站台要等这个站台先售完
 * @author: 栗翱
 * @create: 2020-05-27 13:33
 **/
public class Station extends Thread {

    //通过构造方法给线程名字赋值
    public Station(String name) {
        // 给线程名字赋值
        super(name);
    }

    // 为了保持票数的一致，票数要静态
    static int tick = 10;
    // 创建一个静态钥匙
    static Object lock = "aa";//值是任意的, 必须是对象
    // 重写run方法，实现买票操作


    @Override
    public void run() {
        while (tick > 0) {
            synchronized (lock) {
                if (tick > 0) {
                    System.out.println(getName() + "卖出了第" + tick + "张票");
                    tick--;
                } else {
                    System.out.println("票买完了！！！");
                }
            }
        }
        try {
            sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //实例化站台对象，并为每一个站台取名字
        Station station1 = new Station("窗口1");
        Station station2 = new Station("窗口2");
        Station station3 = new Station("窗口3");
        // 让每一个站台对象各自开始工作
        station1.start();
        station2.start();
        station3.start();
    }
}
