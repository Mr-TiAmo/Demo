package com.li.io.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;


/**
 * @program: Demo
 * @description: 自定义一个 handler 需要继承netty 提供的HandlerAdapter接口
 * @author: 栗翱
 * @create: 2020-11-16 14:09
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * read 事件驱动
     * 这里我们可以读取客户端发送的数据
     * @param ctx 上下文对象， 包含 pipeline， channel，地址
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 多个 client 连接 server， server 使用不同的线程区处理
        // 当 线程数等于 NioEventLoopGroup 默认的 有cpu的核数 * 2个线程时，采用轮询
        System.out.println("服务器处理 client的线程：" + Thread.currentThread().getName());
        System.out.println("ctx:" + ctx);

        // pipeline 是一个双链表， pipeline 和 channel 相互持有对方对象
        System.out.println("pipeline:" + ctx.pipeline());
        System.out.println("channel:" + ctx.channel());

        //netty 提供的， 不是NIO 中的ByteBuffer
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址：" + ctx.channel().remoteAddress());


        // 实例 1 关于长耗时任务 异步处理  ***********
        // 自定义taskQueue 第一个任务10后返回结果， 第二个任务 10+10后返回结果， 两个任务是在一个线程的taskQueue里面
        // 自定义scheduleQueue
//        ctx.channel().eventLoop().schedule(() -> {
//            try {
//                Thread.sleep(10000);
//                ctx.writeAndFlush(Unpooled.copiedBuffer("长耗时任务1：", CharsetUtil.UTF_8));
//                System.out.println("长耗时任务结束1...");
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        },5, TimeUnit.SECONDS);
//        ctx.channel().eventLoop().execute(() -> {
//            try {
//                Thread.sleep(10000);
//                ctx.writeAndFlush(Unpooled.copiedBuffer("长耗时任务1：", CharsetUtil.UTF_8));
//                System.out.println("长耗时任务结束1...");
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        ctx.channel().eventLoop().execute(() -> {
//            try {
//                Thread.sleep(10000);
//                ctx.writeAndFlush(Unpooled.copiedBuffer("长耗时任务2：", CharsetUtil.UTF_8));
//                System.out.println("长耗时任务结束2...");
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(10000);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("长耗时任务：", CharsetUtil.UTF_8));
//        System.out.println("长耗时任务结束...");
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写到缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, this is server", CharsetUtil.UTF_8));
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
