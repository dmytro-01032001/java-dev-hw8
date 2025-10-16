package hw8.model;

public class OldestYoungestWorker {
    private String type;
    private String name;
    private String birthday;
    public OldestYoungestWorker(String type, String name, String birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }
    public String toString() {
        return String.format("type: %s, name: %s, birthday: %s", type, name, birthday);
    }
}
