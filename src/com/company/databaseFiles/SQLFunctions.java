package com.company.databaseFiles;


import com.company.objects.graph.Graph;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.*;

public class SQLFunctions {

    protected static String databaseLocation = (System.getProperty("user.dir") + "\\freightRoutingSystemDatabase.accdb");

    public static ConnectionStatementPair<> init(){
        //https://stackoverflow.com/questions/457629/how-to-return-multiple-objects-from-a-java-method
        //a method to return the connection and statement for each method to make it need less code
    }

    public static void getTable(String tableName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + databaseLocation, "", "");
            // sets up a connection with the database

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //forms a statement which is used to format the results from your SQL

            String sql = "SELECT * FROM " + tableName; //this is just the sql command

            ResultSet resultSet = statement.executeQuery(sql);
            //executes the command

            //loops through the result set printing the result
            while(resultSet.next()){
                System.out.println("jobID: " + resultSet.getString("jobID"));
                System.out.println("userID: " + resultSet.getString("userID"));
                System.out.println("startDate: " + resultSet.getDate("startDate"));
            }

            //closing connections so there are no deadlocks
            resultSet.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error in the SQL class: " + e);
        }
    }

    public static Graph readGraph(){
        Graph graph;
        try{
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + databaseLocation, "", "");
            // sets up a connection with the database

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //forms a statement which is used to format the results from your SQL

        }catch (Exception e){
            System.out.println("Error in the SQL class: " + e);
            graph = new Graph(0);
        }
        return graph;
    }

    public static void main(String[] args) {
        System.out.println(databaseLocation);
        getTable("Jobs");
    }
}
