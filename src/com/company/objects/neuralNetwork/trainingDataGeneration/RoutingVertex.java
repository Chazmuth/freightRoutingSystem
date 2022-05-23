package com.company.objects.neuralNetwork.trainingDataGeneration;

import com.company.objects.graph.Vertex;

public class RoutingVertex{
    int id;
    int cost;
    Vertex previous;
    public RoutingVertex(Vertex previous) {
        this.id = previous.getId();
    }
}
