package com.company.objects.neuralNetwork.trainingDataGeneration;

import com.company.objects.graph.Edge;
import com.company.objects.graph.Graph;
import com.company.objects.graph.Path;
import com.company.objects.graph.Vertex;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

import static com.company.objects.graph.Graph.readGraphFromFile;

public class DijkstraShortestPath {

    public static Path dijkstra(Graph graph, Vertex source, Vertex destination) {
        Path path = new Path();

        ArrayList<RoutingVertex> visited = new ArrayList<>();
        ArrayList<RoutingVertex> unvisited = new ArrayList<>();
        ArrayList<Vertex> graphVertecies = graph.getVertices();

        for (int i = 0; i < graphVertecies.size(); i++) {
            if (graphVertecies.get(i) == source) {
                unvisited.add(new RoutingVertex(graphVertecies.get(i), 0, null));
            } else {
                unvisited.add(new RoutingVertex(graphVertecies.get(i), Integer.MAX_VALUE, null));
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
            for (int i = 0; i < currentEdges.size(); i++) {
                if (!(contains(visited, current.getVertex()))) {
                    int cost = currentEdges.get(i).getCost() + current.getCostFromSource();
                    int routingVertexLocation = getRoutingVertex(unvisited, currentEdges.get(i).getDestination().getId());

                    if (cost < unvisited.get(routingVertexLocation).getCostFromSource()) {
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
            if (current.getVertex() == destination) {
                break;
            }
        }
        current = visited.get(getRoutingVertex(visited, destination.getId()));
        do {
            path.addVertex(current.getVertex());
            current = visited.get(getRoutingVertex(visited, current.getPrevious().getId()));
        } while (!(current.equals(visited.get(getRoutingVertex(visited, source.getId())))));
        path.addVertex(current.getVertex());
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

    /*public static File generateData() {

        try {
            File graphFile = new File("src/com/company/files/prototype/prototypeGraph");
            Writer fileWriter = new FileWriter(graphFile);
            Graph graph = readGraphFromFile(graphFile);
            for (int i = 0; i < graph.getVertexAmount(); i++) {
                Vertex currentVertex = graph.getVertex(i);
                for (int j = 0; j < currentVertex.getEdges().size(); j++) {
                    fileWriter.write(dijkstra(graph, graph.getVertex(i), graph.getVertex(currentVertex.getEdges().get(j).getDestination().getId())).getRoute());
                }
            }
        }catch(Exception e){
            System.out.println("there was an error" + e);
        }
    }*/

    public static void main(String[] args) {
        File graphFile = new File("src/com/company/files/prototype/prototypeGraph");
        Graph graph = readGraphFromFile(graphFile);
        System.out.println(dijkstra(graph, graph.getVertex(0), graph.getVertex(7)).getRoute());
    }
}
