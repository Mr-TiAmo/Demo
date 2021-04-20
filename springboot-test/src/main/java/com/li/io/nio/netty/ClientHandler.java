package com.li.io.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-11-16 14:41
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当通道就绪就会触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client ctx:" + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, this is client", CharsetUtil.UTF_8));
    }
    /**
     * read 事件驱动
     * 这里我们可以读取客户端发送的数据
     * @param ctx 上下文对象， 包含 pipeline， channel，地址
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //netty 提供的， 不是NIO 中的ByteBuffer
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务器发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器地址：" + ctx.channel().remoteAddress());
    }

    /**
     * 处理异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
