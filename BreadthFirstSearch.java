package com.aqaru;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch<T> extends Search<T> {
    public BreadthFirstSearch(WeightedGraph<T> graph, Vertex<T> sourceVertex) {
        super(sourceVertex);
        bfs(graph, sourceVertex);
    }

    private void bfs(WeightedGraph<T> graph, Vertex<T> sourceVertex)
    {
        marked.add(sourceVertex);

        Queue<Vertex<T>> vertices = new LinkedList<>();
        vertices.add(sourceVertex);

        while (!vertices.isEmpty()) {
            Vertex<T> temp = vertices.remove();

            List<Vertex<T>> adjacencyList = graph.adjacencyList(temp);

            for (int i = 0; i < adjacencyList.size(); i++) {
                Vertex<T> element = adjacencyList.get(i);
                if (!marked.contains(element)) {
                    vertices.add(element);
                    edgeTo.put(element, temp);
                    marked.add(element);
                }
            }
        }
    }
}
