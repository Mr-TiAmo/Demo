package com.li.jdk.agent.staticproxy;

/**
 * @program: demo
 * @description: 静态代理
*                模式     需要定义接口或者父类，被代理对象与代理对象一起实现相同的接口或者继承相同的父类
 *               优点     不改变已有类的情况下，进行扩展，在目标方法前后切入操作
 *               缺点     如果有多个需要扩展的类，则需要建立多个代理对象
 *
 * @author: li
 * @create: 2021-09-17 10:22
 **/
public class ZhangSanProxyDemo implements Person{

    private ZhangSan zhangSan;

    public ZhangSanProxyDemo(ZhangSan zhangSan) {
        this.zhangSan = zhangSan;
    }

    @Override
    public void print() {
        System.out.println("张三初始化...");
        zhangSan.print();
        System.out.println("张三销毁...");
    }

    public static void main(String[] args) {
        ZhangSan zhangSan = new ZhangSan();
        ZhangSanProxyDemo zhangSanProxy = new ZhangSanProxyDemo(zhangSan);
        zhangSanProxy.print();

        LiSi liSi = new LiSi();
        LiSiProxyDemo liSiProxy = new LiSiProxyDemo(liSi);
        liSiProxy.print();
    }
}
