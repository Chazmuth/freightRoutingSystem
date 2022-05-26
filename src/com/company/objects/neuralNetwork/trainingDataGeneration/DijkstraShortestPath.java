package com.company.objects.neuralNetwork.trainingDataGeneration;

import com.company.objects.graph.Edge;
import com.company.objects.graph.Graph;
import com.company.objects.graph.Path;
import com.company.objects.graph.Vertex;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

import static com.company.objects.graph.Graph.readGraphFromFile;

public class DijkstraShortestPath {

    public static Path dijkstra(Graph graph, Vertex source, Vertex destination){
        Path path = new Path();

        ArrayList<RoutingVertex> visited = new ArrayList<>();
        ArrayList<RoutingVertex> unvisited = new ArrayList<>();
        ArrayList<Vertex> graphVertecies = graph.getVertices();

        for (int i = 0; i < graphVertecies.size(); i++) {
            if(graphVertecies.get(i) == source){
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
            Collections.sort(unvisited);
            current = unvisited.get(0);
            ArrayList<Edge> currentEdges = current.getVertex().getEdges();

            for (int i = 0; i < currentEdges.size(); i++) {
                if(!(contains(visited, current.getVertex()))){
                    int cost = currentEdges.get(i).getCost() + current.getCostFromSource();
                    int routingVertexLocation = getRoutingVertex(unvisited, currentEdges.get(i).getDestination().getId());

                    if(cost < unvisited.get(routingVertexLocation).getCostFromSource()){
                        RoutingVertex updatedRoutingVertex = unvisited.get(routingVertexLocation);
                        updatedRoutingVertex.setCostFromSource(cost);
                        updatedRoutingVertex.setPrevious(current.getVertex());
                        unvisited.remove(routingVertexLocation);
                        unvisited.add(updatedRoutingVertex);
                    }
                }
            }
            visited.add(current);
            unvisited.remove(current);
        }
        for (int i = 0; i < visited.size(); i++) {
            System.out.println(visited.get(i).toString());
        }
        return path;
    }


    public static boolean contains(ArrayList<RoutingVertex> list, Vertex target) {
        boolean containsVertex = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getVertex().getId() == target.getId()){
                containsVertex = true;
            }
        }
        return containsVertex;
    }

    public static int getRoutingVertex (ArrayList<RoutingVertex> list, int targetId){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getVertex().getId() == targetId){
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args){
        File graphFile = new File("src/com/company/files/prototype/prototypeGraph");
        Graph graph = readGraphFromFile(graphFile);
        DijkstraShortestPath dijkstra = new DijkstraShortestPath();
        dijkstra.dijkstra(graph, graph.getVertices().get(1), graph.getVertices().get(3));
    }
}
