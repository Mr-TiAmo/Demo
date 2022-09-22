package com.li.redis.redislock;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.redisson.RedissonLock;
import org.redisson.RedissonLockEntry;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.client.RedisException;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.command.CommandAsyncExecutor;
import org.redisson.misc.RPromise;
import org.redisson.misc.RedissonPromise;

import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description: Redission 分布式锁实现原理  https://zhuanlan.zhihu.com/p/540654312
 *     <dependency>
 *             <groupId>org.redisson</groupId>
 *             <artifactId>redisson</artifactId>
 *             <version>3.14.0</version>
 *         </dependency>
 * @author: li
 * @create: 2022-09-21 15:36
 **/
public class RedissonTest {



    /**
     *

     commandExecutor: 与redis节点通信并发送指令的真正实现，CommandExecutor 实现是通过 eval 命令来执行 Lua 脚本
     name：锁的全局名称


    public RLock getLock(String name) {
        return new RedissonLock(connectionManager.getCommandExecutor(), name);
    }

     */


    /**
     *  初始化 RedissonLock
     *  commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout()  默认为 3*10 秒， watch dog 看门狗，
     *  用于 当前持有锁的线程，在最大持有时间之后，业务没有执行完，进行续时间
     *
    public RedissonLock(CommandAsyncExecutor commandExecutor, String name) {
        super(commandExecutor, name);
        this.commandExecutor = commandExecutor;
        this.id = commandExecutor.getConnectionManager().getId();
        this.internalLockLeaseTime = commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout();
        this.entryName = id + ":" + name;
        this.pubSub = commandExecutor.getConnectionManager().getSubscribeService().getLockPubSub();
    }

     */


    /**
     *
    private Long tryAcquire(long waitTime, long leaseTime, TimeUnit unit, long threadId) {
        return get(tryAcquireAsync(waitTime, leaseTime, unit, threadId));
    }

     */


    /**
     *
     *
     *
    private <T> RFuture<Long> tryAcquireAsync(long waitTime, long leaseTime, TimeUnit unit, long threadId) {
        // 默认走这个分支，获取到锁的持有时间 != -1
        if (leaseTime != -1) {
            return tryLockInnerAsync(waitTime, leaseTime, unit, threadId, RedisCommands.EVAL_LONG);
        }

        // 当前持有 锁的线程 ，设置的持有时间 到期， 进行续期
        RFuture<Long> ttlRemainingFuture = tryLockInnerAsync(waitTime, internalLockLeaseTime,
                TimeUnit.MILLISECONDS, threadId, RedisCommands.EVAL_LONG);
        ttlRemainingFuture.onComplete((ttlRemaining, e) -> {
            if (e != null) {
                return;
            }

            // lock acquired
            if (ttlRemaining == null) {
                // 具体执行逻辑
                scheduleExpirationRenewal(threadId);
            }
        });
        return ttlRemainingFuture;
    }
     */



    /**
     *
     private void scheduleExpirationRenewal(long threadId) {
        RedissonLock.ExpirationEntry entry = new RedissonLock.ExpirationEntry();
        RedissonLock.ExpirationEntry oldEntry = EXPIRATION_RENEWAL_MAP.putIfAbsent(getEntryName(), entry);
        if (oldEntry != null) {
            oldEntry.addThreadId(threadId);
        } else {
            entry.addThreadId(threadId);
            // 主要逻辑
            renewExpiration();
        }
    }

     */

    /**
     * watch dog 续期逻辑
     * 只要客户端一旦加锁成功，就会启动一个 watch dog 看门狗，他是一个后台线程，会每隔 10 秒检查一下，如果客户端还持有锁 key，那么就会不断的延长锁 key 的过期时间
     *
     * 默认情况下，加锁的时间是 30 秒,.如果加锁的业务没有执行完,就会进行一次续期，把锁重置成 30 秒，万一业务的机器宕机了，那就续期不了，30 秒之后锁就解开了

     *
    private void renewExpiration() {
        //从容器中去获取要被续期的锁
        RedissonLock.ExpirationEntry ee = EXPIRATION_RENEWAL_MAP.get(getEntryName());
        if (ee == null) {
            return;
        }

        // 创建一个定时任务
        Timeout task = commandExecutor.getConnectionManager().newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                RedissonLock.ExpirationEntry ent = EXPIRATION_RENEWAL_MAP.get(getEntryName());
                if (ent == null) {
                    return;
                }
                // 获取到当前 redis 锁的持有线程id
                Long threadId = ent.getFirstThreadId();
                if (threadId == null) {
                    return;
                }

                //Redis进行锁续期
                //这个方法的作用其实底层也是去执行LUA脚本，
                //续期时间为 internalLockLeaseTime，commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout()  默认为 3*10 秒
                RFuture<Boolean> future = renewExpirationAsync(threadId);
                future.onComplete((res, e) -> {
                    if (e != null) {
                        log.error("Can't update lock " + getName() + " expiration", e);
                        EXPIRATION_RENEWAL_MAP.remove(getEntryName());
                        return;
                    }

                    if (res) {
                        // reschedule itself
                        //如果成功续期，递归继续创建下一个 10S 后的任务, 每 internalLockLeaseTime / 3 间隔 续期一次
                        renewExpiration();
                    }
                });
            }
        }, internalLockLeaseTime / 3, TimeUnit.MILLISECONDS);

        ee.setTimeout(task);
    }
     */


    /**
     *

     public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
            //取得最大等待时间  单位毫秒
            long time = unit.toMillis(waitTime);
            // 当前系统时间
            long current = System.currentTimeMillis();
            // 当前线程id
            long threadId = Thread.currentThread().getId();
            //尝试申请锁，返回当前锁还剩余的锁过期时间，  针对 以获取到锁的线程
            Long ttl = tryAcquire(waitTime, leaseTime, unit, threadId);
            // lock acquired，  ttl 为空， 表明 当前redis 锁 已经被其他线程释放
            if (ttl == null) {
                return true;
            }

            //判断 是否超过 获取锁的等待时间
            time -= System.currentTimeMillis() - current;
            if (time <= 0) {
                acquireFailed(waitTime, unit, threadId);
                return false;
            }


            current = System.currentTimeMillis();
            // 订阅 当前 redis 锁订阅锁释放事件， 用于 当持有redis锁的线程 释放锁时，通知当前线程竞争锁，有效的解决了无效的锁申请浪费资源的问题：
            // subscribeFuture.await return false， 表明 等待事件 超过 当前线程设置的获取锁的最大等待时间，当前线程 通过cancel 取消订阅锁释放事件
            // subscribeFuture.await return true， 表明 可以进入循环再次获取锁
            RFuture<RedissonLockEntry> subscribeFuture = subscribe(threadId);
            if (!subscribeFuture.await(time, TimeUnit.MILLISECONDS)) {
                if (!subscribeFuture.cancel(false)) {
                    subscribeFuture.onComplete((res, e) -> {
                        if (e == null) {
                            // 取消订阅
                            unsubscribe(subscribeFuture, threadId);
                        }
                    });
                }
                acquireFailed(waitTime, unit, threadId);
                return false;
            }

            try {

                // 当前线程 还可以等待 多久时间
                time -= System.currentTimeMillis() - current;
                if (time <= 0) {
                    acquireFailed(waitTime, unit, threadId);
                    return false;
                }

                 // 循环
                 // 收到锁释放的信号后，在最大等待时间之内，循环一次接着一次的尝试获取锁
                 // 获取锁成功，则立马返回true，
                 // 若在最大等待时间之内还没获取到锁，则认为获取锁失败，返回false结束循环
                while (true) {
                    long currentTime = System.currentTimeMillis();
                    ttl = tryAcquire(waitTime, leaseTime, unit, threadId);
                    // lock acquired
                    if (ttl == null) {
                        return true;
                    }

                    time -= System.currentTimeMillis() - currentTime;
                    if (time <= 0) {
                        acquireFailed(waitTime, unit, threadId);
                        return false;
                    }

                    // waiting for message
                    //  阻塞 等待
                    currentTime = System.currentTimeMillis();
                    if (ttl >= 0 && ttl < time) {
                        // 持有锁 的线程 剩余持有时间 < 当前线程 剩余等待时间， 等待 持有锁 的线程 剩余持有时间 即可
                        subscribeFuture.getNow().getLatch().tryAcquire(ttl, TimeUnit.MILLISECONDS);
                    } else {
                        // 等待 当前线程 剩余等待时间 即可
                        subscribeFuture.getNow().getLatch().tryAcquire(time, TimeUnit.MILLISECONDS);
                    }

                    time -= System.currentTimeMillis() - currentTime;
                    if (time <= 0) {
                        acquireFailed(waitTime, unit, threadId);
                        return false;
                    }
                }
            } finally {
                unsubscribe(subscribeFuture, threadId);
            }
    //        return get(tryLockAsync(waitTime, leaseTime, unit));
        }

 */


    /**
     *  释放锁

    public void unlock() {
        try {
            get(unlockAsync(Thread.currentThread().getId()));
        } catch (RedisException e) {
            if (e.getCause() instanceof IllegalMonitorStateException) {
                throw (IllegalMonitorStateException) e.getCause();
            } else {
                throw e;
            }
        }
    }
     */

    /**
     *
     *
     *
    public RFuture<Void> unlockAsync(long threadId) {
        RPromise<Void> result = new RedissonPromise<Void>();
        RFuture<Boolean> future = unlockInnerAsync(threadId);

        future.onComplete((opStatus, e) -> {
            //删除expirationRenewalMap缓存，停止watch dog机制， 执行cancel()
            cancelExpirationRenewal(threadId);

            if (e != null) {
                result.tryFailure(e);
                return;
            }

            if (opStatus == null) {
                IllegalMonitorStateException cause = new IllegalMonitorStateException("attempt to unlock lock, not locked by current thread by node id: "
                        + id + " thread-id: " + threadId);
                result.tryFailure(cause);
                return;
            }

            result.trySuccess(null);
        });

        return result;
    }
    */
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        long time = TimeUnit.SECONDS.toMillis(5);
        System.out.println(time);
    }
}
