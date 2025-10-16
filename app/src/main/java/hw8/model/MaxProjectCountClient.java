package hw8.model;

public class MaxProjectCountClient {
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
    private int projectCount;
    public int getProjectCount() {
        return projectCount;
    }
    public void setprojectCount(int projectCount) {
        this.projectCount = projectCount;
    }
    
    public MaxProjectCountClient(int id, String name, int projectCount) {
        this.id = id;
        this.name = name;
        this.projectCount = projectCount;
    }

    public String toString() {
        return String.format("id: %d, name: %s, projectCount: %d", id, name, projectCount);
    }
}
