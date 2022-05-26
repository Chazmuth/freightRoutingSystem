package com.company.objects.graph;

import java.util.ArrayList;
import java.util.Collections;

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

    public void printRoute(){
        Collections.reverse(route);
        for (int i = 0; i < this.route.size(); i++) {
            System.out.print(this.route.get(i).getId() + ",");
        }
    }
}
