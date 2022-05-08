package com.structures.bst;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        if (get(key) != null) {
            return;
        }

        root = insertInTree(root, key, val);
    }

    Node insertInTree(Node root, K key, V val) {
        if (root == null) {
            return new Node(key, val);
        }
        if (key.compareTo(root.key) < 0) {
            root.left = insertInTree(root.left, key, val);
        } else {
            root.right = insertInTree(root.right, key, val);
        }

        return root;
    }


    public V get(K key) {
        Node current = root;

        while (current != null && !current.key.equals(key)) {
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current != null) {
            return current.val;
        } else {
            return null;
        }
    }

    public void delete(K key) {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;

        while (current.key != key) {
            parent = current;

            if (current.key.compareTo(key) > 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return;
            }
        }

        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            Node temporary = current;

            if (current == root) {
                root = temporary;
            } else if (isLeftChild) {
                parent.left = temporary;
            } else {
                parent.right = temporary;
            }
            temporary.left = current.left;
        }
    }

    public Iterable<K> iterator() {
        return (Iterable<K>) new BinarySearchTreeIterator<K>(this.root);
    }

    class BinarySearchTreeIterator<K extends Comparable<? super K>> implements Iterator<K> {

        private Stack<Node> stack = new Stack<>();
        private Node current;
        private Node pending;

        BinarySearchTreeIterator(Node root) {
            if (root == null) {
                throw new IllegalArgumentException();
            }
            current = root;
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            pending = stack.pop();
            current = pending.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            return (K) pending.key;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();

        bst.put(1, 2);
        bst.put(2, 4);
        bst.put(5, 3);

        System.out.println(bst.get(5));
        bst.delete(2);
        int i = 0;
    }
}
