package com.li.design.strategy.demo;

/**
 * @Description:  策略模式接口类
 * @Author:
 * @Date: 2020/4/18 15:48
 */
public interface StrategyService {

    default void init(){
        //通用的一些逻辑
        System.out.println("default方法");
    }


    void test();
}
