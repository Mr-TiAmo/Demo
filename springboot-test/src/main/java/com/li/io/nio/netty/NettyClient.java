package com.li.io.nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program: Demo
 * @description: netty 的客户端
 * @author: 栗翱
 * @create: 2020-11-16 14:34
 **/
public class NettyClient {

    public static void main(String[] args) {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            // 客户端注意 是Bootstrap ，而不是服务器端的ServerBootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)  //设置客户端的通道实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 设置client 的处理器
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            System.out.println("client is ok...");
            //启动client， 并连接server
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
