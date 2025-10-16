package hw8.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import hw8.Database;
import hw8.model.Queries;

public class DatabaseInitService {
    public static void main(String[] args) {
        Connection conn = Database.getConnection();
        try(Statement statement = conn.createStatement()) {
            statement.addBatch(Queries.CREATE_WORKER_TABLE);
            statement.addBatch(Queries.CREATE_CLIENT_TABLE);
            statement.addBatch(Queries.CREATE_PROJECT_TABLE);
            statement.addBatch(Queries.CREATE_PROJECT_CLIENT_TABLE);
            statement.executeBatch();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
