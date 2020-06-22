package com.li.design.responsibility;

/**
 * @program: demo
 * @description: 抽象处理者(Handler)角色：定义职责的接口，通常在这里定义处理请求的方法，
*                如果需要，接口可以定义出一个方法以设定和返回对后继处理者的引用
 * @author: 栗翱
 * @create: 2020-06-16 13:25
 **/
public abstract class Handler {

    /**
     * 持有后继处理器的对象
     */
    protected Handler successor;

    /**
     * 赋值方法，设置后继的处理者对象
     * @param successor
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * 示意处理请求的方法，虽然这个示意方法是没有传入参数的 但实际是可以传入参数的，根据具体需要来选择是否传递参数
     */
    public abstract void handlerRequest();
}
