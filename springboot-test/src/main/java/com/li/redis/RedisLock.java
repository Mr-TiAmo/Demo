package com.li.redis;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;

/**
 * @program: Demo
 * @description: 分布式锁
 * @author: 栗翱
 * @create: 2020-07-04 09:52
 **/
public class RedisLock {
    @Autowired
    private RedisTemplate redisTemplate;

    public void testRedisLock() {
        long now = System.currentTimeMillis();
        // key失效时间 毫秒
        Long expire = 1000L;
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", (now + expire), expire, TimeUnit.MILLISECONDS);
        if (lock) {
            //抢到锁，执行业务
            System.out.println("抢到锁，执行业务");
            //执行完业务 释放锁
            redisTemplate.delete("lock");
        } else {
            // 判断 锁是否过期 ，用于之前抢到锁 的节点业务执行时间超过 锁的过期时间
            Long value = (Long)redisTemplate.opsForValue().get("lock");
            if (now < value) {

            }
        }
    }

    /**   介绍完要使用的命令后，具体的使用步骤如下：

     setnx(lockkey, 当前时间+过期超时时间) ，如果返回1，则获取锁成功；如果返回0则没有获取到锁，转向2。

     get(lockkey)获取值oldExpireTime ，并将这个value值与当前的系统时间进行比较，如果小于当前系统时间，则认为这个锁已经超时，可以允许别的请求重新获取，转向3。

     计算newExpireTime=当前时间+过期超时时间，然后getset(lockkey, newExpireTime) 会返回当前lockkey的值currentExpireTime。

     判断currentExpireTime与oldExpireTime 是否相等，如果相等，说明当前getset设置成功，获取到了锁。如果不相等，说明这个锁又被别的请求获取走了，那么当前请求可以直接返回失败，或者继续重试。

     在获取到锁之后，当前线程可以开始自己的业务处理，当处理完毕后，比较自己的处理时间和对于锁设置的超时时间，如果小于锁设置的超时时间，则直接执行delete释放锁；如果大于锁设置的超时时间，则不需要再锁进行处理。
     */
    private Integer count = 0;
    @GetMapping("/lockDemo")
    public String lock()throws Exception{
        long timeOut = 1000L;
        long beginTime = System.currentTimeMillis();
        String value = String.valueOf(timeOut+ beginTime);
        Boolean isLock = redisTemplate.opsForValue().setIfAbsent("lockkey", value);
        if(!isLock){  //没有获取到锁
            Object sourceLockkey = redisTemplate.opsForValue().get("lockkey");
            long oldTime = Long.valueOf(sourceLockkey.toString());
            if(oldTime < System.currentTimeMillis()){  //原锁已经超时
                String value1 = String.valueOf(timeOut+ System.currentTimeMillis());
                Object newLockkey = redisTemplate.opsForValue().getAndSet("lockkey", value1);
                long newTime = Long.valueOf(newLockkey.toString());
                if(!(oldTime == newTime)){
                    throw new Exception("被别人抢走了锁");
                }else{
                    count++;
                    System.out.println("得到了锁");
                    beginTime = System.currentTimeMillis();
                }
            }
            throw new Exception("锁被他人占有中!");
        }else{
            count++;
            System.out.println("获取锁成功");
        }
        System.out.println(count);
        //业务逻辑  已获取到锁
        System.out.println("抢到了锁+++++++++++++++执行业务逻辑++++++++++++");
        long executeTime = System.currentTimeMillis()-beginTime;
        if(executeTime<timeOut){
            //释放锁
            Boolean lockkey = redisTemplate.delete("lockkey");
            if(lockkey){
                System.out.println("成功释放锁");
            }
        }
        return "成功";
    }
}
