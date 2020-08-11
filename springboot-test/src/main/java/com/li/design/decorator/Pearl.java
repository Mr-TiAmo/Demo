package com.li.design.decorator;

/**
 * @program: Demo
 * @description:  具体装饰角色（Concrete Decorator）它是具体的装饰类，是Decorator的子类，当然也是Component的子类。它主要就是定义具体的装饰功能
 * @author: li
 * @create: 2020-07-31 14:50
 **/
public class Pearl extends DosingDecorator {
    public Pearl(TeaComponent tea) {
        super(tea);
    }

    @Override
    public String desc() {
        String desc = super.desc();
        // 处理 自己的 业务
        desc += "+珍珠";
        return desc;
    }

    @Override
    public double price() {
        double price = super.price();
        // 处理 自己的 业务
        price = price + 2;
        return price;
    }
}
