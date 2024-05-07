package grocery.store.inventory;
public class Item {

    private String type;
    private int quantity;
    private double price;
    int qtyIncrease;//to Increase quantity

    //Constructor
    public Item() {

    }
    //Parameterized Constructor

    public Item(String type) {
        this.type = type;
    }

    //Setters method for Quantity and Price
    public Item setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Item setPrice(double price) {
        this.price = price;
        return this;
    }

    //Getters method for Quantity and Price and Type
    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return type + " - in stock: " + getQuantity() + ", price: $" + getPrice();
    }

}
//Brand subclass of Item

class Brand extends Item {

    private String brand;
    //Constructor

    public Brand() {
        super();
        this.brand = brand;
    }
    //Parameterized Constructor

    public Brand(String brand, String type) {
        super(type);
        this.brand = brand;
    }

    //Get method for brand
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //toString to print Information 
    @Override
    public String toString() {
        return brand + " " + getType() + " - in stock: " + getQuantity() + ", price: $" + getPrice();
    }

}

