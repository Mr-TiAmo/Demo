package com.li.design.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Demo
 * @description:  对象的适配器模式
 *                  通过组合方式来实现适配器功能
 * @author: li
 * @create: 2020-08-01 13:48
 **/
public class Typec2Vga2 {

    private Phone phone;

    public Typec2Vga2(Phone phone) {
        this.phone = phone;
    }

    public void vgaInterface() {
        phone.typecPhone();
        System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
    }
}
