package com.li.algorithm;

import com.li.algorithm.model.Node;

/**
 * @program: demo
 * @description:  单向链表
 * @author: li
 * @create: 2022-09-07 10:50
 **/
public class SingleLinkedList<T> {
    private int size;
    private final Node<T> head = new Node<>();
    private Node<T> tail = head;


    public void addHead(T data) {
        Node<T> newNode = new Node<>(data);
//        Node<T> temp = head.next;
//        head.next = newNode;
//        newNode.next = temp;
        // 尾节点初始化
        if (head.next == null) {
            tail = newNode;
        }
        newNode.next = head.next;
        head.next = newNode;
        size++;

    }

    public void addTail(T data) {
        Node<T> newNode = new Node<>(data);
        tail.next = newNode;
        tail = newNode;
        size++;

    }

    public void foreach() {
        Node<T> temp = head;
        if (size == 0) {
            System.out.println("链表为空！");
            return;
        }
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp.data);
        }
    }

    public Node<T> getIndex(Integer index) {
        if (index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void removeNode(Integer index) {
        if (index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        // 需要删除的节点
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        if (index == size - 1) {
            tail = temp;
        }
        size--;
    }


    public static void main(String[] args) {
        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
//        linkedList.addTail(4);
//        linkedList.addTail(5);
//        linkedList.addTail(6);

        linkedList.addHead(1);
        linkedList.addHead(2);
        linkedList.addHead(3);

//        linkedList.addHead(1);
//        linkedList.addTail(4);
//        linkedList.addHead(2);
//        linkedList.addTail(5);
//        linkedList.addHead(3);
//        linkedList.addTail(6);


        linkedList.addTail(4);
        linkedList.addTail(5);
        linkedList.addTail(6);

        linkedList.foreach();
        System.out.println("---------------------------");
        Node<Integer> index = linkedList.getIndex(0);
        System.out.println(index.data);
        System.out.println("---------------------------");
        linkedList.removeNode(5);
        linkedList.foreach();
        System.out.println("---------------------------");
    }
}
