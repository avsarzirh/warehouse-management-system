public class Product {
    static int idCounter = 1000;

    private int id;
    private String productName;
    private String manufacturer;
    private String unit;
    private int quantity = 0;
    private String shelf = "-";

    public Product(String productName, String manufacturer, String unit) {
        this.id = idCounter;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.unit = unit;
        idCounter++;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-10s %-15s %-10d %-10s %-10s", id, productName, manufacturer, quantity, unit, shelf);
    }
}