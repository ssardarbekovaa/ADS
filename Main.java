package com.aqaru;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>(true);

        Vertex<String> firstVertex = new Vertex<>("Almaty");
        Vertex<String> secondVertex = new Vertex<>("Astana");
        Vertex<String> thirdVertex = new Vertex<>("Shymkent");
        Vertex<String> fourthVertex = new Vertex<>("Qostanay");
        Vertex<String> fifthVertex = new Vertex<>("Qyzylorda");

        graph.addEdge(firstVertex, secondVertex, 2.1);
        graph.addEdge(firstVertex, secondVertex, 7.2);
        graph.addEdge(thirdVertex, secondVertex, 3.9);
        graph.addEdge(secondVertex, fourthVertex, 3.5);
        graph.addEdge(thirdVertex, fifthVertex, 5.4);

        System.out.println("Dijkstra:");
        Search<String> djk = new DijkstraSearch<>(graph, firstVertex);
        outputPath(djk, fifthVertex);

        System.out.println();
        System.out.println("---------------------------");
        System.out.println("BFS:");
        Search<String> bfs = new BreadthFirstSearch<>(graph, firstVertex);
        outputPath(bfs, fifthVertex);
    }

    public static void outputPath(Search<String> search, Vertex<String> key) {
        for (Vertex<String> vertex : search.pathTo(key)) {
            System.out.print(vertex.getData() + " -> ");
        }
    }
}
