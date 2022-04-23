package com.aqaru.java;

import java.util.ArrayList;
import java.util.List;

public class MyHeap<T extends Comparable<T>> {
    private List<T> arr;

    public MyHeap() {
        arr = new ArrayList<>();
    }

    public List<T> getHeap() {
        return arr;
    }

    public void add(T item) {
        arr.add(arr.size(), item);
        heapify(arr.size() - 1);
    }

    public T removeRoot() {
        T temp = arr.get(1);
        arr.set(1, arr.get(arr.size() - 1));
        heapify(1);

        return temp;
    }

    private void heapify(int index) {
        T temp = arr.get(index);

        while (temp.compareTo(arr.get(index / 2)) > 0) {
            arr.add(index, arr.get(index / 2));
            index = index / 2;
        }

        arr.add(index, temp);
    }

    public static void main(String[] args) {
        MyHeap<Integer> heap = new MyHeap<>();

        heap.add(1);
        heap.add(2);
        heap.add(2);

        heap.removeRoot();
    }

}
