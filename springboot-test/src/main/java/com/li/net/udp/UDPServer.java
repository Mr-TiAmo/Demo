package com.li.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-01-07 15:37
 **/
public class UDPServer {
    private static int port = 10000;
    private static DatagramSocket socket = null;
    private static String message = "敌军还有30秒到达战场!!!";
    private static InetAddress ip = null;

    public static void main(String[] args) {

        try {
            ip = InetAddress.getByName("localhost");
            socket = new DatagramSocket(port);
            while(true){
                //接收数据
                byte[] bytes = new byte[1024];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                socket.receive(packet);
                serverThread(socket, packet);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            socket.close();
        }

    }

    public static void serverThread(final DatagramSocket socket,final DatagramPacket packet){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int length = packet.getLength();
                System.out.println(new String(packet.getData(), 0, length));

                //发送数据
                try {
                    DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.getBytes().length, ip, port);
                    socket.send(datagramPacket);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
