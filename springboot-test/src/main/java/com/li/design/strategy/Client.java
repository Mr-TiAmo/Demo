package com.li.design.strategy;

/**
 * @program: demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-15 18:43
 **/
public class Client {
    public static void main(String[] args) {

        StrategyOne strategyOne = new StrategyOne();
        StrategyTwo strategyTwo = new StrategyTwo();
        new StrategyContext(strategyOne).execute();
        new StrategyContext(strategyTwo).execute();
    }
}
