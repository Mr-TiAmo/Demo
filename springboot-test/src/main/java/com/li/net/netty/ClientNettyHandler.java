/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.li.net.netty;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.PlatformDependent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author: Administrator
 * @Date: 2020-02-05 14:11
 * @Version 1.0
 */
@Component
@Slf4j
public class ClientNettyHandler extends SimpleChannelInboundHandler<Object> {

    public static ChannelGroup group = new DefaultChannelGroup("CLIENT_GROUP",GlobalEventExecutor.INSTANCE);
    private static final ConcurrentMap<String, Channel> clientChannels = PlatformDependent.newConcurrentHashMap();
    private static Map<String, Queue<ClientMessage<ResultDataVO>>> queueMap = new ConcurrentHashMap<>();
    @Getter
//    private static Map<String, InOutParkVO> ioNoMap = new ConcurrentHashMap<>();
    private static Map<String, List<String>> gateList = new ConcurrentHashMap<>();
    private WebSocketServerHandshaker handshaker;
    private static ClientNettyHandler clientNettyHandler;



    @PostConstruct
    public void init() {
        clientNettyHandler = this;
    }

//    /**
//     * 心跳检测
//     */
//    @Scheduled(cron = "0 */10 * * * ?")
//    public void TestDatabase() {
////        ClientMessage send = new ClientMessage();
////        send.setCmd(ClientCmd.DATABASE_STATUS);
////        try {
////            log.info("+++++++++++++++检测数据库连接状态");
////            clientNettyHandler.parkingInfoService.list();
////            send.setData(0);
////            log.info("+++++++++++++++数据库连接正常");
////        } catch (Exception e) {
////            log.error("+++++++++++++++数据库连接异常:" + ShowUtil.exceptionToString(e));
////            send.setData(1);
////        } finally {
////            sendAllMsg(send);
////        }
//
//    }

    /**
     * channel 通道 action 活跃的 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("+++++++++++++++客户端与服务端连接开启：{}", ctx.channel().remoteAddress().toString());
    }

    /**
     * channel 通道 Inactive 不活跃的 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端关闭了通信通道并且不可以传输数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Attribute clientAttr = ctx.channel().attr(AttributeKey.valueOf("boxId"));
        try {
            if (null != clientAttr && null != clientAttr.get()) {
                String boxId = clientAttr.get().toString();
                log.info("+++++++++++++++客户端与服务端连接关闭：{}", boxId);
                clientChannels.remove(boxId, ctx.channel());
            }
        } catch (Exception e) {
            log.error("+++++++++++++++关闭不活跃客户端连接发生异常：{}");
        }
        group.remove(ctx.channel());
        log.info("+++++++++++++++客户端与服务端连接关闭:{},当前连接数量:{}，group连接数量:{}", ctx.channel().remoteAddress().toString(), clientChannels.size(), group.size());
    }

    /**
     * 接收客户端发送的消息 channel 通道 Read 读 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据。但是这个数据在不进行解码时它是ByteBuf类型的
     *
     * @param channelHandlerContext
     * @param msg 客户端传过来的数据
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        //初始握手
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(channelHandlerContext, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handlerWebSocketFrame(channelHandlerContext, (WebSocketFrame) msg);
        } else {
            //TODO 其他处理
            log.info("+++++++++++++++其他情况暂不处理："+ msg.getClass().getName());
            return;
        }
    }

    /**
     * channel 通道 Read 读取 Complete 完成 在通道读取完成后会在这个方法里通知，对应可以做刷新操作 ctx.flush()
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * exception 异常 Caught 抓住 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        String e = cause.getLocalizedMessage();
        if(cause instanceof Exception){
        }
        log.error("+++++++++++++++连接发生异常：{}",e);
    }

    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //获取设备id
        Attribute attr = ctx.channel().attr(AttributeKey.valueOf("boxId"));
        String boxId = (null != attr && null != attr.get()) ? attr.get().toString() : "";

        //关闭连接请求
        if (frame instanceof CloseWebSocketFrame) {
            log.info("+++++++++++++++岗亭连接关闭,boxId：{}", boxId);
            ctx.channel().close();
            return;
        }
        //ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //文本消息
        if (frame instanceof TextWebSocketFrame) {
            String msg = ((TextWebSocketFrame) frame).text();
            handlerMessage(ctx, msg, boxId);
        }
    }

    /**
     * 处理 http 请求，WebSocket 初始握手 (opening handshake ) 都始于一个 HTTP 请求
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        // 如果HTTP解码失败，返回HTTP异常
        if (!req.getDecoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //获取url后置参数
        HttpMethod method = req.getMethod();
        String uri = req.getUri();
        uri = uri.replaceAll("/", "");
        String[] split = uri.split("=");
        if (method == HttpMethod.GET && "/webssss".equals(uri)) {
            //....处理
            ctx.attr(AttributeKey.valueOf("type")).set("vs");
        } else if (method == HttpMethod.GET && "/websocket".equals(uri)) {
            //...处理
            ctx.attr(AttributeKey.valueOf("type")).set("live");
        } else if (method == HttpMethod.GET && "boxId".equals(split[0])) {    /////测试
            // 向通道放入 boxId
            ctx.channel().attr(AttributeKey.valueOf("boxId")).set(split[1]);
            clientChannels.put(split[1], ctx.channel());
        }

        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://" + req.headers().get(HttpHeaders.Names.HOST) + uri, null, false, 65536 * 10);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 响应非 WebSocket 初始握手请求
     */
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpHeaders.isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

//    private static void initData(String boxId) {
//        try {
//            ClientMessage send = new ClientMessage();
//            //下发进出场记录列表
//            List<ParkIORecordVO> parkIORecordVOS = clientNettyHandler.parkingInRecordService.getInOutRecordListByBoxId(boxId);
//            send.setCmd(ClientCmd.IO_RECORD_LIST);
//            send.setData(parkIORecordVOS);
//            sendMsg(boxId, send);
//            //下发车场及区域余位信息
//            List<SpaceVO> spaceVOS = ProcessBase.querySpaceByBoxId(boxId);
//            send.setCmd(ClientCmd.CAR_BERTH_INFO);
//            send.setData(spaceVOS);
//            sendMsg(boxId, send);
////            //下发设备状态信息
////            List<DeviceStatusVO> deviceStatusVOS = ProcessBase.listDeviceStatusByBoxId(boxId);
////            send.setCmd(ClientCmd.DEVICE_STATUS_INFO);
////            send.setData(deviceStatusVOS);
////            sendMsg(boxId, send);
//
//            send.setCmd(ClientCmd.DATABASE_STATUS);
//            send.setData(0);
//            sendAllMsg(send);
//        } catch (Exception e) {
//            log.error("+++++++++++++++给岗亭推送初始化数据发生异常：{}" ,ShowUtil.exceptionToString(e));
//        }
//    }

    private static void sendAllMsg(ClientMessage message){
        try {
            String msg = JSONUtil.toJsonStr(message);
            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(msg);
            group.writeAndFlush(textWebSocketFrame);
        } catch (Exception e) {
            log.error("+++++++++++++++给所有岗亭推送数据发生异常：{}");
        }
    }

    private static void handlerMessage(ChannelHandlerContext channelHandlerContext, String msg, String boxId) {
        try {
            if (!(msg.indexOf("HEARTBEAT")> 0)){
                log.info("+++++++++++++++处理岗亭发送过来的数据,msg：{}" , msg);
            }
            JSONObject jsonObject = JSONUtil.parseObj(msg);
            ClientMessage<String> clientMessage = jsonObject.toBean(ClientMessage.class);
            ClientMessage send = new ClientMessage();
            switch (clientMessage.getCmd()) {
                case ClientCmd.SIGN_IN:
                    if (StrUtil.isNotBlank(clientMessage.getData())){
                        if (clientChannels.containsKey(clientMessage.getData())) {
                            send.setCmd(ClientCmd.CLOSE);
                            sendMsg(clientMessage.getData(), send);
                            clientChannels.get(clientMessage.getData()).close();
                            group.remove(clientChannels.get(clientMessage.getData()));
                        }
                        group.add(channelHandlerContext.channel());
                        channelHandlerContext.channel().attr(AttributeKey.valueOf("boxId")).set(clientMessage.getData());
                        clientChannels.put(clientMessage.getData(), channelHandlerContext.channel());
                        log.info("+++++++++++++++当前连接数量:{}，group连接数量:{}", clientChannels.size(), group.size());
//                        ProcessBase.getIsProcessings().put(clientMessage.getData(), Boolean.FALSE);
                        send.setCmd(ClientCmd.SIGN_IN);
                        send.setData("SIGN_OK");
                        queueMap.put(clientMessage.getData(), new ConcurrentLinkedQueue<>());
                        sendMsg(clientMessage.getData(), send);
//                        initData(clientMessage.getData());
                    }else {
                        send.setCmd(ClientCmd.ERROR);
                        send.setData("SIGN_IN:岗亭ID不能为空");
                        String sendMsg = JSONUtil.toJsonStr(send);
                        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(sendMsg);
                        channelHandlerContext.channel().writeAndFlush(textWebSocketFrame);
                    }
                    break;
                case ClientCmd.SIGN_OUT:
                    clientChannels.get(boxId).close();
                    queueMap.remove(boxId);
                    break;
                case ClientCmd.PAGE_REFRESH:
                    log.info("+++++++++++++++页面刷新：{}",clientMessage.getData());
//                    ProcessBase.getIsProcessings().put(clientMessage.getData(), Boolean.FALSE);
                    break;
                case ClientCmd.RE_CONNECTION:
                    if(StrUtil.isNotBlank(clientMessage.getData())){
//                        ProcessBase.getIsProcessings().put(clientMessage.getData(), Boolean.FALSE);
                        channelHandlerContext.channel().attr(AttributeKey.valueOf("boxId")).set(clientMessage.getData());
                        clientChannels.put(clientMessage.getData(), channelHandlerContext.channel());
                        if (!queueMap.containsKey(clientMessage.getData())) {
                            queueMap.put(clientMessage.getData(), new ConcurrentLinkedQueue<>());
//                            ProcessBase.getIsProcessings().put(clientMessage.getData(), Boolean.FALSE);
                        }
                        group.add(channelHandlerContext.channel());
                        send.setCmd(ClientCmd.RE_CONNECTION);
                        send.setData("CONNECTION_OK");
                        sendMsg(clientMessage.getData(), send);
//                        initData(clientMessage.getData());
                    }else {
                        send.setCmd(ClientCmd.ERROR);
                        send.setData("RE_CONNECTION:岗亭ID不能为空");
                        String sendMsg = JSONUtil.toJsonStr(send);
                        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(sendMsg);
                        channelHandlerContext.channel().writeAndFlush(textWebSocketFrame);
                    }
                    break;
                case ClientCmd.HEARTBEAT:
                    if (clientChannels.containsKey(boxId)){
                        send.setCmd(ClientCmd.HEARTBEAT);
                        send.setData("HEARTBEAT_OK");
                        sendMsg(boxId, send);
                    }else {
                        send.setCmd(ClientCmd.HEARTBEAT);
                        send.setData("HEARTBEAT_FAILED");
                        String sendMsg = JSONUtil.toJsonStr(send);
                        TextWebSocketFrame sendMsgFrame = new TextWebSocketFrame(sendMsg);
                        channelHandlerContext.channel().writeAndFlush(sendMsgFrame);
                        channelHandlerContext.channel().close();
                        group.remove(channelHandlerContext.channel());
                    }
                    break;
                case ClientCmd.MONITOR_GATE_LIST:
                    JSONArray jsonArray = JSONUtil.parseArray(clientMessage.getData());
                    List<String> gateIds = jsonArray.toList(String.class);
                    if (null != gateIds && !gateIds.isEmpty()) {
                        gateList.put(boxId, gateIds);
                    }
                    break;
                default:
                    return;
            }
        } catch (Exception e) {
            log.error("+++++++++++++++岗亭id：{}，处理推送数据发生异常：{}" ,boxId);
        }
    }

    /**
     * 判断当前通道是否需要推送数据
     *
     * @param boxId
     * @param gateId
     * @return
     */
    public static boolean checkGate(String boxId, String gateId) {
        if (gateList.containsKey(boxId)) {
            List<String> gateIds = gateList.get(boxId);
            for (String item : gateIds) {
                if (item.equalsIgnoreCase(gateId)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 向岗亭推送消息
     *
     * @param boxId   岗亭ID
     * @param message 消息对象
     * @throws
     */
    public static void sendMsg(String boxId, ClientMessage message) throws Exception {
        try {
//            if (clientChannels.containsKey(boxId)) {
//                if (clientChannels.get(boxId).isActive()){
//                    if (!message.getCmd().equalsIgnoreCase(ClientCmd.HEARTBEAT)){
//                        log.info("+++++++++++++推送岗亭数据岗亭id：{},{}" , boxId ,clientChannels.get(boxId).remoteAddress().toString());
//                        log.info("+++++++++++++推送消息内容类型：{}", message.getCmd());
//                    }
//                    String msg = JSONUtil.toJsonStr(message);
//                    TextWebSocketFrame send = new TextWebSocketFrame(msg);
//                    clientChannels.get(boxId).writeAndFlush(send);
//                }else {
//                    if (!message.getCmd().equalsIgnoreCase(ClientCmd.HEARTBEAT)){
//                        log.info("+++++++++++++当前岗亭和服务端连接不是活跃状态，未推送数据：" + boxId);
//                    }
//                }
//            }else {
//                if (!message.getCmd().equalsIgnoreCase(ClientCmd.HEARTBEAT)){
//                    log.info("+++++++++++++岗亭未连接到服务端，未推送数据：" + boxId);
//                }
//            }
            if (clientChannels.containsKey(boxId)) {
                TextWebSocketFrame send = new TextWebSocketFrame(boxId);
                    clientChannels.get(boxId).writeAndFlush(send);
            }
        } catch (Exception e) {
            log.error("+++++++++++++++岗亭id：{}，推送数据发生异常：{}" ,boxId);
        }
    }



    /**
     * 向岗亭推送消息(队列模式)
     *
     * @param boxId
     * @param message
     */
//    public static void sendQueueMsg(String boxId, ClientMessage<ResultDataVO> message) {
//        try {
//            if (queueMap.containsKey(boxId)) {
//                Queue<ClientMessage<ResultDataVO>> queue = queueMap.get(boxId);
//                Iterator<ClientMessage<ResultDataVO>> dd = queue.iterator();
//                while (dd.hasNext()) {
//                    ClientMessage<ResultDataVO> item = dd.next();
//                    ResultDataVO resultDataVO = item.getData();
//                    if (resultDataVO.getGateId().equalsIgnoreCase(message.getData().getGateId())) {
//                        dd.remove();
//                    }
//                }
//                queue.offer(message);
//                sendNextMsg(boxId);
//            }else {
//                log.info("+++++++++++++++该岗亭无队列对象，岗亭ID：{}",boxId);
//                queueMap.put(boxId,new ConcurrentLinkedQueue<>());
//            }
//        } catch (Exception e) {
//            log.error("+++++++++++++++向岗亭推送消息(队列模式)岗亭id：{}，发生异常：{}" ,boxId);
//        }
//    }

    /**
     * 发送队列中的下一条数据
     *
     * @param boxId
     */
//    public static void sendNextMsg(String boxId) {
//        if (queueMap.containsKey(boxId)) {
//            Queue<ClientMessage<ResultDataVO>> queue = queueMap.get(boxId);
//            if (!queue.isEmpty()) {
//                if (ProcessBase.getIsProcessings().containsKey(boxId) && !ProcessBase.getIsProcessings().get(boxId)) {
//                    log.info("+++++++++++++++从队列中取出数据推送给岗亭:" + boxId);
//                    ClientMessage<ResultDataVO> message = queue.poll();
//                    ProcessBase.getProcessingPlateNumbers().put(boxId, message.getData().getPlateNumber());
//                    sendMsg(boxId, message);
//                    ProcessBase.getIsProcessings().put(boxId, true);
//                }else {
//                    log.info("+++++++++++++++当前岗亭正在处理数据中，无需推送数据:" + boxId);
//                }
//            }
//        }
//    }

    /**
     * 删除队列中线上支付完成的车辆消息
     *
     * @param boxId
     * @param plateNumber
     */
//    public static void excludePaySuccessMsg(String boxId, String plateNumber) {
//        if (queueMap.containsKey(boxId)) {
//            Queue<ClientMessage<ResultDataVO>> queue = queueMap.get(boxId);
//            Iterator<ClientMessage<ResultDataVO>> dd = queue.iterator();
//            while (dd.hasNext()) {
//                ClientMessage<ResultDataVO> item = dd.next();
//                ResultDataVO resultDataVO = item.getData();
//                if (resultDataVO.getPlateNumber().equalsIgnoreCase(plateNumber)) {
//                    dd.remove();
//                }
//            }
//            //判断队列是否需要推送数据给岗亭
//            if (plateNumber.equalsIgnoreCase(ProcessBase.getProcessingPlateNumbers().get(boxId))) {
//                log.info("+++++++++++++++当前岗亭：{}，正在处理该车牌：{}",boxId,plateNumber);
//                ProcessBase.getIsProcessings().put(boxId, false);
//                sendNextMsg(boxId);
//            } else {
//                log.info("+++++++++++++++当前岗亭：{}，未在处理该车牌：{}",boxId,plateNumber);
//                sendNextMsg(boxId);
//            }
//        }
//    }
}
