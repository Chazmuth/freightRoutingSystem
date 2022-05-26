package com.company.objects.neuralNetwork.trainingDataGeneration;

import com.company.objects.graph.Edge;
import com.company.objects.graph.Graph;
import com.company.objects.graph.Path;
import com.company.objects.graph.Vertex;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import static com.company.objects.graph.Graph.readGraphFromFile;

public class DijkstraShortestPath {

    public Path dijkstra(Graph graph, int source, int destination){
        Path path = new Path();

        ArrayList<RoutingVertex> visited = new ArrayList<>();
        PriorityQueue<RoutingVertex> unvisited = new PriorityQueue<>();
        ArrayList<Vertex> graphVertecies = graph.getVertices();

        for (int i = 0; i < graphVertecies.size(); i++) {
            if(graphVertecies.get(i).getId() == source){
                unvisited.add(new RoutingVertex(graphVertecies.get(i), 0, null));
            }else{
                unvisited.add(new RoutingVertex(graphVertecies.get(i), Integer.MAX_VALUE, null));
            }
        }

        //checks that the unvisted list is working and fully loaded
        /*
        Iterator unvisitedIterator = unvisited.iterator();

        while(unvisitedIterator.hasNext()) {
            System.out.println(unvisited.poll().toString());
            System.out.println(" ");
        }*/
        RoutingVertex current;
        while(unvisited.size()>0){
            current = unvisited.poll();
            ArrayList<Edge> currentEdges = current.getVertex().getEdges();

            for (int i = 0; i < currentEdges.size(); i++) {
                if(!(contains(visited, current.getVertex()))){
                    
                }
            }
        }
        return path;
    }


    public boolean contains(ArrayList<RoutingVertex> list, Vertex target) {
        boolean containsVertex = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getVertex().getId() == target.getId()){
                containsVertex = true;
            }
        }
        return containsVertex;
    }

    public static void main(String[] args){
        File graphFile = new File("src/com/company/files/prototype/prototypeGraph");
        Graph graph = readGraphFromFile(graphFile);
        DijkstraShortestPath dijkstra = new DijkstraShortestPath();
        dijkstra.dijkstra(graph, 1, 3);

    }
}
