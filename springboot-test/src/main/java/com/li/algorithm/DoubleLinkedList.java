package com.li.algorithm;

import com.li.algorithm.model.Node;

/**
 * @program: demo
 * @description: 双向链表
 * @author: li
 * @create: 2022-09-08 13:50
 **/
public class DoubleLinkedList<T> {
    private Integer size = 0;
    private Node<T> head = new Node<>();
    private Node<T> tail = new Node<>();

    public DoubleLinkedList() {
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 头插法  head 1 2
     *
     * @param newNote
     */
    public void addHead(Node<T> newNote) {
        newNote.pre = head;
        newNote.next = head.next;
        head.next.pre = newNote;
        head.next = newNote;
        size++;
    }

    /**
     * 尾插法
     *
     * @param newNote
     */
    public void addTail(Node<T> newNote) {
        newNote.next = tail;
        newNote.pre = tail.pre;
        tail.pre.next = newNote;
        tail.pre = newNote;
        size++;
    }

    public boolean moveHeadByNode(Node<T> newNote) {
        if (newNote == null) {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.data == null) {
                    removeByNode(x);
                    return true;
                }
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next) {
                if (newNote.data.equals(x.data)) {
                    removeByNode(x);
                    return true;
                }
            }
        }
        return false;
    }

    public T removeByNode(Node<T> node) {
        T data = node.data;
        if (node.pre == null) {
            head = node.next;
        } else {
            node.pre.next = node.next;
            node.pre = null;
        }

        if (node.next == null) {
            tail = node.pre;
        } else {
            node.next.pre = node.pre;
            node.next = null;
        }

        node.data = null;
        size--;
        return data;
    }

    public boolean exist(Node<T> newNote) {
        Node<T> tempNode = head;
        while (tempNode.next != null) {
            if (newNote.data.equals(tempNode.next.data)) {
                return true;
            }
            tempNode = tempNode.next;
        }
        return false;
    }

    public void removeHead() {
        if (size > 0) {
            head.next.next.pre = head;
            head.next = head.next.next;
            size--;
        }
    }

    public T removeTail() {
        if (size > 0) {
            Node<T> pre = tail.pre;
            tail.pre.pre.next = tail;
            tail.pre = tail.pre.pre;
            size--;
            return pre.data;
        }
        return null;
    }

    public void foreach() {
        Node<T> tempNode = head;
        while (tempNode.next != null && tempNode.next.data != null) {
            System.out.println(tempNode.next.data);
            tempNode = tempNode.next;
        }
    }

    public void foreachReverse() {
        Node<T> tempNode = tail;
        while (tempNode.pre != null && tempNode.pre.data != null) {
            System.out.println(tempNode.pre.data);
            tempNode = tempNode.pre;
        }
    }


    public static void main(String[] args) {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();
        doubleLinkedList.addHead(new Node<>(1));
        doubleLinkedList.addHead(new Node<>(2));
        doubleLinkedList.addHead(new Node<>(3));

        doubleLinkedList.addTail(new Node<>(4));
        doubleLinkedList.addTail(new Node<>(5));

        doubleLinkedList.foreach();
        System.out.println("--------------------");
        doubleLinkedList.foreachReverse();
        System.out.println("--------------------");
//        doubleLinkedList.removeHead();
        doubleLinkedList.removeTail();
    }
}
