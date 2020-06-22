package com.li.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-04-17 16:55
 **/
public class ThreadPoolTest {
    public static int num = 0;

    public static void main(String[] args) throws Exception {

        /** 线程池 详解 https://www.cnblogs.com/superfj/p/7544971.html   https://www.cnblogs.com/trust-freedom/p/6681948.html
         *  任务处理流程原理：如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
         *                  如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，
         *                  则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
         *                  如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
         *                  如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize；
         *                  如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止
         *
         *  构造方法参数：int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
         *              BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler
         *
             *  corePoolSize :线程池的核心池大小，在创建线程池之后，线程池默认没有任何线程
             *  当有任务过来的时候才会去创建创建线程执行任务。换个说法，线程池创建之后，线程池中的线程数为0，
             *   当任务过来就会创建一个线程去执行，直到线程数达到corePoolSize 之后，就会被到达的任务放在队列中。
             *   （注意是到达的任务）。换句更精炼的话：corePoolSize 表示允许线程池中允许同时运行的最大线程数。
             *      如果执行了线程池的prestartAllCoreThreads()方法，线程池会提前创建并启动所有核心线程。
             *
             *  maximumPoolSize :线程池允许的最大线程数，他表示最大能创建多少个线程。maximumPoolSize肯定是大于等于corePoolSize
             *
             *  keepAliveTime :表示线程没有任务时最多保持多久然后停止。默认情况下，只有线程池中线程数大于corePoolSize 时，keepAliveTime 才会起作用。
             *  换句话说，当线程池中的线程数大于corePoolSize，并且一个线程空闲时间达到了keepAliveTime，那么就是shutdown。
             *
             *  Unit :keepAliveTime 的单位
             *
             *  workQueue ：一个阻塞队列，用来存储等待执行的任务，当线程池中的线程数超过它的corePoolSize的时候，线程会进入阻塞队列进行阻塞等待。通过workQueue，线程池实现了阻塞功能
             *              workQueue的类型为BlockingQueue<Runnable>，通常可以取下面三种类型：
             *
             *              1）有界任务队列ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；
             *
             *                  2）无界任务队列LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；
             *
             *              3）直接提交队列synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务
             * threadFactory ：线程工厂，用来创建线程。
             *
             * handler :表示当拒绝处理任务时的策略。
             *          AbortPolicy:丢弃任务并抛出RejectedExecutionException
             *          CallerRunsPolicy：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降。
             *          DiscardOldestPolicy：丢弃队列中最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务。
             *          DiscardPolicy：丢弃任务，不做任何处理。
             *
             *          线程池的关闭：shutdown()：不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
             *                   shutdownNow()：立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务
             *
             *
             *
             *           四种线程池 ：
             *           newFixedThreadPool：固定大小的线程池，可以指定线程池的大小，该线程池corePoolSize和maximumPoolSize相等，阻塞队列使用的是LinkedBlockingQueue，大小为整数最大值。
             *              该线程池中的线程数量始终不变，当有新任务提交时，线程池中有空闲线程则会立即执行，如果没有，则会暂存到阻塞队列。
             *          对于固定大小的线程池，不存在线程数量的变化。同时使用无界的LinkedBlockingQueue来存放执行的任务。
             *          当任务提交十分频繁的时候，LinkedBlockingQueue迅速增大，存在着耗尽系统资源的问题。
             *          而且在线程池空闲时，即线程池中没有可运行任务时，它也不会释放工作线程，还会占用一定的系统资源，需要shutdown。
             *
             *              newSingleThreadExecutor:单个线程线程池，只有一个线程的线程池，阻塞队列使用的是LinkedBlockingQueue,
             *              若有多余的任务提交到线程池中，则会被暂存到阻塞队列，待空闲时再去执行。按照先入先出的顺序执行任务。
             *
             *              newCachedThreadPool:缓存线程池，缓存的线程默认存活60秒。线程的核心池corePoolSize大小为0，核心池最大为Integer.MAX_VALUE,阻塞队列使用的是SynchronousQueue。
             *              是一个直接提交的阻塞队列，他总会迫使线程池增加新的线程去执行新的任务。在没有任务执行时，当线程的空闲时间超过keepAliveTime（60秒），
             *              则工作线程将会终止被回收，当提交新任务时，如果没有空闲线程，则创建新线程执行任务，会导致一定的系统开销。
             *              如果同时又大量任务被提交，而且任务执行的时间不是特别快，那么线程池便会新增出等量的线程池处理任务，这很可能会很快耗尽系统的资源。
             *
             *              newScheduledThreadPool:定时线程池，该线程池可用于周期性地去执行任务，通常用于周期性的同步数据。
             *                   scheduleAtFixedRate:是以固定的频率去执行任务，周期是指每次执行任务成功执行之间的间隔。
             *                   schedultWithFixedDelay:是以固定的延时去执行任务，延时是指上一次执行成功之后和下一次开始执行的之前的时间。
         *
         *
         * CountDownLatch: 这个类使一个线程等待其他线程各自执行完毕后再执行。
         *          是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，
         *          表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了

         */
//        ThreadPoolExecutor executor = new ThreadPoolExecutor();


        // thread 运行时间计算 CountDownLatch
//        long start = System.currentTimeMillis();
//        CountDownLatch countDownLatch = new CountDownLatch(20);   // 需要计算的线程个数
//        for (int i = 0; i < 20 ; i++) {
//            new Thread(() -> {
//                try {
//                    Thread.sleep(500);
//                    num ++;
//                    System.out.println(num);
//                    countDownLatch.countDown();  // 线程个数 -1
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//        countDownLatch.await();  //等待要计算的线程 执行完毕
//        System.out.println("运行时间" + (System.currentTimeMillis() - start));

//        //固定线程池
//        long start = System.currentTimeMillis();
////        int i1 = Runtime.getRuntime().availableProcessors();//获得可用的处理器个数 *2 +1 设置为
//        CountDownLatch countDownLatch = new CountDownLatch(20);
//        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("固定线程池").build();
//        ThreadPoolExecutor fixedThreadPool =
//                new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory);
////        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 20; i++) {
//            fixedThreadPool.execute(getThread(i, countDownLatch));
//        }
//        fixedThreadPool.shutdown();
//        countDownLatch.await();
//
//
//        System.out.println("运行时间" + (System.currentTimeMillis() - start));


//        //单个线程池
//        long start = System.currentTimeMillis();
//        CountDownLatch countDownLatch = new CountDownLatch(20);
//        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("单个线程池").build();
//        ThreadPoolExecutor fixedThreadPool =
//                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory);
////        ExecutorService fixedThreadPool = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 20; i++) {
//            fixedThreadPool.execute(getThread(i, countDownLatch));
//        }
//        fixedThreadPool.shutdown();
//        countDownLatch.await();
//        System.out.println("运行时间" + (System.currentTimeMillis() - start));

//        //缓存线程池
//        long start = System.currentTimeMillis();
//        CountDownLatch countDownLatch = new CountDownLatch(20);
//        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("缓存线程池").build();
//        ThreadPoolExecutor fixedThreadPool =
//                new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory);
////        ExecutorService fixedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 20; i++) {
//            fixedThreadPool.execute(getThread(i, countDownLatch));
//        }
//        fixedThreadPool.shutdown();
//        countDownLatch.await();
//        System.out.println("运行时间" + (System.currentTimeMillis() - start));

        //定时线程池
//        long start = System.currentTimeMillis();
//        CountDownLatch countDownLatch = new CountDownLatch(20);
//        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("定时线程池").build();
////        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10);
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 0, 2, TimeUnit.SECONDS);
//        for (int i = 0; i < 20; i++) {
//            fixedThreadPool.execute(getThread(i, countDownLatch));
//        }
//        fixedThreadPool.shutdown();
//        countDownLatch.await();
//        System.out.println("运行时间" + (System.currentTimeMillis() - start));
    }


    private static Runnable getThread(final int i, CountDownLatch countDownLatch) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(i + Thread.currentThread().getName());
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
