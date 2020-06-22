package com.li.design.responsibility;

/**
 * @program: demo
 * @description: 客户端(Client)角色：职责链的客户端，向链上的具体处理对象提交请求，让职责链负责处理
 * @author: 栗翱
 * @create: 2020-06-16 16:17
 **/
public class Client {
    public static void main(String[] args) {

        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        handlerA.setSuccessor(handlerB);
        handlerA.handlerRequest();
    }
}
