package com.aqaru;

import java.util.*;

public class DijkstraSearch<T> extends Search<T> {
    private final Map<Vertex<T>, Double> distances;
    private final Set<Vertex<T>> unsettledNodes;
    private final WeightedGraph<T> graph;

    public DijkstraSearch(WeightedGraph<T> graph, Vertex<T> sourceVertex) {
        super(sourceVertex);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (unsettledNodes.size() > 0) {
            Vertex<T> vertexWithMinimumWeight = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(vertexWithMinimumWeight);
            unsettledNodes.remove(vertexWithMinimumWeight);

            List<Vertex<T>> adjacencyList = graph.adjacencyList(vertexWithMinimumWeight);

            for (int i = 0; i < adjacencyList.size(); i++) {
                Vertex<T> element = adjacencyList.get(i);
                if (getShortestDistance(element) > getShortestDistance(vertexWithMinimumWeight) + getDistance(vertexWithMinimumWeight, element)) {
                    unsettledNodes.add(element);
                    edgeTo.put(element, vertexWithMinimumWeight);
                    distances.put(element, getShortestDistance(vertexWithMinimumWeight) + getDistance(vertexWithMinimumWeight, element));
                }
            }
        }
    }

    private double getDistance(Vertex<T> sourceVertex, Vertex<T> targetVertex) {
        for (Vertex<T> adjacentVertex : graph.getEdges(sourceVertex).keySet()) {
            if (adjacentVertex.equals(targetVertex)) {
                return graph.getEdges(sourceVertex).get(adjacentVertex);
            }
        }

        throw new RuntimeException("Not found!");
    }

    private Vertex<T> getVertexWithMinimumWeight(Set<Vertex<T>> vertices) {
        Vertex<T> minimum = null;

        for (Vertex<T> tempVertex : vertices) {
            if (minimum != null) {
                if (!(getShortestDistance(tempVertex) < getShortestDistance(minimum))) {
                    continue;
                }
                minimum = tempVertex;
            } else {
                minimum = tempVertex;
            }
        }

        return minimum;
    }

    private double getShortestDistance(Vertex<T> destination) {
        Double d = distances.get(destination);
        return (d == null ? Double.MAX_VALUE : d);
    }
}
