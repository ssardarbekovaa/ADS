package com.aqaru;

import java.util.*;

public class Search<T> {
    protected int count;
    protected Set<Vertex<T>> marked;
    protected Map<Vertex<T>, Vertex<T>> edgeTo;
    protected final Vertex<T> source;

    public Search(Vertex<T> source) {
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(Vertex<T> vertex) {
        return marked.contains(vertex);
    }

    public Iterable<Vertex<T>> pathTo(Vertex<T> sourceVertex) {
        if (!hasPathTo(sourceVertex)) {
            return null;
        } else {
            LinkedList<Vertex<T>> vertices = new LinkedList<>();
            Vertex<T> temp = sourceVertex;

            if (temp != source) {
                do {
                    vertices.push(temp);
                    temp = edgeTo.get(temp);
                } while (temp != source);
                vertices.push(source);
            } else {
                vertices.push(source);
            }

            return vertices;
        }

    }

    public int getCount() {
        return count;
    }
}
