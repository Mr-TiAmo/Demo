package com.li.thread.lock;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-04-18 15:54
 **/
public class ThreadCommunicate {

    public static void main(String[] args) throws Exception {
        final FactoryCopy factory = new FactoryCopy();

        new Thread(() ->{

                try {
                    Thread.sleep(200);
                    System.out.println(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10; j++) {
                    factory.createProduct(j);
                }

        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                    System.out.println(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10; j++) {
                    factory.sellProduct(j);
                }
            }
        }).start();
    }


}
