/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.li.net.netty;

/**
 * @Author: Administrator
 * @Date: 2020-02-05 16:59
 * @Version 1.0
 */
public interface ClientCmd {
    /**
     * 服务器主动断开连接
     */
    String CLOSE = "CLOSE";
    /**
     * 岗亭注册
     */
    String SIGN_IN = "SIGN_IN";
    /**
     * 岗亭注销
     */
    String SIGN_OUT = "SIGN_OUT";

    /**
     * 岗亭重连
     */
    String RE_CONNECTION = "RE_CONNECTION";
    /**
     * 页面刷新
     */
    String PAGE_REFRESH = "PAGE_REFRESH";

    /**
     * 发生错误
     */
    String ERROR = "ERROR";

    /**
     * 岗亭心跳
     */
    String HEARTBEAT = "HEARTBEAT";

    /**
     * 岗亭通知
     */
    String BOX_NOTIFY = "BOX_NOTIFY";

    /**
     * 线上支付
     */
    String ONLINE_PAY = "ONLINE_PAY";

    /**
     * 进出记录列表
     */
    String IO_RECORD_LIST = "IO_RECORD_LIST";

    /**
     * 页面监控通道列表
     */
    String MONITOR_GATE_LIST = "MONITOR_GATE_LIST";

    /**
     * 车辆停车信息 + 收费信息
     */
    String PARK_PAYMENT = "PARK_PAYMENT";

    /**
     * 车辆图片
     */
    String PARK_IMG = "PARK_IMG";


    /**
     * 剩余车位信息
     */
    String CAR_BERTH_INFO = "CAR_BERTH_INFO";

    /**
     * 文字消息提醒
     */
    String TEXT_WARN_INFO = "TEXT_WARN_INFO";

    /**
     * 设备状态信息
     */
    String DEVICE_STATUS_INFO = "DEVICE_STATUS_INFO";

    /**
     * 数据库状态(0连接正常，1连接失败)
     */
    String DATABASE_STATUS = "DATABASE_STATUS";
}
