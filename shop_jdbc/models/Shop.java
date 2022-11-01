package Lesson31.shop_jdbc.models;

public class Shop {
    private int id;
    private String name;
    private int active;

    public Shop(int id, String name, int active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Shop(String name, int active) {
        this.name = name;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
