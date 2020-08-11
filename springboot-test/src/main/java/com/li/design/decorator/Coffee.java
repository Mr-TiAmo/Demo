package com.li.design.decorator;

/**
 * @program: Demo
 * @description:  具体构件角色（Concrete Component）
 *                  是Component的子类，实现了对应的方法，它就是那个被装饰的类
 * @author: li
 * @create: 2020-07-31 14:45
 **/
public class Coffee extends TeaComponent {
    @Override
    public String desc() {
        return "拿铁";
    }

    @Override
    public double price() {
        return 10;
    }
}
