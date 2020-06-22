package com.li.thread.lock;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-04-18 15:12
 **/
public class SingletonException {

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "》》》》》》》" + MySingle.getInstance().hashCode());
                }
            }).start();
        }
    }

}
