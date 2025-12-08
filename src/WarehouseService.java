import java.util.Comparator;
import java.util.Scanner;

public class WarehouseService implements InventoryService {
    static Scanner input = new Scanner(System.in);

    private final Warehouse<Product> warehouse;

    public WarehouseService(Warehouse<Product> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void defineProduct() {
        System.out.print("Ürün ismi: ");
        String name = ScannerUtils.getValidString("Ürün ismi").trim().toLowerCase();

        System.out.print("Üretici ismi: ");
        String manufacturer = ScannerUtils.getValidString("Üretici ismi").trim().toLowerCase();

        System.out.print("Birim (çuval, litre, kg vs.): ");
        String unit = ScannerUtils.getValidAlphabeticString("Birim").toLowerCase();

        Product newProduct = new Product(name, manufacturer, unit);

        warehouse.getInventory().put(newProduct.getId(), newProduct);
        System.out.println("Ürününüz " + newProduct.getId() + " id numarası ile eklenmiştir");
        ScannerUtils.pressEnter();
    }

    @Override
    public void listProducts() {
        System.out.printf("%-5s %-10s %-15s %-10s %-10s %-10s%n", "id", "ismi", "üreticisi", "miktar", "birimi", "raf");
        System.out.println("------------------------------------------------------------");
        warehouse.getInventory().values().forEach(System.out::println);
    }

    @Override
    public void stockIn() {
        System.out.println("--- ÜRÜN GİRİŞİ ---");
        listProducts(); // Girmeden önce ürün id görmek için.

        System.out.print("Giriş yapmak istediğiniz ürün ID: ");
        int id = ScannerUtils.getValidInteger();
        if (!warehouse.getInventory().containsKey(id)) {
            System.err.println("Bu ID sistemde kayıtlı değil.");
            ScannerUtils.pressEnter();
            return;
        }
        System.out.println("Giriş yapmak istediğiniz ürün miktarını giriniz: ");
        int entryAmount = ScannerUtils.getValidInteger();

        if (entryAmount <= 0) {
            System.err.println(" HATA: Ürün girişi 0 veya negatif olamaz!");
            ScannerUtils.pressEnter();
            return;
        }

        Product product = warehouse.getInventory().get(id);
        product.setQuantity(product.getQuantity() + entryAmount);
        System.out.println("Yeni miktar: " + product.getQuantity());
        ScannerUtils.pressEnter();
    }

    @Override
    public void stockOut() {
        System.out.println("--- ÜRÜN ÇIKIŞI ---");
        listProducts();

        System.out.print("Çıkış yapmak istediğiniz ürün ID: ");
        int id = ScannerUtils.getValidInteger();

        if (!warehouse.getInventory().containsKey(id)) {
            System.err.println("Bu ID sistemde kayıtlı değil.");
            ScannerUtils.pressEnter();
            return;
        }
        Product product = warehouse.getInventory().get(id);
        if (product.getQuantity() == 0) {
            System.err.println("Girdiğiniz id deki ürünün miktarı zaten 0");
            ScannerUtils.pressEnter();
            return;
        }
        System.out.println("Çıkış yapmak istediğiniz ürün miktarını giriniz: ");
        int exitAmount = ScannerUtils.getValidInteger();
        if (exitAmount > product.getQuantity() || exitAmount < 0) {
            System.err.println("Var olan ürün miktarından fazla ürün çıkışı yapamazsınız ve girilen miktar negatif olamaz");
            ScannerUtils.pressEnter();
            return;
        }
        warehouse.getInventory().get(id).setQuantity(product.getQuantity() - exitAmount);
        System.out.println("Ürün çıkışı başarılı. Kalan ürün miktarı: " + product.getQuantity());
        ScannerUtils.pressEnter();
    }

    @Override
    public void assignShelf() {
        listProducts();
        System.out.println("Rafa eklemek istediğiniz ürünün id numarasasını giriniz");
        int id = ScannerUtils.getValidInteger();
        if (!warehouse.getInventory().containsKey(id)) {
            System.err.println("Girdiğiniz id'de bir ürün bulunmuyor");
            ScannerUtils.pressEnter();
            return;
        }
        Product product = warehouse.getInventory().get(id);

        System.out.println("Hangi rafa eklensin");
        String shelf = ScannerUtils.getValidString("Raf ismi").trim().toLowerCase();

        product.setShelf(shelf);

        System.out.println(product.getProductName() + " ürünü " + product.getShelf() + " rafına başarılı bir şekilde eklendi");
        ScannerUtils.pressEnter();
    }
    //Stoğu 5 adetten az olan ürünleri getir.
    @Override
    public void listLowStocks() {
        System.out.println("--- KRİTİK STOK LİSTESİ (<5) ---");
        warehouse.getInventory().values().stream()
                .filter(p -> p.getQuantity() < 5)
                .sorted(Comparator.comparing(Product::getProductName))
                .forEach(System.out::println);

        ScannerUtils.pressEnter();
    }

}