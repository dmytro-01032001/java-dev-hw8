package hw8.model;


public class Client {
    private long id;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return String.format("(id: %d, name: %s)", id, name);
    }
}
