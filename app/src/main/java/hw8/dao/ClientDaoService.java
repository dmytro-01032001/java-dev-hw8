package hw8.dao;

import java.util.List;

import hw8.model.Client;

public interface ClientDaoService {
    void addClient(Client client);
    Client getClient(long id);
    void updateClient(Client client, String name);
    void deleteClient(long id);
    List<Client> listAll();
}
