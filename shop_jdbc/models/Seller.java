package Lesson31.shop_jdbc.models;

public class Seller {
    private int id;
    private String name;
    private int age;
    private int active;
    private int shopId;

    public Seller(int id, String name, int age, int active, int shopId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
        this.shopId = shopId;
    }

    public Seller(String name, int age, int active, int shopId) {
        this.name = name;
        this.age = age;
        this.active = active;
        this.shopId = shopId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", active=" + active +
                ", shopId=" + shopId +
                '}';
    }
}
