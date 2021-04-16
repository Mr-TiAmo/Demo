package com.li.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.li.comm.aop.DataSource;
import com.li.comm.constant.DataSourceType;
import com.li.redis.BloomFilterHelper;
import com.li.redis.RedisBloom;
import com.li.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2021-04-15 15:34
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisBloom redisBloom;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderService orderService;

    /**
     * 谷歌布隆过滤器
     */
    // 初始容量
    private static final int size = 10000;
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.03);

    /**
     * redis布隆过滤器
     */
    private BloomFilterHelper<String> bloomFilterHelper = new BloomFilterHelper<>((Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8)
            .putString(from, Charsets.UTF_8), 1000, 0.03);
//    private BloomFilterHelper<Integer> bloomFilterHelper = new BloomFilterHelper<>(Funnels.integerFunnel(), 10000, 0.03);

    @GetMapping("/testRedis")
    @ApiOperation(value = "testRedis", notes = "redis")
    @DataSource(value = DataSourceType.db1)
    public void test3() throws Exception {
        orderService.testRedis();
        for (int i = 1; i < 25000; i++) {
            redisTemplate.delete(String.valueOf(i));
        }
//        System.out.println(redisTemplate.opsForValue().get("10"));
    }

    @GetMapping("/testBloomFilter")
    @ApiOperation(value = "测试redisBloomFilter", notes = "redis")
    public String BloomFilter() {
        for (int i = 1; i < 100; i++) {
            //先放入 redis布隆过滤器容器中
            redisBloom.addByBloomFilter(bloomFilterHelper, String.valueOf(i), String.valueOf(i));
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 100; i < 1000; i++) {
            if (redisBloom.includeByBloomFilter(bloomFilterHelper, String.valueOf(i), String.valueOf(i))) {
                System.out.println(i);
                list.add(i);
            }
        }
        System.out.println("测试redisBloomFilter 误判:" + list.size());
        return null;
    }

    /**
     * BloomFilter 维护在内存中，服务重启会清空
     */
    @GetMapping("/googleBloomFilter")
    @ApiOperation(value = "测试谷歌BloomFilter", notes = "redis")
    public void googleBloomFilter() {
        for (int i = 0; i < 10000; i++) {
            bloomFilter.put(i);
        }
        List<Integer> list = new ArrayList<>(10000);
        //故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = 10000; i < 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("测试谷歌BloomFilter 误判：" + list.size()); //在300左右
    }

    @GetMapping("/keyExpire")
    @ApiOperation(value = "key过期监听", notes = "redis")
    public void keyExpire() {
//        redisTemplate.opsForValue().set("keyExpire", "keyExpire");
        redisTemplate.opsForValue().set("keyExpire", "11111", 3, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("hello", "11111", 3, TimeUnit.SECONDS);
    }

    @GetMapping("/redisPublish")
    @ApiOperation(value = "redis 发布订阅", notes = "redis")
    public void redisPublish() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                redisTemplate.convertAndSend("index",String.valueOf(i));
            }else {
                redisTemplate.convertAndSend("home",String.valueOf(i));
            }
            Thread.sleep(100);
        }

    }

    @GetMapping("/redisPublishList")
    @ApiOperation(value = "redis 发布订阅 List", notes = "redis")
    public void redisPublishList() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            redisTemplate.opsForList().rightPush("fan",String.valueOf(i));
            redisTemplate.convertAndSend("fans",String.valueOf(i));
            System.out.println("redis 发布订阅 List 执行完毕");

        }

    }
}
