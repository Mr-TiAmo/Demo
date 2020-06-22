package com.li.net.tcp;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-01-07 10:07
 **/
public class TcpServerThread {
    private static int i = 1;

    public static void serverThread(Socket socket){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("客户端"+i+"连接成功");
                OutputStream outputStream = null;
                InputStream inputStream = null;

                try {
                    outputStream = socket.getOutputStream();
                    outputStream.write("欢迎来到英雄联盟!!!".getBytes("utf-8"));
                    outputStream.flush();


                    Thread.sleep(1000 * 60);


                    while (true) {
//                        inputStream = socket.getInputStream();
                        outputStream.write("欢迎来到英雄联盟!!!".getBytes("utf-8"));
                        outputStream.flush();
                        Thread.sleep(1000);
                    }
//                    int len;
//                    byte[] bytes = new byte[1024];
//                    while ((len = inputStream.read(bytes)) != -1) {
//                        System.out.println(new String(bytes, 0, len, "utf-8"));
//                        //当数据读取完毕后，手动结束，不然会造成流的阻塞
//                        if(len!=bytes.length){
//                            break ;
//                        }
//                    }
//                i++;
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (null != outputStream) {
                            outputStream.close();
                        }
                        if (null != inputStream) {
                            inputStream.close();
                        }
                        if (null != socket) {
                            socket.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();
    }

    public static void main(String[] args) {
        int port = 10000;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务器已启动：");
            while(true){
                socket = serverSocket.accept();
                serverThread(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
