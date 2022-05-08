package com.structures.hashtable;

import java.util.Map;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalStateException();
        }

        int index = hash(key);

        if (chainArray[index] == null) {
            chainArray[index] = new HashNode<>(key, value);
        } else {
            HashNode<K, V> temporary = chainArray[index];

            while (temporary.next != null) {
                temporary = temporary.next;
            }

            temporary.next = new HashNode<>(key, value);
        }

        size++;
    }

    public V get(K key) {
        int hash = hash(key);

        if (chainArray[hash] != null) {
            HashNode<K, V> temporary = chainArray[hash];

            while (!temporary.key.equals(key)
                    && temporary.next != null) {
                temporary = temporary.next;
            }

            return temporary.value;
        }

        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        if (chainArray[index] == null) {
            throw new IllegalStateException();
        }
        if (chainArray[index].key.equals(key)) {
            HashNode<K, V> temp = chainArray[index];
            chainArray[index] = chainArray[index].next;
            size--;
            return temp.value;
        }

        HashNode<K, V> previous = chainArray[index];
        HashNode<K, V> current = previous.next;

        while (current != null && !current.key.equals(key)) {
            current = current.next;
            previous = current;
        }

        if (current != null) {
            previous.next = current.next;
            size--;
        }

        return null;
    }

    public boolean contains(V value) {
        for (int i = chainArray.length - 1; i > 0; i--) {
            for (HashNode<K, V> element = chainArray[i]; element != null; element = element.next) {
                if (element.value.equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            if (node.value.equals(value)) {
                return node.key;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        MyHashTable<String, Integer> hashTable = new MyHashTable<>();

        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);
        hashTable.put("D", 4);

        System.out.println(hashTable.get("C"));
        System.out.println(hashTable.getKey(3));
        hashTable.remove("B");
        System.out.println(hashTable.contains(4));
        System.out.println(hashTable.contains(2));
    }
}
