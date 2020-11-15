package com.li.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-11-13 17:50
 **/
public class NIOClientTest {

    public static void main(String[] args) throws Exception{
        //创建 SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        //设置channel 非阻塞
        socketChannel.configureBlocking(false);
        //设置socket连接 ip port
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);

        //连接服务器
        if (!socketChannel.connect(address)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接需要事件，可以做其他事，客户端不会阻塞");
            }
        }
        System.out.println("连接服务器...");
        //连接成功，发送数据
        String str = "你好呀";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(byteBuffer);
        //使客户端停在这
        System.in.read();
    }
}
