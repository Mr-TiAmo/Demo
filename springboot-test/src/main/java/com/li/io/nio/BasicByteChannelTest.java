package com.li.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-11-13 10:57
 **/
public class BasicByteChannelTest {

    public static void main(String[] args) throws Exception {
        write();
//        read();
//        copy();
//        transformCopy();
//        mappedByteBuffer();
//        ScatteringOrGathering();
    }

    /**
     * 写入文件
     * @throws Exception
     */
    public static void write() throws Exception{
        String str = "hello java 666";
        //创建一个输出流
        FileOutputStream outputStream = new FileOutputStream("F:\\ByteChannel.txt");
        //通过输出流获取通道channel
        FileChannel fileChannel = outputStream.getChannel();

        //创建一个缓冲区 ByteBuffer 设置大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将需要写入的字符 写入ByteBuffer
        byteBuffer.put(str.getBytes());

        //将写入ByteBuffer 的数据 读到channel中
        byteBuffer.flip();
        fileChannel.write(byteBuffer);

        //关闭资源
        outputStream.close();
    }

    /**
     * 读出文件
     * @throws Exception
     */
    public static void read() throws Exception{
        //创建一个输出流
        FileInputStream inputStream = new FileInputStream("F:\\ByteChannel.txt");
        //通过输出流获取通道channel
        FileChannel fileChannel = inputStream.getChannel();

        //创建一个缓冲区 ByteBuffer 设置大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将需要写入的字符 写入ByteBuffer
        fileChannel.read(byteBuffer);

        //输出
        System.out.println(new String(byteBuffer.array()));
        //关闭资源
        inputStream.close();
    }

    /**
     * 文件内容基础版拷贝
     * @throws Exception
     */
    public static void copy() throws Exception{
        //创建一个输出流
        FileInputStream inputStream = new FileInputStream("F:\\ByteChannel.txt");
        //通过输出流获取通道channel
        FileChannel fileChannel = inputStream.getChannel();

        //创建一个输出流
        FileOutputStream outputStream = new FileOutputStream("F:\\ByteChannelCopy.txt");
        FileChannel copyChannel = outputStream.getChannel();

        //创建一个缓冲区 ByteBuffer 设置大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        //将需要写入的字符 写入ByteBuffer
        while (true) {

            int read = fileChannel.read(byteBuffer);
            if (read != -1) {
                //赋值内容到另外一个channel中
                byteBuffer.flip();
                copyChannel.write(byteBuffer);
                //如果文件太大，buffer一次读不完，需要进行复位
                byteBuffer.clear();
            } else {
                break;
            }
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * 使用api进行复制
     *
     * @throws Exception
     */
    public static void transformCopy() throws Exception{
        //创建一个输出流
        FileInputStream inputStream = new FileInputStream("F:\\ByteChannel.txt");
        //通过输出流获取通道channel
        FileChannel fileChannel = inputStream.getChannel();

        //创建一个输出流
        FileOutputStream outputStream = new FileOutputStream("F:\\ByteChannelTransformCopy.txt");
        FileChannel copyChannel = outputStream.getChannel();

        //复制
        copyChannel.transferFrom(fileChannel,0,fileChannel.size());

        inputStream.close();
        outputStream.close();
    }

    /**
     * mappedByteBuffer 通过直接内存，以映射的方式 直接修改文件内容
     *  写入速度快，消耗大量直接内存
     */
    public static void mappedByteBuffer() throws Exception{
        RandomAccessFile file = new RandomAccessFile("F:\\ByteChannel.txt", "rw");
        //获取channel
        FileChannel channel = file.getChannel();
        //参数1 使用读写模式  参数2 可以直接修改的直接位置  参数3 映射到内存的大小
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0,(byte)'H');
        // 不能超过索引 IndexOutOfBoundsException
//        mappedByteBuffer.put(10000,(byte)'H');
        file.close();
    }

    /**
     * Scattering: 将数据写入buffer时，可以写入多个buffer数组中， 进行依次写入
     * Gathering: 从buffer中读取数据时， 可以采用buffer数组中， 进行依次读出
     */

    public static void ScatteringOrGathering() throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(7777);
        //绑定端口到socket 并启动
        serverSocketChannel.socket().bind(address);

        //创建byteBuffer数组
        ByteBuffer[] buffers = new ByteBuffer[2];
        //第一个ByteBuffer 长度
        buffers[0] = ByteBuffer.allocate(5);
        //第二个ByteBuffer 长度
        buffers[1] = ByteBuffer.allocate(4);

        //等待 客户端连接(telnet)
        SocketChannel channel = serverSocketChannel.accept();

        //设置缓冲区长度，超过该长度才开始输出
        Integer messageLength = 9;

        while (true) {
            int read = 0;
            while (read < messageLength) {
                long l = channel.read(buffers);
                read += l;
                System.out.println("读取到数据长度" + read);
                Arrays.asList(buffers).stream().map(buffer -> "position" + buffer.position() + ", limit" + buffer.limit())
                        .forEach(System.out::println);
            }

            //将所有buffer进行 flip
            Arrays.asList(buffers).forEach(buffer -> buffer.flip());

            //将数据回显到客户端
            int write = 0;
            while (write < messageLength) {
                long l = channel.write(buffers);
                write += l;
            }
            //将所有buffer clear
            Arrays.asList(buffers).forEach(buffer -> buffer.clear());

            System.out.println("read:" + read + ", write:" + write);
            Arrays.asList(buffers).forEach(buffer -> System.out.println(new String(buffer.array())));
        }
    }
}
