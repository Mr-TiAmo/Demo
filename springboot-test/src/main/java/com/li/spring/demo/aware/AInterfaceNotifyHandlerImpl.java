package com.li.spring.demo.aware;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2021-09-08 15:43
 **/
@Service("aInterfaceNotifyHandlerImpl")
public class AInterfaceNotifyHandlerImpl implements InterfaceNotifyHandler{
    @Override
    public void handle(PolicyVo policyVo, PolicyNotifyService policyNotifyService) throws Exception {
        System.out.println("-------------B------------------");
    }

    @Override
    public List<Long> getIds() {
        return Lists.newArrayList(1L);
    }
}
