package com.aqaru.java;

import java.util.ArrayList;
import java.util.List;

public class MyQueue<T> {
    private List<T> arr;
    private int front;
    private int rear;

    public MyQueue() {
        arr = new ArrayList<>();
        front = -1;
        rear = -1;
    }

    public List<T> getQueue() {
        return arr;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public void enqueue(T item) {
        if (arr.isEmpty()) {
            front++;
            rear++;
            arr.add(item);
            return;
        }

        front++;

        if (arr.size() > front) {
            arr.set(front, item);
        } else {
            arr.add(item);
        }

    }

    public void dequeue() {
        if (arr.isEmpty()) {
            System.out.println("Queue is empty");
        } else if (rear != front) {
            rear += 1;
        } else {
            rear = -1;
            front = -1;
        }
    }

    public T front() {
        if (front == -1) {
            System.out.println("Queue is empty");
            return null;
        }

        return arr.get(front);
    }

    public T rear() {
        if (rear == -1) {
            System.out.println("Queue is empty");
            return null;
        }

        return arr.get(rear);
    }

    public void print() {
        if (arr.isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            for (int i = rear; i < front; i++) {
                System.out.print(arr.get(i) + "\t");
            }
            System.out.print(arr.get(front));
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();

        queue.enqueue(100);
        queue.enqueue(222);
        queue.enqueue(388);

        queue.print();

        queue.dequeue();

        queue.print();

        System.out.println(queue.getFront());
        System.out.println(queue.getRear());
    }
}
