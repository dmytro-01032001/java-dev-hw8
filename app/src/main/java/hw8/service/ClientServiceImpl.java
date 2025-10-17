package hw8.service;

import java.util.List;

import hw8.dao.ClientDaoService;
import hw8.model.Client;

public class ClientServiceImpl implements ClientService {
    private ClientDaoService daoService;
    public ClientServiceImpl (ClientDaoService daoService) {
        this.daoService = daoService;
    }

    public Client create(String name) throws Exception{
        if (name.length() < 3 || name.length() > 256) {
            throw new Exception("Invalid argument");
        }
        Client client = new Client(0, name);
        daoService.addClient(client);
        return client;
    }

    public Client read(long id) throws Exception {
        if (id < 1) {
            throw new Exception("Invalid argument");
        }
        return daoService.getClient(id);
    }

    public void update(Client client, String new_name) throws Exception {
        if (client.getId() < 1 || client.getName().length() < 3 || client.getName().length() > 256) {
            throw new Exception("Invalid argument");
        }
        daoService.updateClient(client, new_name);
    }

    public void delete(Client client) throws Exception {
        if (client.getId() < 1) {
            throw new Exception("Invalid argument");
        }
        daoService.deleteClient(client.getId());
    }

    public List<Client> listAll() {
        return daoService.listAll();
    }
}
