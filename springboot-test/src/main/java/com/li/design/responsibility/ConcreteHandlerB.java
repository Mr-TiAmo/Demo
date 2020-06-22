package com.li.design.responsibility;

/**
 * @program: demo
 * @description: 具体处理者(ConcreteHandler)角色
 * @author: 栗翱
 * @create: 2020-06-16 16:08
 **/
public class ConcreteHandlerB extends Handler {

    @Override
    public void handlerRequest() {
        // 根据某些条件来判断是否属于自己处理的职责范围,下面這句話只是个示意
        Boolean requirement = false;
        if (requirement) {
            //如果属于自己处理的职责范围，就在这里处理请求
            System.out.println("ConcreteHandlerB 的业务代码");
        } else {
            // 如果不属于自己处理的职责范围，那就判断是否还有后继的职责对象
            // 如果有，就转发请求给后继的职责对象
            // 如果没有，什么都不做，自然结束
            if (null != this.successor) {
                this.successor.handlerRequest();
            }
        }

    }
}
