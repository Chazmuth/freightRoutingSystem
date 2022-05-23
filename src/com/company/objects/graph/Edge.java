package com.company.objects.graph;

public class Edge {
    int source;
    int destination;
    //source and destination will eventually
    //be locationIDs from the database
    int cost; //expand cost into the 3 types
    // (money, time, and GHGs)


    public Edge(int source, int destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }
}
