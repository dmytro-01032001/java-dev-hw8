package hw8.model;

public class MaxSalaryWorker {
    private String name;
    private int salary;
    public MaxSalaryWorker(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return String.format("name: %s, salary: %d", name, salary);
    }
}
