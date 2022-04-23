package com.assignment.heap;

import java.util.ArrayList;
import java.util.List;

public class MyHeap<T extends Comparable<T>> {
    private final List<T> heap;

    public MyHeap() {
        this.heap = new ArrayList<>();
    }

    public void add(T item) {
        heap.add(heap.size(), item);
        heapify(heap.size() - 1);
    }

    public T removeRoot(){
        T temporary = heap.get(1);
        heap.set(1, heap.get(heap.size()-1));

        heapify(1);

        return temporary;
    }

    private void heapify(int index) {
        T temporary = heap.get(index);

        while (temporary.compareTo(heap.get(index / 2)) == 1) {
            heap.add(index, heap.get(index / 2));
            index = index / 2;
        }

        heap.add(index, temporary);
    }

}
