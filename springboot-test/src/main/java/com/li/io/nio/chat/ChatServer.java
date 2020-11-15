package com.li.io.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-11-15 10:06
 **/
public class ChatServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final Integer port = 6666;

    // 初始化 服务端
    public ChatServer() throws Exception {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 监听 事件
     */
    public void listen() {
        System.out.println("服务器启动了！！！");
        try {
            while (true) {
                //在这里等待1秒，如果没有事件发生返回
                if (selector.select(1000) == 0) {
//                    System.out.println("服务器端在这里等待1秒");
                    continue;
                }
                // 有事件发生
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey next = keyIterator.next();
                    if (next.isAcceptable()) {
                        //连接事件发生
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        System.out.println(socketChannel.getRemoteAddress() + "上线了");
                    }
                    if (next.isReadable()) {
                        //可读事件发生
                        read(next);
                    }
                }
                //防止多线程重复操作
                keyIterator.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取客户端消息
     *
     * @param key
     */
    public void read(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            //通过 SelectionKey获取 当前可读的channel
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                //控制台打印
                System.out.println("from " + socketChannel.getRemoteAddress() + "客户端的消息：" + msg);
                //向其他的客户端转发消息
                sendToOther(msg, socketChannel);
            }
        } catch (IOException e) {
            try {
                System.out.println("客户端 " + socketChannel.getRemoteAddress() + "离线了");
                //取消注册
                key.cancel();
                //关闭通道
                socketChannel.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    /**
     * 向其他客户端转发消息
     * @param msg
     * @param self
     * @throws Exception
     */
    public void sendToOther(String msg, SocketChannel self) throws Exception{
        System.out.println("服务器转发客户端的消息...");
        //遍历selector 关联的所有 channel
        for (SelectionKey key : selector.keys()) {
            //获取当前 SelectionKey 对应的channel
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != self) {
                //排除自己需要转发的客户端
                ((SocketChannel) channel).write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }

    public static void main(String[] args) throws Exception{
        ChatServer chatServer = new ChatServer();
        chatServer.listen();
    }
}
