package com.li.design.decorator;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-07-31 14:53
 **/
public class TestDecorator {

    public static void main(String[] args) {
        TeaComponent tea;
        tea = new Coffee();
        System.out.println(tea.desc() + "》》》》》" + tea.price());

        tea = new Pearl(new MilkTea());
        System.out.println(tea.desc() + "》》》》》" + tea.price());

        tea = new Pudding(new MilkTea());
        System.out.println(tea.desc() + "》》》》》" + tea.price());
        tea = new Pearl(tea);
        System.out.println(tea.desc() + "》》》》》" + tea.price());
    }
}
