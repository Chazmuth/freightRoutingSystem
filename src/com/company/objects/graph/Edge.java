package com.company.objects.graph;

public class Edge {
    Vertex source;
    Vertex destination;
    //source and destination will eventually
    //be locationIDs from the database
    int cost; //expand cost into the 3 types
    // (money, time, and GHGs)


    public Edge(Vertex source, Vertex destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public Edge(int[] data){
        this.source = new Vertex(data[0]);
        this.destination = new Vertex(data[1]);
        this.cost = data[2];
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + destination +
                ", cost=" + cost +
                '}';
    }
}
