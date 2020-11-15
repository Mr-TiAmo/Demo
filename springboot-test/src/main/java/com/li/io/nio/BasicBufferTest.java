package com.li.io.nio;

import java.nio.IntBuffer;
import java.util.stream.IntStream;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-11-13 09:29
 **/
public class BasicBufferTest {
    public static void main(String[] args) {
        //创建一个 buffer并设置buffer容量
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //存放数据
        IntStream.range(0,5).forEach(i -> intBuffer.put(i *2));
        //进行buffer 读写切换
        intBuffer.flip();
        //读取数据
        while (intBuffer.hasRemaining()) {
            //intBuffer.get() 中会维护一个下标索引
            System.out.println(intBuffer.get());
        }
    }
}
