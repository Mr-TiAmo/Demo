package com.li.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: Demo
 * @description:  模拟 serverSocketChannel 服务器端
*                   SelectionKey API
 *                   SelectionKey.select() 得到与之相关联的select 对象
 *                   SelectionKey.channel() 得到与之相关联的channel 对象
 *                   SelectionKey.attachment() 得到与之相关联的共享的数据
 *                   SelectionKey.interestOps() 设置或改变监听事件
 *
 * @author: 栗翱
 * @create: 2020-11-13 16:15
 **/
public class NIOServerTest {

    public static void main(String[] args) throws Exception{
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //获取一个Selector 对象
        Selector selector = Selector.open();
        //服务器端监听6666端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把ServerSocketChannel 注册到Selector 对象 并设置关心事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("注册后的 SelectionKey个数：" + selector.keys().size());

        //循环等待客户端连接
        while (true) {
            //在这里等待1秒，如果没有事件发生返回
            if(selector.select(1000) == 0) {
                System.out.println("服务器端在这里等待1秒");
                continue;
            }
            // 如果返回大于0，说明有对应的 连接事件发生， 返回关注事件的集合
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            System.out.println("有事件发生的channel：" + selectionKeySet.size());
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //通过 SelectionKey 获取对应的channel
                if (key.isAcceptable()) { //如果当前对象 是 关注事件为 OP_ACCEPT，表明有客户端连接
                    //给 当前需要连接的客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel 注册到 Selector 对象， 并绑定关注事件OP_READ 以及buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接成功，当前channel：" + socketChannel.hashCode());
                    System.out.println("客户端连接成功后 注册后的 SelectionKey个数：" + selector.keys().size());
                }
                if (key.isReadable()) {  //如果当前对象 是 关注事件为 OP_READ
                    //通过key 反向获取 channel
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    //获取key 关联的ByteBuffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    socketChannel.read(buffer);
                    System.out.println("客户端发送的信息：" + new String(buffer.array()));
                }

                //手动移除当前SelectionKey，防止重复操作
                iterator.remove();
            }
        }
    }
}
