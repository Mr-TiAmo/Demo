package com.li.lambda;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-08-11 09:23
 **/
public class test4 {

    public static void main(String[] args) {

        IntStream.range(0, 10).forEach(i -> System.out.println(i));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Stream.iterate(0, i -> i + 2).limit(11).forEach(i -> System.out.println(i));
    }
}
