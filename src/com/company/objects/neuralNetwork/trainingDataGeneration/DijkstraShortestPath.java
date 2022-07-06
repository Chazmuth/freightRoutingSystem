package com.company.objects.neuralNetwork.trainingDataGeneration;

import com.company.databaseFiles.SQLFunctions;
import com.company.objects.graph.Edge;
import com.company.objects.graph.Graph;
import com.company.objects.graph.Path;
import com.company.objects.graph.Vertex;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

import static com.company.databaseFiles.SQLFunctions.readGraph;

public class DijkstraShortestPath {

    public static Path dijkstra(Graph graph, Vertex source, Vertex destination) {
        Path path = new Path();

        ArrayList<RoutingVertex> visited = new ArrayList<>();
        ArrayList<RoutingVertex> unvisited = new ArrayList<>();
        ArrayList<Vertex> graphVertecies = graph.getVertices();


        for (Vertex graphVertex : graphVertecies) {
            if (graphVertex.getId() == source.getId()) {
                unvisited.add(new RoutingVertex(graphVertex, 0, null));
            } else {
                unvisited.add(new RoutingVertex(graphVertex, Integer.MAX_VALUE, null));
            }
        }
        //adds all vertecies to the unvisted graph, if
        //it is the source, the cost is 0,
        //otherwise the cost is the max integer value
        //and all do not have a previous node yet

        /*
        //checks that the unvisted list is working and fully loaded
        Iterator unvisitedIterator = unvisited.iterator();

        while(unvisitedIterator.hasNext()) {
            System.out.println(unvisited.poll().toString());
            System.out.println(" ");
        }*/

        RoutingVertex current;

        while (unvisited.size() > 0) {
            Collections.sort(unvisited);
            current = unvisited.get(0);
            //sorts the unvisited list so that the
            //Routing Vertex with the lowest cost from
            //source is at index 0
            //and makes it the routing vertex current

            ArrayList<Edge> currentEdges = current.getVertex().getEdges();
            //makes an arraylist with all of the current vertex's edges
            for (Edge currentEdge : currentEdges) {
                if (!(contains(visited, current.getVertex()))) {
                    int cost = currentEdge.getCost() + current.getCostFromSource();
                    int routingVertexLocation = getRoutingVertex(unvisited, currentEdge.getDestination().getId());

                    if (cost < unvisited.get(routingVertexLocation).getCostFromSource()) {
                        unvisited.get(routingVertexLocation).setCostFromSource(cost);
                        unvisited.get(routingVertexLocation).setPrevious(current.getVertex());
                    }
                }
            }
            visited.add(current);
            unvisited.remove(current);
            if (current.getVertex() == destination) {
                break;
            }
        }
        current = visited.get(getRoutingVertex(visited, destination.getId()));
        while (true) {
            path.addVertex(current.getVertex());
            if (current.getPrevious() != null) {
                current = visited.get(getRoutingVertex(visited, current.getPrevious().getId()));
            }else{
                break;
            }
        }
        return path;
    }

    public static boolean contains(ArrayList<RoutingVertex> list, Vertex target) {
        boolean containsVertex = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVertex().getId() == target.getId()) {
                containsVertex = true;
                break;
            }
        }
        return containsVertex;
    }

    public static int getRoutingVertex(ArrayList<RoutingVertex> list, int targetId) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVertex().getId() == targetId) {
                return i;
            }
        }
        return 0;
    }

    public static void generateData() {
        File trainingData;
        try {
            trainingData = new File("src/com/company/objects/neuralNetwork/trainingDataGeneration/trainingData");
            System.out.println("file created");
        } catch (Exception e1) {
            System.out.println("An error occurred" + e1);
            trainingData = null;
        }
        try {
            System.out.println("here");
            Writer fileWriter = new FileWriter(trainingData);
            Graph graph = readGraph();
            for (int i = 0; i < graph.getVertexAmount()-1; i++) {
                for (int j = 0; j < graph.getVertexAmount()-1; j++) {
                    if (i != j) {
                        System.out.println(dijkstra(graph, graph.getVertex(i), graph.getVertex(j)).getRoute());
                        fileWriter.write(dijkstra(graph, graph.getVertex(i), graph.getVertex(j)).getRoute() + "\n");
                        System.out.println("written "  +i + " to " + j);
                    }
                }
            }
            fileWriter.close();
        } catch (Exception e2) {
            System.out.println("there was an error" + e2);
        }
    }

    public static void main(String[] args) {
        generateData();
    }
}
