package com.company.objects.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    private int vertexAmount;
    private ArrayList<Vertex> vertices;

    public Graph(int vertexAmount) {
        this.vertexAmount = vertexAmount;
        vertices = new ArrayList<>();
        for (int i = 0; i < vertexAmount; i++) {
            vertices.add(new Vertex(i));
        }
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int location){
        try{
            return this.vertices.get(location);
        }catch (Exception e){
            System.out.println("An error occured" + e);
            return this.vertices.get(0);
        }
    }

    public int getVertexAmount() {
        return vertexAmount;
    }

    public void addEdge(Edge edge) {
        if (edge.getSource().getId() < vertexAmount
                && edge.getDestination().getId() < vertexAmount) {
            vertices.get(edge.getSource().getId()).addEdge(edge);
            //adds the given edge to the vertex specified by the vertex in the edge
            Edge reverseEdge = new Edge(edge.getDestination(), edge.getSource()
                    , edge.getCost());
            //creates the reverse edge
            vertices.get(edge.getDestination().getId()).addEdge(reverseEdge);
            //adds a 2-way edge to the vertex specified by the int source in
            //the edge object
        } else {
            System.out.println("Source or Destination out of range");
        }
    }

    public int getEdgeCost(int sourceId, int destinationId){
        Vertex source = getVertex(sourceId);
        int cost = 0;
        for (int i = 0; i < source.getEdges().size(); i++) {
            Edge edge = source.getEdges().get(i);
            if(edge.getDestination().getId() == destinationId){
                cost = edge.getCost();
            }
        }
        return cost;
    }

    public void printGraph() {
        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < vertices.get(i).edges.size(); j++) {
                System.out.println("vertex-" + i + " is connected to " +
                        vertices.get(i).edges.get(j).getDestination() + " with weight "
                        + vertices.get(i).edges.get(j).getCost());
            }
        }
    }

    public static Graph readGraphFromFile(File file) {
        Graph graph;
        try {
            Scanner reader = new Scanner(file);
            reader.useDelimiter(",");
            graph = new Graph(Integer.parseInt(reader.nextLine()));
            while(reader.hasNext()){
                String[] edgeDataString = reader.nextLine().split(",");
                int[] edgeData = new int[3];
                for (int i = 0; i < 3; i++) {
                    edgeData[i] = Integer.parseInt(edgeDataString[i]);
                }
                Edge edge = new Edge(edgeData);
                graph.addEdge(edge);
            }
        }catch(FileNotFoundException e){
            System.out.println("There was an error");
            System.out.println(e);
            graph = new Graph(1);
        }
        return graph;
    }

    public static void main(String[] args) {

    }
}
