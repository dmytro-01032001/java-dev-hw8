package hw8.model;

public class ProjectPrice {
    private String name;
    private int price;
    public ProjectPrice(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return String.format("name: %s, price: %d", name, price);
    }
}
