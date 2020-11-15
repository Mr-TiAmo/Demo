package com.li.io.nio.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-11-15 10:06
 **/
public class ChatClient {

    private Selector selector;
    private SocketChannel socketChannel;
    private static final Integer port = 6666;
    private static final String host = "127.0.0.1";
    private String username;

    public ChatClient() throws Exception {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        username = socketChannel.getLocalAddress().toString().substring(1);
    }

    /**
     * 向服务器发送消息
     *
     * @param msg
     */
    public void send(String msg) {
        try {
            msg = username +"说" + msg;
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从服务器读取数据
     */
    public void read() {
        try {
            //有消息的事件
            int count = selector.select();
            if (count > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    if (next.isReadable()) {
                        SocketChannel channel = (SocketChannel) next.channel();
                        channel.configureBlocking(false);
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        String s = new String(buffer.array());
                        System.out.println(s);
                    } else {
                        System.out.println("ping ...");
                    }
                }
                iterator.remove();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ChatClient chatClient = new ChatClient();
        System.out.println(chatClient.socketChannel.getLocalAddress() + "连接到服务器");
        new Thread(() -> {
            while (true) {
                try {
                    //每个2s 读取一次数据
                    chatClient.read();
                    Thread.sleep(2000);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //发送数据
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            chatClient.send(s);
        }
    }
}
