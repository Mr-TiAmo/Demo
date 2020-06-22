package com.li.sort;

import java.util.LinkedList;

/**
 * @program: demo
 * @description: 最近最久未使用
 * @author: 栗翱
 * @create: 2020-06-19 14:05
 **/
public class LRUAlgorithm {

    private static int size = 5;

    public static void main(String[] args) {
        int[] num = new int[]{2, 4, 3, 2, 1, 1, 6, 4, 7, 9, 7};
        LinkedList<Integer> list = new LinkedList<>();
        for (int i : num) {
            if (list.contains(i)) {
                int index = list.lastIndexOf(i);
                list.remove(index);
                list.addLast(i);
            } else {
                if (list.size() < size) {
                    list.add(i);
                } else {
                    list.removeFirst();
                    list.addLast(i);
                }
            }
        }
    }
}
