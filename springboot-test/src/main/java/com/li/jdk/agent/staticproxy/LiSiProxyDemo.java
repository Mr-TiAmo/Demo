package com.li.jdk.agent.staticproxy;

/**
 * @program: demo
 * @description: 对 lisi类进行代理，在方法执行前后 切入操作
 * @author: li
 * @create: 2021-09-17 10:55
 **/
public class LiSiProxyDemo implements Person{
    private LiSi liSi;

    public LiSiProxyDemo(LiSi liSi) {
        this.liSi = liSi;
    }

    @Override
    public void print() {
        System.out.println("lisi 初始化...");
        liSi.print();
        System.out.println("lisi 销毁...");
    }
}
