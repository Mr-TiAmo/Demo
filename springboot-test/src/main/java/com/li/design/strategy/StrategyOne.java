package com.li.design.strategy;

/**
 * @program: demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-15 18:39
 **/
public class StrategyOne implements Strategy {
    @Override
    public String run() {
        return "业务A的逻辑代码";
    }
}
