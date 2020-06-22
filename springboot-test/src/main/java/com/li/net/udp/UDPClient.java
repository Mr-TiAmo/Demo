package com.li.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-01-07 15:22
 **/
public class UDPClient {
    public static void main(String[] args) {
        int port = 10000;
        DatagramSocket socket = null;
        String message = "欢迎来到英雄联盟!!!";
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            socket = new DatagramSocket();

            //向服务器发送消息
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, ip, port);
            socket.send(packet);

            //接收消息
            byte[] bytes = new byte[1024];
            packet = new DatagramPacket(bytes, bytes.length, ip, port);
            socket.receive(packet);

            System.out.println("接收到的数据>>>>>"+ new String(packet.getData(), 0, packet.getLength()));





        }catch (Exception e){
            e.printStackTrace();
        }finally {
            socket.close();
        }

    }
}
