package com.company.objects.graph;

import java.util.ArrayList;

public class Path {
    int cost;
    ArrayList<Vertex> route;

    public Path() {
        this.cost = 0;
        this.route = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        route.add(vertex);
    }
}
