package com.li.design.decorator;

/**
 * @program: Demo
 * @description:  装饰角色（Decorator）是Component的子类，它是具体装饰角色共同实现的抽象类（也可以是接口），
 *                  并且持有一个Component类型的对象引用，它的主要作用就是把客户端的调用委派到被装饰类
 * @author: li
 * @create: 2020-07-31 14:46
 **/
public class DosingDecorator extends TeaComponent {

    /**
     * Component类型的对象引用
     */
    private TeaComponent tea;

    public DosingDecorator(TeaComponent tea) {
        this.tea = tea;
    }

    @Override
    public String desc() {
        return tea.desc();
    }

    @Override
    public double price() {
        return tea.price();
    }
}
