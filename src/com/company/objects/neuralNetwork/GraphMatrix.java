package com.company.objects.neuralNetwork;

import com.company.objects.graph.Graph;

public class GraphMatrix extends Matrix {
    Graph graph;

    public GraphMatrix(Graph graph) {
        this.graph = graph;
        int graphSize = graph.getVertexAmount();
        this.rows = graphSize;
        this.cols = graphSize;
        this.data = new double[rows][cols];
        for (int i = 0; i <graphSize; i++) {
            for (int j = 0; j < graphSize; j++) {
                data[i][j] = graph.getEdgeCost(i, j);
            }
        }
    }

    //creates an adjacency matrix from a graph (eventually extend the graph object to be 3 adjacency
    // matrices for each type of weight
    // https://www.tutorialspoint.com/weighted-graph-representation-in-data-structure
    // https://towardsdatascience.com/graph-theory-and-deep-learning-know-hows-6556b0e9891b
}
