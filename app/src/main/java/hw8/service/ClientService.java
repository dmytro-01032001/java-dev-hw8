package hw8.service;

import java.util.List;

import hw8.model.Client;

public interface ClientService {
    public Client create(String name) throws Exception;
    public Client read(long id) throws Exception;
    public void update(Client client, String name) throws Exception;
    public void delete(Client client) throws Exception;
    public List<Client> listAll();
}
