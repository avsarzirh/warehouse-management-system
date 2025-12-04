import java.util.HashMap;
import java.util.Map;

// Taslak - Depo Varlığı
public class Warehouse {
    private String name;
    private String location;
    // Her deponun kendi envanteri (Heap bellekte ayrı alanlar)
    private final Map<Integer, Product> inventory = new HashMap<>();

    // Constructor, Getter, Setter...

    public Warehouse(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Map<Integer, Product> getInventory() {
        return inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
