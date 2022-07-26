package com.li.thread.lock;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-09-18 20:16
 **/
public class SynchronizeTest {

    //测试普通的同步方法
    public synchronized void test1() {
        for (int i = 0; i < 5; i++){
            System.out.println("现在在test1内部,休息1秒");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("从test1出来，释放锁");
    }

    public synchronized void test2() {
        for (int i = 0; i < 5; i++){
            System.out.println("现在在test2内部，休息1秒");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("从test2出来，释放锁");
    }

    public static synchronized void test3() {
        for (int i = 0; i < 5; i++){
            System.out.println("现在在test3内部，休息1秒");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("从test3出来，释放锁");
    }

    public static synchronized void test4() {
        for (int i = 0; i < 5; i++){
            System.out.println("现在在test4内部，休息1秒");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("从test4出来，释放锁");
    }

    public void test5() {
        for (int i = 0; i < 5; i++){
            System.out.println("现在在test5内部，休息1秒");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("从test5出来");
    }


    public static void main(String[] args) {
        //同一个对象在两个线程中分别访问该对象的同一个同步方法、两个不同同步方法会产生互斥
        SynchronizeTest demo = new SynchronizeTest();
        SynchronizeTest demo1 = new SynchronizeTest();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test1();
//            }
//        },"thread1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test2();
//            }
//        },"thread2").start();

        //当新建两个不同的对象去调用test1方法时，调用同一个同步方法，不会产生互斥
        //形象的来说就是因为我们每个线程在调用方法的时候都是new 一个对象，那么就会出现两个空间，两把钥匙，
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test1();
//            }
//        },"thread1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo1.test1();
//            }
//        },"thread2").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test1();
//            }
//        },"thread1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo1.test2();
//            }
//        },"thread2").start();


        //synchronized修饰静态方法
        //一个对象直接在两个线程中调用两个不同的同步方法会互斥
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test3();
//            }
//        },"thread1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test4();
//            }
//        },"thread2").start();

        //synchronized修饰静态方法
        //两个个对象直接在两个线程中调用同一个同步方法、不同同步方法 会互斥
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test3();
//            }
//        },"thread1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo1.test3();
//            }
//        },"thread2").start();

        // 同一个对象的 static 的 synchronize 方法 和 普通方法不会互斥
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test3();
//            }
//        }, "thread1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test5();
//            }
//        }, "thread2").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test1();
//            }
//        },"thread1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.test5();
//            }
//        },"thread2").start();
    }
    //  同一个对象访问 synchronized普通方法 synchronized静态方法 不会互斥
    //  不同一个对象访问 synchronized普通方法 synchronized静态方法  不会互斥

    //  同一个对象访问 同一个或不同的 synchronized普通方法        互斥
    //  不同一个对象访问 同一个或不同的 synchronized普通方法         不会互斥

    //  同一个对象访问 同一个或不同的 synchronized静态方法     互斥
    //  不同对象访问 同一个或不同的 synchronized静态方法       互斥

    //  synchronized普通方法 和 普通方法 、synchronized静态方法和 普通方法  不会互斥

}
