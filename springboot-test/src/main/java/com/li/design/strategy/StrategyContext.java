package com.li.design.strategy;

/**
 * @program: demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-15 18:42
 **/
public class StrategyContext {
    private Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute()
    {
        //StrategyContext 的 execute 有许多事情要做
        System.out.println("------");

        String result = strategy.run();
        System.out.println(result);

        //后续还有好多操作。。。
        System.out.println("======");
    }
}
