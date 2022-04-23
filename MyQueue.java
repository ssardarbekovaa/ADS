package com.assignment.queue;

import java.util.ArrayList;
import java.util.List;

public class MyQueue<T> {
    private final List<T> queue;
    private int front;
    private int rear;

    public MyQueue() {
        queue = new ArrayList<>();
        front = -1;
        rear = -1;
    }

    public T front() {
        if (front == -1) {
            return null;
        }

        return queue.get(front);
    }

    public T rear() {
        if (rear == -1) {
            return null;
        }
        return queue.get(rear);
    }

    public void enqueue(T item) {
        if (isEmptyQueue()) {
            front = 0;
            rear = 0;
            queue.add(item);
        } else {
            front++;

            if (queue.size() > front) {
                queue.set(front, item);
            } else {
                queue.add(item);
            }
        }
    }

    public void dequeue() {
        if (isEmptyQueue()) {
            System.out.println("Queue is empty");
            throw new IllegalStateException();
        } else if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            rear++;
        }
    }

    private boolean isEmptyQueue() {
        return rear == -1 && front == -1;
    }

    public void printQueue() {
        if (isEmptyQueue()) {
            throw new IllegalStateException();
        } else {
            for (int i = rear; i < front; i++) {
                System.out.print(queue.get(i) + " ");
            }

            System.out.print(queue.get(front));

            System.out.println();
        }
    }
}
