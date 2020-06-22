package com.li.thread.lock;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-04-18 15:13
 **/
public class MySingle {

    private static MySingle mySingle = null;
//    private volatile MySingle mySingle = null;
//    volatile 告诉编译器mySingle是随时可能发生变化的，每次使用它的时候必须从mySingle的地址中读取
//    优化做法是，由于编译器发现两次从i读数据的代码之间的代码没有对i进行过操作，它会自动把上次读的数据放在k中。而不是重新从i里面读。
    private  MySingle() {}

    public  static MySingle getInstance() {
//        synchronized (MySingle.class) {
            if (mySingle == null) {
                mySingle = new MySingle();
            }
            return mySingle;
//        }
    }
}
