package com.company.objects.neuralNetwork;

import com.company.objects.graph.Graph;

public class GraphMatrix extends Matrix{
    Graph graph;

    public GraphMatrix(Graph graph) {
        this.graph = graph;
        this.rows = graph.getVertexAmount();
        this.cols = graph.getVertexAmount();
        this.data = new double[rows][cols];
        for (int i = 0; i < ; i++) {

        }
    }
}
