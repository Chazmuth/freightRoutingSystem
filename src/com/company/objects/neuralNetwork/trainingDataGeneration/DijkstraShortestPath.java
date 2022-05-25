package com.company.objects.neuralNetwork.trainingDataGeneration;

import com.company.objects.graph.Graph;
import com.company.objects.graph.Path;
import com.company.objects.graph.Vertex;

import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static com.company.objects.graph.Graph.readGraphFromFile;

public class DijkstraShortestPath {

    public Path dijkstra(Graph graph, int source, int destination){
        Path path = new Path();
        //update to make the unvisited a priority queue
        //(makes it faster)
        ArrayList<RoutingVertex> visited = new ArrayList<>();
        ArrayList<RoutingVertex> unvisited = new ArrayList<>();
        ArrayList<Vertex> graphVertecies = graph.getVertices();

        for (int i = 0; i < graphVertecies.size(); i++) {
            if(graphVertecies.get(i).getId() == source){
                unvisited.add(new RoutingVertex(graphVertecies.get(i), 0, null));
            }else{
                unvisited.add(new RoutingVertex(graphVertecies.get(i), Integer.MAX_VALUE, null));
            }
        }
        for (int i = 0; i < unvisited.size(); i++) {
            System.out.println(unvisited.get(i).toString());
        }
        return path;
    }

    public static void main(String[] args){
        File graphFile = new File("src/com/company/files/prototype/prototypeGraph");
        Graph graph = readGraphFromFile(graphFile);
        DijkstraShortestPath dijkstra = new DijkstraShortestPath();
        dijkstra.dijkstra(graph, 1, 3);

    }
}
