package com.aqaru;

import java.util.*;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final List<Vertex<T>> vertices;

    WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        this.vertices = new ArrayList<>();
    }

    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Vertex<T> sourceVertex, Vertex<T> targetVertex, double weight) {
        if (!hasVertex(sourceVertex)) {
            addVertex(sourceVertex);
        }

        if (!hasVertex(targetVertex)) {
            addVertex(targetVertex);
        }

        if (hasEdge(sourceVertex, targetVertex) || sourceVertex.equals(targetVertex)) return;

        if (!undirected) {
            for (int i = 0; i < vertices.size(); i++) {
                Vertex<T> element = vertices.get(i);
                if (element.getData().equals(sourceVertex)
                        && !hasEdge(sourceVertex, targetVertex)) {
                    element.addAdjacentVertex(targetVertex, weight);
                }
            }
        } else {
            for (int i = 0; i < vertices.size(); i++) {
                Vertex<T> element = vertices.get(i);
                if (element.equals(sourceVertex) && !hasEdge(sourceVertex, targetVertex)) {
                    element.addAdjacentVertex(targetVertex, weight);
                }
                if (element.equals(targetVertex) && !hasEdge(targetVertex, sourceVertex)) {
                    element.addAdjacentVertex(sourceVertex, weight);
                }
            }
        }

    }

    public boolean hasVertex(Vertex<T> vertex) {
        for (int i = 0, verticesSize = vertices.size(); i < verticesSize; i++) {
            Vertex<T> element = vertices.get(i);
            if (element.getData().equals(vertex.getData())) {
                return true;
            }
        }

        return false;
    }

    public boolean hasEdge(Vertex<T> destinationFrom, Vertex<T> destinationTo) {
        if (!hasVertex(destinationFrom)) {
            return false;
        }

        for (int i = 0; i < vertices.size(); i++) {
            Vertex<T> element = vertices.get(i);
            if (element.getData().equals(destinationFrom) && element.getAdjacentVertices() != null) {
                for (Vertex<T> elementVertex : element.getAdjacentVertices().keySet()) {
                    if (elementVertex.getData().equals(destinationTo)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public List<Vertex<T>> adjacencyList(Vertex<T> sourceVertex) {
        if (!hasVertex(sourceVertex)) {
            return null;
        }

        List<Vertex<T>> verticesAdjacent = new LinkedList<>();

        List<Vertex<T>> vertexList = this.vertices;
        for (int i = 0; i < vertexList.size(); i++) {
            Vertex<T> element = vertexList.get(i);
            if (element.getData().equals(sourceVertex.getData())) {
                verticesAdjacent.addAll(element.getAdjacentVertices().keySet());
                break;
            }
        }

        return verticesAdjacent;
    }

    public Map<Vertex<T>, Double> getEdges(Vertex<T> vertex) {
        if (!hasVertex(vertex)) {
            return null;
        }

        for (int i = 0; i < vertices.size(); i++) {
            Vertex<T> element = vertices.get(i);
            if (element.getData().equals(vertex.getData())) {
                return Optional.of(element).map(Vertex::getAdjacentVertices).orElse(null);
            }
        }
        return null;
    }
}
