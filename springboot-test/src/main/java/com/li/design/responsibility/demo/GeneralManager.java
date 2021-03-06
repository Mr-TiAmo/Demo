package com.li.design.responsibility.demo;

import java.util.Objects;

/**
 * @program: demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-16 16:52
 **/
public class GeneralManager extends Handler{
    @Override
    public String handleFeeRequest(String user, double fee) {
        String str = "";
        if (fee >= 1000) {
            if ("张三".equals(user)) {
                str += "GeneralManager批准了" + user + fee + "元的申请费用";
            } else {
                str += "GeneralManager驳回了" + user + fee + "元的申请费用";
            }
        } else {
            if(Objects.nonNull(this.successor)) {
                return this.successor.handleFeeRequest(user, fee);
            }
        }
        return str;
    }
}
