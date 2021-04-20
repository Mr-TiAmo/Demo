package com.li.io.nio.netty.httpdemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @program: Demo
 * @description:   SimpleChannelInboundHandler 是ChannelInboundHandlerAdapter的 子类
 *                 HttpObject  和 http交互的数据类型
 * @author: 栗翱
 * @create: 2020-11-16 17:12
 **/
public class NettyHttpHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 读取浏览器http客户端发送数据
     * @param channelHandlerContext
     * @param httpObject
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpResponse) {
            //判断是不是http请求
            System.out.println("浏览器客户端地址："+channelHandlerContext.channel().remoteAddress());
            //给浏览器发送消息 [http协议]

            ByteBuf byteBuf = Unpooled.copiedBuffer("hello, this is server", CharsetUtil.UTF_8);
            //创建一个http响应
            //HttpVersion.HTTP_1_1 http协议版本   HttpResponseStatus.OK 200状态码
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text");//发送的消息类型
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());//发送的消息长度

            //将构建好的httpResponse 返回
            channelHandlerContext.writeAndFlush(response);
        }
    }
}
