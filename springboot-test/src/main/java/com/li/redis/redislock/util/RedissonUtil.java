package com.li.redis.redislock.util;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-09-21 15:39
 **/
public class RedissonUtil {

    private String address;
    private String password;
    private String port;
    private int dataSource;

    private Config config;
    private RedissonClient redissonClient;

    public RedissonUtil(String address,String password,String port,int dataSource){
        this.address = address;
        this.password = password;
        this.port = port;
        this.dataSource = dataSource;
        config = new Config();
        StringBuilder sb = new StringBuilder();
        sb.append("redis://").append(this.address).append(":").append(this.port);
        config.useSingleServer().
                setIdleConnectionTimeout(30000).
                setConnectionMinimumIdleSize(10).
                setAddress(sb.toString()).
                setPassword(this.password).setDatabase(this.dataSource);
        this.redissonClient = Redisson.create(config);
    }

    public Config getConfig() {
        return config;
    }

    public RLock getRLock(String key){
        RLock lock = redissonClient.getLock(key);
        return lock;
    }

    private void destroy() {
        redissonClient.shutdown();
    }
}
