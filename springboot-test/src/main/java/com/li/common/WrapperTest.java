package com.li.common;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-02-24 16:55
 **/
public class WrapperTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long h = 3L;

        System.out.println(c.equals(d));
        System.out.println(f.equals(e));
        System.out.println(c == d);
        System.out.println(f == e);
        System.out.println(h == (a+b));
        System.out.println(h.equals(a+b));
    }
}
