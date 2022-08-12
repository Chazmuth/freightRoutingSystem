package com.company.objects.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringJoiner;

public class Path {
    int cost;
    ArrayList<Vertex> route;

    public Path() {
        this.cost = 0;
        this.route = new ArrayList<>();
    }

    public void addVertex(Vertex node) {
        route.add(node);
    }

    public String getRoute() {
        StringJoiner stringJoiner = new StringJoiner(",");
        Collections.reverse(route);
        for (int i = 0; i < this.route.size(); i++) {
            if (i < this.route.size() - 1) {
                stringJoiner.add(Integer.toString(this.route.get(i).getId()));
            } else {
                stringJoiner.add(Integer.toString(this.route.get(i).getId()));
            }
        }
        return stringJoiner.toString();
    }
}

