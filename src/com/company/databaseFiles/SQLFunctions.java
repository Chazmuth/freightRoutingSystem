package com.company.databaseFiles;


import com.company.objects.graph.Edge;
import com.company.objects.graph.Graph;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.*;

public class SQLFunctions {

    protected static String databaseLocation = (System.getProperty("user.dir") + "\\freightRoutingSystemDatabase.accdb");

    public static ConnectionStatementPair init() {
        //a method to return the connection and statement for each method to make it need less code
        ConnectionStatementPair output;
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + databaseLocation, "", "");
            // sets up a connection with the database

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //forms a statement which is used to format the results from your SQL

            output = new ConnectionStatementPair(connection, statement);
        }catch (Exception e){
            System.out.println("Error in the SQL class: " + e);
            output = new ConnectionStatementPair(null, null);
        }
        return output;
    }

    public static void getTable(String tableName) {
        try {
            ConnectionStatementPair connectionStatementPair = init();

            String sql = "SELECT * FROM " + tableName; //this is just the sql command
            ResultSet resultSet = connectionStatementPair.getStatement().executeQuery(sql);
            //executes the command

            //loops through the result set printing the result
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(resultSet.getMetaData().getColumnName(i) + " " + columnValue);
                }
                System.out.println("\n");
            }

            //closing connections so there are no deadlocks
            resultSet.close();
            connectionStatementPair.closeConnection();

        } catch (Exception e) {
            System.out.println("Error in the SQL class: " + e);
        }
    }

    public static Graph readGraph() {
        Graph graph;
        try {
            ConnectionStatementPair connectionStatementPair = init();

            String getTableSQL = "SELECT * FROM Edges";
            ResultSet resultSet = connectionStatementPair.getStatement().executeQuery(getTableSQL);

            String countSQL = "SELECT COUNT(*) FROM Edges AS count";
            ResultSet countResultSet = connectionStatementPair.getStatement().executeQuery(countSQL);

            countResultSet.next();
            graph = new Graph(countResultSet.getInt(1));

            while(resultSet.next()){
                int source = Integer.parseInt(resultSet.getString("StartNode")) - 1;
                int destination = Integer.parseInt(resultSet.getString("EndNode")) - 1;
                StringBuilder stringCost = new StringBuilder(resultSet.getString("Cost"));
                stringCost.delete(stringCost.length()-5, stringCost.length());
                int cost = Integer.parseInt(stringCost.toString());
                int[] edgeData = {source, destination, cost};
                graph.addEdge(new Edge(edgeData));
            }

        } catch (Exception e) {
            System.out.println("Error in the SQL class: " + e);
            graph = new Graph(0);
        }
        return graph;
    }

    public static void main(String[] args) {
        getTable("Edges");
        readGraph().printGraph();
    }
}
