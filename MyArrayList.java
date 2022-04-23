package com.aqaru.java;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements MyList<T>{
    private Object[] arr;
    private int length = 0;
    private int capacity = 3;

    public MyArrayList() {
        arr = new Object[capacity];
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o) != -1) {
            return true;
        }

        return false;
    }

    @Override
    public void add(T item) {
        if (length == capacity)
            increaseCapacity();

        arr[length++] = item;
    }

    @Override
    public void add(T item, int index) {
        if (index >= length) {
            System.out.println("Index out of range");
            return;
        }

        add(item);

        for (int i = length - 1; i > index; i--) {
            arr[i] = arr[i - 1];
        }

        arr[index] = item;
    }

    @Override
    public boolean remove(T item) {
        int index = indexOf(item);

        if (index >= 0) {
            System.arraycopy(arr, index + 1, arr, index, length - index - 1);
            length--;
            arr[length] = null;
            return true;
        }

        return false;
    }

    @Override
    public T remove(int index) {
        if (index >= length) {
            System.out.println("Index is out of range");
            return null;
        }

        Object element = arr[index];

        int previousIndex = index;
        arr[index] = null;

        while (previousIndex < length) {
            arr[previousIndex] = arr[previousIndex + 1];
            arr[previousIndex + 1] = null;
            previousIndex++;
        }

        length -= 1;

        return (T) element;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            arr[i] = null;
        }

        length = 0;
        capacity = 3;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (arr[i].equals(o)) {
                return i;
            }
        }

        System.out.println("Did not fount element");
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (arr[i].equals(o)) {
                return i;
            }
        }

        System.out.println("Did not fount element");
        return -1;
    }

    public void sort() {
    }


    private void increaseCapacity() {
        capacity = 2 * capacity;
        Object[] old = arr;
        arr = new Object[capacity];

        for (int i = 0; i < old.length; i++) {
            arr[i] = old[i];
        }
    }

    @Override
    public T get(int index) {
        return (T) arr[index];
    }

    @Override
    public int size() {
        return length;
    }

    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>();

        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(4, 1);

        System.out.println(arrayList.get(1) + " " + arrayList.get(2));
        System.out.println(arrayList.lastIndexOf(3));
        System.out.println(arrayList.indexOf(2));
        System.out.println(arrayList.contains(1));
        System.out.println(arrayList.remove(2));
        System.out.println(arrayList.size());

        System.out.println();
        System.out.println(arrayList.get(0) + " " + arrayList.get(1) + " " + arrayList.get(2));
    }
}
