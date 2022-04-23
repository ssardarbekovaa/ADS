package com.aqaru.java;

import java.util.ArrayList;
import java.util.List;

public class MyStack<T> {
    private List<T> arr;
    private int index;

    public MyStack() {
        arr = new ArrayList<>();
        index = -1;
    }

    public List<T> getMyStack() {
        return arr;
    }

    public int getIndex() {
        return index;
    }

    public void push(T item) {
        arr.add(item);
        index += 1;
    }

    public T peek() {
        if (arr.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }

        return arr.get(index);
    }

    public void pop() {
        if (arr.isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        arr.remove(index);
        index--;
    }


    public void print() {
        if (arr.isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        for (T element : arr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        stack.push(100);
        stack.push(222);
        stack.push(388);

        System.out.println("Calling peek... " + stack.peek());
        stack.pop();
        System.out.println("Calling peek after pop... " + stack.peek());
        System.out.println();
    }
}

