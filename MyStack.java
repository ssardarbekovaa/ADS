package com.assignment.stack;

import java.util.ArrayList;
import java.util.List;

public class MyStack<T> {
    private final List<T> stack;
    private int last;

    public MyStack() {
        this.stack = new ArrayList<>();
        this.last = -1;
    }

    public void push(T item) {
        stack.add(item);
        last++;
    }

    public void pop() {
        if (!isEmpty()) {
            stack.remove(last);
            last--;
        } else {
            System.out.println("Stack is empty!");
        }
    }

    public T peek() {
        if (!isEmpty()) {
            return stack.get(last);
        } else {
            System.out.println("Stack is empty!");
            throw new IllegalStateException();
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void printStack() {
        if (!isEmpty()) {
            for (T element : stack) {
                System.out.print(element + " ");
            }
            System.out.println();
        } else {
            throw new IllegalStateException();
        }
    }
}
