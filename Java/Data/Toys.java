package Java.Data;

public class Toys {
    private int id;
    private String name;
    private int count;
    private int weight;
    private static int lastId = 0;

    public Toys(int id, String name, int count, int weight) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.weight = weight;
    }

    public Toys(String name, int count, int weight) {
        this.id = lastId++;
        this.name = name;
        this.count = count;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Count: " + count + ", Weight: " + weight;
    }

}