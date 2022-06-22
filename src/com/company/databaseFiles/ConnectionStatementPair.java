package com.company.databaseFiles;

import java.sql.Connection;
import java.sql.Statement;

public class ConnectionStatementPair {
    private Connection connection;
    private Statement statement;

    public ConnectionStatementPair(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void closeConnection() {
        try {
            connection.close();
        }catch (Exception e){
            System.out.println("Error in the SQL class: " + e);
        }
    }
}
