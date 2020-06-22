package com.li.thread.lock;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-04-18 15:55
 **/
public class FactoryCopy {
    private boolean isCreate = true;

    /**
     * 生产者
     *
     * @param i
     */
    public synchronized void createProduct(int i) {
        while (!isCreate) {
            try {
                this.wait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 20; j++) {
            System.out.println("第" + i + "轮生产，产出" + j + "件");
        }
        isCreate = false;
        this.notify();

    }

    /**
     * 消费者
     *
     * @param i
     */
    public synchronized void sellProduct(int i) {
        while (isCreate) {
            try {
                this.wait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 20; j++) {
            System.out.println("第" + i + "轮销售，销售" + j + "件");
        }
        isCreate = true;
        this.notify();

    }
}
