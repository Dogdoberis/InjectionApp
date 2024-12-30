package lt.jono.injectionapp.database;

public class Product {
    private int id;
    private String material;
    private int glassFiber;
    private int weight;
    private int quantity;
    private int sprueWeight;
    private String date;

    public Product(int id, String material, int glassFiber, int weight, int quantity, int sprueWeight, String date) {
        this.id = id;
        this.material = material;
        this.glassFiber = glassFiber;
        this.weight = weight;
        this.quantity = quantity;
        this.sprueWeight = sprueWeight;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getGlassFiber() {
        return glassFiber;
    }

    public void setGlassFiber(int glassFiber) {
        this.glassFiber = glassFiber;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSprueWeight() {
        return sprueWeight;
    }

    public void setSprueWeight(int sprueWeight) {
        this.sprueWeight = sprueWeight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
// Constructors, getters and setters
}
