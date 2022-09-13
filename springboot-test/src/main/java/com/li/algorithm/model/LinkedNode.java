package com.li.algorithm.model;

/**
 * @program: Test
 * @description:
 * @author: li
 * @create: 2021-03-23 09:29
 **/
public class LinkedNode {
    public int val;
    public LinkedNode pre;
    public LinkedNode next;
    public LinkedNode head;
    public LinkedNode tail;
    public LinkedNode() {}

    public LinkedNode(int val) {
        this.val = val;
    }

    public LinkedNode(int val, LinkedNode next) {
        this.val = val;
        this.next = next;
        //2
    }

}
