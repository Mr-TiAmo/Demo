/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.li.net.netty;

import lombok.Data;

/**
 * @Author: Administrator
 * @Date: 2020-02-05 16:49
 * @Version 1.0
 */
@Data
public class ClientMessage<T> {
    private String cmd;
    private T data;
}
