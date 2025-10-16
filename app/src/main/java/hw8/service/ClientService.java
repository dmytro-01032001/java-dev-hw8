package hw8.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hw8.Database;
import hw8.model.Queries;
import hw8.model.Client;
import hw8.model.LongestProject;

public class ClientService {
    private static Connection conn = Database.getConnection();
    public static long create(String name) {
        String[] keyColumn = {"id"};
        try (PreparedStatement statement = conn.prepareStatement(Queries.CREATE_CLIENT_TEMPLATE, keyColumn)) {
            statement.setString(1, name);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    return result.getLong(1);
                }

            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getById(long id) {
        try (PreparedStatement statement = conn.prepareStatement(Queries.GET_CLIENT_NAME_BY_ID)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString(1);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setName(long id, String name) {
        try (PreparedStatement statement = conn.prepareStatement(Queries.SET_CLIENT_NAME_BY_ID)) {
            statement.setLong(2, id);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteById(long id) {
        try (PreparedStatement statement = conn.prepareStatement(Queries.DELETE_CLIENT_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(Queries.SELECT_ALL_CLIENTS);
            System.out.println(result.getMetaData().getColumnCount());
            while (result.next()) {
                clients.add(new Client(
                        result.getLong("id"),
                        result.getString("name")
                    )
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        };
        return clients;

    }
}
