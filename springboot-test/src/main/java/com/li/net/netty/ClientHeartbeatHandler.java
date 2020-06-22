package com.li.net.netty;///*
// * Copyright 2020 tu.cn All right reserved. This software is the
// * confidential and proprietary information of tu.cn ("Confidential
// * Information"). You shall not disclose such Confidential Information and shall
// * use it only in accordance with the terms of the license agreement you entered
// * into with Tu.cn
// */
//package com.vinsuan.parking.client.netty;
//
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.timeout.IdleState;
//import io.netty.handler.timeout.IdleStateEvent;
//import io.netty.util.Attribute;
//import io.netty.util.AttributeKey;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
///**
// * @Author: Administrator
// * @Date: 2020-03-05 16:42
// * @Version 1.0
// */
//@Component
//@Slf4j
//public class ClientHeartbeatHandler extends ChannelHandlerAdapter {
//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        //获取岗亭id
//        Attribute attr = ctx.channel().attr(AttributeKey.valueOf("boxId"));
//        String boxId = (null != attr && null != attr.get()) ? attr.get().toString() : "";
//        IdleStateEvent event = (IdleStateEvent) evt;
//        if (event.state().equals(IdleState.READER_IDLE)) {
//            log.warn("+++++++++++++Netty服务器在岗亭boxId：" + boxId +  ",通道id："+ ctx.channel().id() +"上读超时，已将该通道关闭");
//            ctx.channel().close();
//        } else if (event.state().equals(IdleState.WRITER_IDLE)) {
//            log.warn("+++++++++++++Netty服务器在岗亭boxId：" + boxId + ",通道id："+ ctx.channel().id() +"上写超时，已将该通道关闭");
//            ctx.channel().close();
//        } else if (event.state().equals(IdleState.ALL_IDLE)) {
//            log.warn("+++++++++++++Netty服务器在岗亭boxId：" + boxId + ",通道id："+ ctx.channel().id() +"上读&写超时，已将该通道关闭");
//            ctx.channel().close();
//        }
//        super.userEventTriggered(ctx, evt);
//    }
//}
