package com.company.objects.graph;

import java.util.ArrayList;

public class Vertex {
    int id;
    ArrayList<Edge> edges;

    public Vertex(int id) {
        this.id = id;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }
}
