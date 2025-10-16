package hw8.model;

public class LongestProject {
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int monthCount;

    public int getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public LongestProject(int id, String name, int monthCount) {
        this.id = id;
        this.name = name;
        this.monthCount = monthCount;
    }

    public String toString() {
        return String.format("id: %d, name: %s, monthCount: %d", id, name, monthCount);
    }

}
