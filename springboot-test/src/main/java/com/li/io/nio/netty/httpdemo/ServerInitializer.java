package com.li.io.nio.netty.httpdemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @program: Demo
 * @description:   给pipeline设置 处理器对象
 * @author: 栗翱
 * @create: 2020-11-16 17:08
 **/
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 设置 pipeline  处理HTTP请求的编码解码器
        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());
        // 设置 处理器
        pipeline.addLast("MyNettyHttpHandler",new NettyHttpHandler());
    }
}
