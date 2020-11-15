package com.li.io.bio;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-11-13 08:58
 **/
public class BIOTest {
    public static void main(String[] args) throws Exception{

        ServerSocket socketServer = new ServerSocket(6666);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("缓存线程池").build();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory);

        System.out.println("socket 服务器启动了");

        while (true) {
            System.out.println("线程ID：" +Thread.currentThread().getId() +"线程Name：" +Thread.currentThread().getName());
            //监听，等待客户端连接
            final Socket socket = socketServer.accept();
            System.out.println("连接到一个客户端");
            poolExecutor.execute(() -> {
                socketTest(socket);
            });
        }
    }
    public static void socketTest(Socket socket) {
        System.out.println("线程ID：" +Thread.currentThread().getId() +"线程Name：" +Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println("线程ID：" +Thread.currentThread().getId() +"线程Name：" +Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if (read != -1) {
                    //读取数据
                    System.out.println(new String(bytes,0,read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("关闭与客户端连接");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
