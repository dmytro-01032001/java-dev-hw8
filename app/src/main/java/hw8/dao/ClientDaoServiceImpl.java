package hw8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hw8.model.Client;
import hw8.model.Queries;

public class ClientDaoServiceImpl implements ClientDaoService {
    private Connection connection;
    public ClientDaoServiceImpl(Connection connection) {
        this.connection = connection;
    }

    public void addClient(Client client) {
        String[] keyColumn = {"id"};
        try (PreparedStatement statement = connection.prepareStatement(Queries.CREATE_CLIENT_TEMPLATE, keyColumn)) {
            statement.setString(1, client.getName());
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    client.setId(result.getLong(1));
                }

            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getClient(long id) {
        try (PreparedStatement statement = connection.prepareStatement(Queries.GET_CLIENT_NAME_BY_ID)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Client(id, result.getString(1));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateClient(Client client, String name) {
        try (PreparedStatement statement = connection.prepareStatement(Queries.SET_CLIENT_NAME_BY_ID)) {
            statement.setLong(2, client.getId());
            statement.setString(1, name);
            statement.executeUpdate();
            client.setName(name);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(long id) {
        try (PreparedStatement statement = connection.prepareStatement(Queries.DELETE_CLIENT_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(Queries.SELECT_ALL_CLIENTS);
            while (result.next()) {
                clients.add(new Client(
                        result.getLong("id"),
                        result.getString("name")
                    )
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
