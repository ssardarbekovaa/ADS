package com.aqaru;

import java.util.HashMap;
import java.util.Map;

public class Vertex<V> {
    private final V data;
    private final Map<Vertex<V>, Double> adjacentVertices;

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public V getData() {
        return data;
    }

    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void addAdjacentVertex(Vertex<V> targetVertex, Double weight) {
        if(!data.equals(targetVertex.getData())) {
            adjacentVertices.put(targetVertex, weight);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return data.equals(vertex.data);
    }
}
