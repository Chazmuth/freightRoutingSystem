package com.company.objects.graph;

import java.util.ArrayList;

public class Graph {
    int vertexAmount;
    ArrayList<Vertex> vertices;

    public Graph(int vertexAmount) {
        this.vertexAmount = vertexAmount;
        for (int i = 0; i < vertexAmount; i++) {
            vertices.add(new Vertex(i));
        }
    }

    public void addEdge(Edge edge) {
        if (edge.getSource() < vertexAmount && edge.getDestination() < vertexAmount) {
            vertices.get(edge.getSource()).addEdge(edge);
            Edge reverseEdge = new Edge(edge.getDestination(), edge.getSource()
                    , edge.getCost());
            vertices.get(edge.getDestination()).addEdge(reverseEdge);
            //adds a 2-way edge to the vertex specified by the int source in
            //the edge object
        }else{
            System.out.println("Source or Destination out of range");
        }
    }

    public void printGraph() {
        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < vertices.get(i).edges.size(); j++) {
                System.out.println("vertex-" + i + " is connected to " +
                        vertices.get(i).edges.get(j).getDestination() + " with weight "
                        + vertices.get(i).edges.get(j).getCost());
            }
        }
    }
}
