package com.li.jdk.base;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2021-07-02 17:44
 **/
public class FinalTest {
    /**
     * https://blog.csdn.net/a15123837995/article/details/83048772
     *
     * @param args
     */
    public static void main(String[] args) {
        StringBuilder b1 = new StringBuilder("111");
        StringBuilder b2 = b1;
        final StringBuilder b3 = b2;

        System.out.println("b3---" + b3);
        System.out.println("--------------------");

        b1.append("111");
        System.out.println("b3---" + b3);
        System.out.println("--------------------");

        b2.append("222");
        System.out.println("b3---" + b3);
        System.out.println("--------------------");


        b2 = new StringBuilder("new sb2");
        System.out.println("b3---" + b3);
        System.out.println("--------------------");
        System.out.println("b2---" + b2);
        System.out.println("--------------------");

        b2.append("222");
        System.out.println("b3---" + b3);
        System.out.println("--------------------");
        System.out.println("b2---" + b2);
        System.out.println("--------------------");


        final String finalStrA = "str";
        String strA = "str";
        String strB = "str2";
        String strC = finalStrA + "2";
        String strD = strA + "2";
        String strE = "str" + "2";//等同于 finalStrA+"2"

        System.out.println(strB == strC);//输出: true
        System.out.println(strB == strD);//输出: false
        System.out.println(strB == strE);//输出: true

        System.out.println(strB.equals(strC));//输出: true
        System.out.println(strB.equals(strD));//输出: true
        System.out.println(strB.equals(strE));//输出: true

    }

}
