package hw8.model;


public class Client {
    private long id;
    private String name;

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return String.format("(id: %d, name: %s)", id, name);
    }
}
