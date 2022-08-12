package com.company.objects.neuralNetwork.trainingDataGeneration;

import com.company.objects.graph.Vertex;

import java.util.Comparator;

public class RoutingVertex implements Comparator<RoutingVertex>, Comparable<RoutingVertex>{
    //implement priority queues when the fucntion works
    Vertex node;
    int costFromSource;
    Vertex previous;

    public RoutingVertex(Vertex node, int cost, Vertex previous) {
        this.node = node;
        this.costFromSource = cost;
        this.previous = previous;
    }

    public Vertex getVertex() {
        return node;
    }

    public int getCostFromSource() {
        return costFromSource;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setCostFromSource(int costFromSource) {
        this.costFromSource = costFromSource;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    //returns an arraylist of routing vertex of the nodes that neighbours the given routing vertex

    @Override
    public String toString() {
        return "RoutingVertex{" +
                "vertex=" + node +
                ", costFromSource=" + costFromSource +
                ", previous=" + previous +
                '}';
    }

    //overides the compareTo method
    @Override
    public int compareTo(RoutingVertex routingVertex) {
        return (this.costFromSource) -(routingVertex.costFromSource);
    }

    // Overriding the compare method to sort the age
    @Override
    public int compare(RoutingVertex RV1, RoutingVertex RV2) {
        return RV1.costFromSource - RV2.costFromSource;
    }
}
