package com.li.jdk.id;

import java.util.UUID;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-08-10 17:37
 **/
public class GenerationIdTest {

    public static void main(String[] args) throws Exception {
        GenerationIdTest test = new GenerationIdTest();
        test.snowflake();
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }

    /**
     * 构成：由一组32位数的16进制数字所构成。
     * 格式：以连字号分为五段，表现形式为xxxxxxxx-xxxx-Mxxx-Nxxx-xxxxxxxxxxxx的32个字符
     * M 表示 UUID版本，目前只有五个版本，即只会出现1，2，3，4，5（下文会介绍这五个版本）
     * 数字 N 的一至三个最高有效位表示 UUID 变体，目前只会出现 8，9，a，b 四种情况。
     *
     * 版本1 基于时间的uuid，主要依赖时间戳和机器的mac地址
     *  规则：主要依赖当前的时间戳及机器mac地址，因此可以保证全球唯一性。
     *  优点：能基本保证全球唯一性。
     *  缺点：使用了Mac地址，因此会暴露Mac地址和生成时间
     *
     * 版本2 在版本1的基础上优化
     *  规则：将版本1的时间戳前四位换为POSIX的UID或GID，很少使用。
     *  优点：能保证全球唯一性。
     *  缺点：很少使用，常用库基本没有实现
     *
     * 版本3  基于名字空间的uuid
     *  规则：基于指定的名字空间/名字生成MD5散列值得到，标准不推荐。
     *  优点：不同名字空间或名字下的UUID是唯一的；相同名字空间及名字下得到的UUID保持重复。
     *  缺点：MD5碰撞问题，只用于向后兼容，后续不再使用。
     *
     * 版本4 基于随机数的uuid
     *  规则：基于随机数或伪随机数生成。
     *  优点：实现简单。
     *  缺点：重复几率可计算。机率也与随机数产生器的质量有关。若要避免重复机率提高，必须要使用基于密码学上的强伪随机数产生器来生成值才行
     * 版本5 跟版本3差不多，只是把散列算法的 MD5 变成 SHA1
     *  规则：将版本3的散列算法改为SHA1。
     *  优点：不同名字空间或名字下的UUID是唯一的；相同名字空间及名字下得到的UUID保持重复。
     *  缺点：SHA1计算相对耗时。
     *
     * 总得来说：
     * 版本 1/2 适用于需要高度唯一性且无需重复的场景
     * 版本 3/5 适用于一定范围内唯一且需要或可能会重复生成UUID的环境下
     * 版本 4 适用于对唯一性要求不太严格且追求简单的场景
     *
     * @return
     */
    public String uuid() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 雪花id   64 long类型
     * 1位：是符号位，由于long类型在java中带符号，正数0， 负数1
     * 41位：时间戳，毫秒级
     * 10位：数据机器位, 不同机器设置不同的值， 支持1024个机器  2^10   包括5位datacenterId和5位workerId<br>
     * 12位：序列号， 毫秒内的计数，12位的计数顺序号支持每个节点每毫秒4096个序列号(同一个机器，同一时间戳 2^12)
     *
     * 优点：本地生成，整体按时间趋势递增，有序，整个分布式系统内不会产生ID碰撞（由数据中心标识ID、机器标识ID作区分）
     * 缺点：由于雪花算法是强依赖于时间的，在分布式环境下，如果发生时钟回拨，很可能会引起ID重复、ID乱序、服务会处于不可用状态等问题
     * @return
     */
    public Long snowflake() {
        return new TwitterUidGeneratorUtil(9, 20).nextId();
    }
}
