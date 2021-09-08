package com.li.spring.demo.aware;

import java.util.List;

public interface InterfaceNotifyHandler {
    /**
     * 处理通知
     * @param policyVo
     */
    void handle(PolicyVo policyVo, PolicyNotifyService policyNotifyService)throws  Exception;

    /**
     * 产品Ids
     * @return
     */
    List<Long> getIds();
}

