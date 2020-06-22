package com.li.redis;

import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @program: https://www.cnblogs.com/wmyskxz/p/12461766.html
 * @description: redis 布隆过滤器  可以持久化到 redis服务中
 * 布隆过滤器相对于Set、Map 等数据结构来说，它可以更高效地插入和查询，并且占用空间更少，它也有缺点，
 * 就是判断某种东西是否存在时，可能会被误判。但是只要参数设置的合理，它的精确度也可以控制的相对精确，只会有小小的误判概率
 *
 * 原理：
 *  长度为 m 的位向量或位列表(只包含0、1位值的列表)， 初始值都为0，通过多个hash函数对 key进行位运算，获取一个证书索引值，
 *  然后对位数组长度进行取模运算，每个hash函数会算的一个不同的位置，把数组的这几个位置设为1，就完成了add操作
 *
 *  查询是否存在是，通过key与多个hash函数进行运算，查看对应的位置是否为1 ，全部为1，判定存在， 有为0，判不存在
 *  会存在误判系数
 *
 * 应用：
 * 解决缓存穿透的问题
 * 一般情况下，先查询缓存是否有该条数据，缓存中没有时，再查询数据库。当数据库也不存在该条数据时，
 * 每次查询都要访问数据库，这就是缓存穿透。缓存穿透带来的问题是，当有大量请求查询数据库不存在的数据时，就会给数据库带来压力，甚至会拖垮数据库。
 * 可以使用布隆过滤器解决缓存穿透的问题，把已存在数据的key存在布隆过滤器中。当有新的请求时，先到布隆过滤器中查询是否存在，
 * 如果不存在该条数据直接返回；如果存在该条数据再查询缓存查询数据库。
 * <p>
 * 黑名单校验
 * 发现存在黑名单中的，就执行特定操作。比如：识别垃圾邮件，只要是邮箱在黑名单中的邮件，就识别为垃圾邮件。
 * 假设黑名单的数量是数以亿计的，存放起来就是非常耗费存储空间的，布隆过滤器则是一个较好的解决方案。
 * 把所有黑名单都放在布隆过滤器中，再收到邮件时，判断邮件地址是否在布隆过滤器中即可
 * @author: 栗翱
 * @create: 2020-06-18 14:57
 **/
public class BloomFilterHelper<T> {
    //    //初始容量，当实际元素的数量超过这个初始化容量时，误判率上升
//    private long count = 10000;
//    //期望错误率，期望错误率越低，需要的空间就越大
//    private double fpp = 0.03;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    //bit数组长度
//    private long numBits;
//    //hash函数数量
//    private int numHashFunctions ;
//
//    public long getCount() {
//        return count;
//    }
//
//    public void setExpectedInsertions(long count) {
//        this.count = count;
//    }
//
//    public void setFpp(double fpp) {
//        this.fpp = fpp;
//    }
//
//    public double getFpp() {
//        return fpp;
//    }
//
//    @PostConstruct
//    public void init(){
//        this.numBits = optimalNumOfBits(count, fpp);
//        this.numHashFunctions = optimalNumOfHashFunctions(count, numBits);
//    }
//
//    //计算hash函数个数
//    private int optimalNumOfHashFunctions(long n, long m) {
//        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
//    }
//
//    //计算bit数组长度
//    private long optimalNumOfBits(long n, double p) {
//        if (p == 0) {
//            p = Double.MIN_VALUE;
//        }
//        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
//    }
//
//    /**
//     * 判断keys是否存在于集合
//     */
//    public boolean isExist(String key) {
//        long[] indexs = getIndexs(key);
//        List list = redisTemplate.executePipelined(new RedisCallback<Object>() {
//
//            @Nullable
//            @Override
//            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                redisConnection.openPipeline();
//                for (long index : indexs) {
//                    redisConnection.getBit("bf:hilite".getBytes(), index);
//                }
//                redisConnection.close();
//                return null;
//            }
//        });
//        return list.contains(false);
//    }
//
//    /**
//     * 将key存入redis bitmap
//     */
//    public void put(String key) {
//        long[] indexs = getIndexs(key);
//        redisTemplate.executePipelined(new RedisCallback<Object>() {
//
//            @Nullable
//            @Override
//            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                redisConnection.openPipeline();
//                for (long index : indexs) {
//                    redisConnection.setBit("bf:hilite".getBytes(),index,true);
//                }
//                redisConnection.close();
//                return null;
//            }
//        });
//    }
//
//    /**
//     * 根据key获取bitmap下标
//     */
//    private long[] getIndexs(String key) {
//        long hash1 = hash(key);
//        long hash2 = hash1 >>> 16;
//        long[] result = new long[numHashFunctions];
//        for (int i = 0; i < numHashFunctions; i++) {
//            long combinedHash = hash1 + i * hash2;
//            if (combinedHash < 0) {
//                combinedHash = ~combinedHash;
//            }
//            result[i] = combinedHash % numBits;
//        }
//        return result;
//    }
//
//    /**
//     * 获取一个hash值
//     */
//    private long hash(String key) {
//        Charset charset = Charset.forName("UTF-8");
//        return Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asLong();
//    }
    //初始容量，当实际元素的数量超过这个初始化容量时，误判率上升
    private int numHashFunctions;
    //期望错误率，期望错误率越低，需要的空间就越大
    private int bitSize;

    private Funnel<T> funnel;

    public BloomFilterHelper(Funnel<T> funnel, int expectedInsertions, double fpp) {
        Preconditions.checkArgument(funnel != null, "funnel不能为空");
        this.funnel = funnel;
        bitSize = optimalNumOfBits(expectedInsertions, fpp);
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
    }

    public int[] murmurHashOffset(T value) {
        int[] offset = new int[numHashFunctions];

        long hash64 = Hashing.murmur3_128().hashObject(value, funnel).asLong();
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);
        for (int i = 1; i <= numHashFunctions; i++) {
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0) {
                nextHash = ~nextHash;
            }
            offset[i - 1] = nextHash % bitSize;
        }

        return offset;
    }

    /**
     * 计算bit数组长度
     */
    private int optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    /**
     * 计算hash方法执行次数
     */
    private int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

}
