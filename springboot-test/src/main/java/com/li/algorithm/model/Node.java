package com.li.algorithm.model;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-09-08 13:35
 **/
public class Node<T> {
    public T data;
    public Node<T> next = null;
    public Node<T> pre = null;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}
