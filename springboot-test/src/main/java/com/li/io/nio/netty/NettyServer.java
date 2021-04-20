package com.li.io.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.NettyRuntime;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-11-16 11:32
 **/
public class NettyServer {

    public static void main(String[] args) throws Exception{

        //创建两个线程组 bossGroup WorkerGroup
        // bossGroup 负责连接  WorkerGroup 负责IO 读写， 无限循环的
        // NioEventLoopGroup 默认的 有cpu的核数 * 2个  NioEventLoop
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
//        System.out.println(NettyRuntime.availableProcessors() * 2);
        try {
            //创建服务器启动对象，设置启动参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)  //设置两个线程组
                    .channel(NioServerSocketChannel.class)  //使用NioSocketChannel作为服务器通道
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //保持连接活动
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //创建一个通道测试对象，给pipeline设置 处理器对象
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //*****************************************
                            //可以通过这里用集合存放不同client对应的不同 SocketChannel，向不同client推送不同的信息，
                            // 放入channel().eventLoop()  自定义taskQueue或自定义 scheduleQueue
                            //*****************************************
                            System.out.println("不同客户端 client 对应 不同的 socketChannel" + socketChannel.hashCode());
                            socketChannel.pipeline().addLast(new ServerHandler());//设置通道处理器...
                        }
                    });

            System.out.println("server is ok...");
            //绑定一个端口并同步，创建一个ChannelFuture对象， 这里就是启动server
            // ChannelFuture对象 涉及到netty的异步模型
            ChannelFuture channelFuture = serverBootstrap.bind(6666).sync();

            //Future-Listener机制
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("端口绑定成功！！！");
                    }
                }
            });

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }finally {
            //关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
