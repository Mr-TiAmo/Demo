package com.li.net.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-01-07 10:06
 **/
public class TcpClient1 {

    public static void main(String[] args) {
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String ip = "127.0.0.1";
        int port = 10000;
        String message = "敌军还有30秒到达战场!!!";

        try {
            //建立连接
            socket = new Socket(ip, port);

            //接收数据
            inputStream = socket.getInputStream();
            int len;
            byte[] bytes = new byte[1024];
            while((len = inputStream.read(bytes)) != -1){
                System.out.println(new String(bytes, 0, len, "utf-8"));
                //当数据读取完毕后，手动结束，不然会造成流的阻塞
                if(len!=bytes.length){
                    break ;
                }
            }
            //发送数据
            outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes());
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(null != inputStream){
                    inputStream.close();
                }
                if(null != outputStream){
                    outputStream.close();
                }
                if(null != socket){
                    socket.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
