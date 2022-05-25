package com.company.objects.neuralNetwork.trainingDataGeneration;

import com.company.objects.graph.Edge;
import com.company.objects.graph.Vertex;

import java.util.ArrayList;

public class RoutingVertex{
    Vertex vertex;
    int costFromSource;
    Vertex previous;

    public RoutingVertex(Vertex vertex, int cost, Vertex previous) {
        this.vertex = vertex;
        this.costFromSource = cost;
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "RoutingVertex{" +
                "vertex=" + vertex +
                ", costFromSource=" + costFromSource +
                ", previous=" + previous +
                '}';
    }
}
