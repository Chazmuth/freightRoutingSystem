package com.company.objects.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    int vertexAmount;
    ArrayList<Vertex> vertices;

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

    public void addEdge(Edge edge) {
        if (edge.getSource() < vertexAmount && edge.getDestination() < vertexAmount) {
            vertices.get(edge.getSource()).addEdge(edge);
            Edge reverseEdge = new Edge(edge.getDestination(), edge.getSource()
                    , edge.getCost());
            vertices.get(edge.getDestination()).addEdge(reverseEdge);
            //adds a 2-way edge to the vertex specified by the int source in
            //the edge object
        } else {
            System.out.println("Source or Destination out of range");
        }
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
        File graphFile = new File("src/com/company/files/prototype/prototypeGraph");
        Graph graph = readGraphFromFile(graphFile);
        graph.printGraph();
    }
}
