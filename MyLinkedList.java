package com.aqaru.java;

import java.util.Objects;

public class MyLinkedList<T> implements MyList<T> {
    private static class MyNode<T> {
        T data;
        MyNode<T> next, prev;

        MyNode(T data) {
            this.data = data;
        }
    }

    private int length = 0;
    private MyNode<T> head, tail;

    public MyLinkedList() {
    }

    @Override
    public void add(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    @Override
    public void add(T item, int index) {
        if (index > length + 1) {
            System.out.println("Index out of range");
            return;
        }
        MyNode<T> curr = head;

        for (int i = 1; i < index; i++) {
            curr = curr.next;
        }

        MyNode<T> temp = curr.next;
        curr.next = new MyNode<T>(item);
        (curr.next).next = temp;

        length++;
    }

    @Override
    public boolean remove(T item) {
        if (head == null)
            throw new RuntimeException("cannot delete");

        if (head.data.equals(item))
        {
            head = head.next;
            return true;
        }

        MyNode<T> curr = head;
        MyNode<T> prev = null;

        while (curr != null && !curr.data.equals(item))
        {
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) {
            System.out.println("This element is not in list");
            return false;
        }

        Objects.requireNonNull(prev).next = curr.next;
        return true;
    }

    @Override
    public T remove(int index) {
        if (index > length + 1 || index < 0) {
            System.out.println("Index out of range");
            return null;
        }

        MyNode<T> prev = head;

        for (int i = 1; i < index; i++) {
            prev = prev.next;
        }

        MyNode<T> temp = prev.next;
        prev.next = temp.next;
        length--;

        return temp.data;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        length = 0;
    }

    public T get(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException("index should be positive and less than size");

        MyNode<T> temp = head;

        while (index != 0) {
            temp = temp.next;
            index--;
        }

        return temp.data;
    }

    @Override
    public int indexOf(Object o) {
        MyNode<T> temp = head;

        for (int i = 0; i < length; i++) {
            if (temp.data.equals(o)) {
                return i;
            }

            temp = temp.next;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        MyNode<T> temp = head;

        int index = -1;

        for (int i = 0; i < length; i++) {
            if (temp.data.equals(o)) {
                index = i;
            }

            temp = temp.next;
        }

        return index;
    }

    @Override
    public void sort() {
    }

    public int size() {
        return length;
    }

    @Override
    public boolean contains(Object o) {
        MyNode<T> curr = head;

        for (int i = 0; i < length; i++) {
            if (curr.data.equals(o)) {
                return true;
            }

            curr = curr.next;
        }

        return false;
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();

        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);
        linkedList.add(4, 1);

        System.out.println(linkedList.get(1) + " " + linkedList.get(2));
        System.out.println(linkedList.lastIndexOf(3));
        System.out.println(linkedList.indexOf(2));
        System.out.println(linkedList.contains(1));
        System.out.println(linkedList.remove(2));
        System.out.println(linkedList.size());

        System.out.println();
        System.out.println(linkedList.get(0) + " " + linkedList.get(1) + " " + linkedList.get(2));
    }
}
