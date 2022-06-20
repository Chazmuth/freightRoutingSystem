package com.company.databaseFiles;

public class ConnectionStatementPair<Connection, Statement> {
    public final Connection connection;
    public final Statement statement;

    public ConnectionStatementPair(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }
}
