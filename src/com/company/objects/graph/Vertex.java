package com.company.objects.graph;

import java.util.ArrayList;

public class Vertex {
    int id;
    ArrayList<Edge> edges;

    public Vertex(int id) {
        this.id = id;
        edges = new ArrayList<>();
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                '}';
    }
}
