package com.li.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-01-07 09:22
 **/
public class NetTest {
    /**
     * InetAddress类的简单实用
     * @throws Exception
     */
    public static void test1() throws Exception{
        InetAddress name = InetAddress.getByName("www.baidu.com");
        System.out.println(">>>>>>>>>>>>"+name);

        InetAddress name1 = InetAddress.getByName("127.0.0.1");
        System.out.println(">>>>>>>>>>>>>"+name1);

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(">>>>>>>>>>>>>"+localHost);
        //获取ip
        System.out.println(">>>>>>>>>>>>>"+localHost.getHostAddress());
        //获取域名
        System.out.println(">>>>>>>>>>>>>"+localHost.getHostName());
    }

    /**
     * 简单的 tcp 客户端
     */
    public static void tcpClient() {
        String ip = "127.0.0.1";
        int port = 10000;
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String message = "欢迎来到英雄联盟!!!";

        try {
            //建立连接
            socket = new Socket(ip, port);
            //发送数据
            outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes());
            //接收数据
            inputStream = socket.getInputStream();
            int len;
            byte[] bytes = new byte[1024];
            while((len = inputStream.read(bytes)) != -1){
                System.out.println(new String(bytes, 0, len, "utf-8"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
               if(null != socket){
                   socket.close();
               }
               if(null != outputStream){
                    outputStream.close();
               }
               if(null != inputStream){
                   inputStream.close();
               }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void tcpServer() {
        int port = 10000;
        ServerSocket serverSocket = null;
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            int len;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len, "utf-8"));
            }
            outputStream.write("敌军还有30秒到达战场!!!".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != socket) {
                    socket.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception{
        tcpClient();
    }
}
