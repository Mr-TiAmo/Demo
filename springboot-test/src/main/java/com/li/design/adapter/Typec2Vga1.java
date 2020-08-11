package com.li.design.adapter;

/**
 * @program: Demo
 * @description: 类的适配器模式
 *                  通过继承特性来实现适配器功能
 * @author: li
 * @create: 2020-08-01 13:42
 **/
public class Typec2Vga1 extends Phone implements Vga {
    @Override
    public void vgaInterface() {
        typecPhone();
        System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
    }
}
