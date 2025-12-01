import java.util.HashMap;
import java.util.Scanner;

public class WarehouseService {
    static Scanner input = new Scanner(System.in);

    // Eski: urunMap -> Yeni: inventory
    static HashMap<Integer, Product> inventory = new HashMap<>();

    // Eski: urunTanimla
    public static void defineProduct() {
        System.out.print("Ürün ismi: ");
        String name = getValidString("Ürün ismi").trim().toLowerCase();

        System.out.print("Üretici ismi: ");
        String manufacturer = getValidString("Üretici ismi").trim().toLowerCase();

        System.out.print("Birim (çuval, litre, kg vs.) yoksa boş bırakın: ");
        String unit = input.nextLine().trim().toLowerCase();

        Product newProduct = new Product(name, manufacturer, unit);

        inventory.put(newProduct.getId(), newProduct);
        System.out.println("Ürününüz " + newProduct.getId() + " id numarası ile eklenmiştir");
        pressEnter();
    }

    // Eski: urunListeleme
    public static void listProducts() {
        System.out.printf("%-5s %-10s %-15s %-10s %-10s %-10s%n", "id", "ismi", "üreticisi", "miktar", "birimi", "raf");
        System.out.println("------------------------------------------------------------");
        inventory.values().forEach(System.out::println);
    }

    // Eski: urunGirisi
    public static void stockIn() {
        System.out.println("--- ÜRÜN GİRİŞİ ---");
        listProducts(); // Girmeden önce ürün id görmek için.

        System.out.print("Giriş yapmak istediğiniz ürün ID: ");
        int id = getValidInteger();
        if (!inventory.containsKey(id)) {
            System.err.println("Bu ID sistemde kayıtlı değil.");
            pressEnter();
            return;
        }
        System.out.println("Giriş yapmak istediğiniz ürün miktarını giriniz: ");
        int entryAmount = getValidInteger();

        if (entryAmount <= 0) {
            System.err.println(" HATA: Ürün girişi 0 veya negatif olamaz!");
            pressEnter();
            return;
        }

        Product product = inventory.get(id);
        product.setQuantity(product.getQuantity() + entryAmount);
        System.out.println("Yeni miktar: " + product.getQuantity());
        pressEnter();
    }

    // Eski: urunCikis
    public static void stockOut() {
        System.out.println("--- ÜRÜN ÇIKIŞI ---");
        listProducts();

        System.out.print("Çıkış yapmak istediğiniz ürün ID: ");
        int id = getValidInteger();

        if (!inventory.containsKey(id)) {
            System.err.println("Bu ID sistemde kayıtlı değil.");
            pressEnter();
            return;
        }
        Product product = inventory.get(id);
        if (product.getQuantity() == 0) {
            System.err.println("Girdiğiniz id deki ürünün miktarı zaten 0");
            pressEnter();
            return;
        }
        System.out.println("Çıkış yapmak istediğiniz ürün miktarını giriniz: ");
        int exitAmount = getValidInteger();
        if (exitAmount > product.getQuantity() || exitAmount < 0) {
            System.err.println("Var olan ürün miktarından fazla ürün çıkışı yapamazsınız ve girilen miktar negatif olamaz");
            pressEnter();
            return;
        }
        inventory.get(id).setQuantity(product.getQuantity() - exitAmount);
        System.out.println("Ürün çıkışı başarılı. Kalan ürün miktarı: " + product.getQuantity());
        pressEnter();
    }

    // Eski: rafaEkle
    public static void assignShelf() {
        listProducts();
        System.out.println("Rafa eklemek istediğiniz ürünün id numarasasını giriniz");
        int id = getValidInteger();
        if (!inventory.containsKey(id)) {
            System.err.println("Girdiğiniz id'de bir ürün bulunmuyor");
            pressEnter();
            return;
        }
        Product product = inventory.get(id);

        System.out.println("Hangi rafa eklensin");
        String shelf = getValidString("Raf ismi").trim().toLowerCase();

        product.setShelf(shelf);

        System.out.println(product.getProductName() + " ürünü " + product.getShelf() + " rafına başarılı bir şekilde eklendi");
        pressEnter();
    }

    public static void pressEnter() {
        System.out.println("Devam etmek için Enter'a basınız...");
        input.nextLine();
    }

    // Eski: getIntTryCatch
    public static int getValidInteger() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Lütfen sadece sayısal değer giriniz");
            }
        }
    }

    // Eski: isBlankCheck
    public static String getValidString(String str) {
        String userInput = input.nextLine();
        while (userInput.isBlank()) {
            System.err.println(str + " Boş bırakılamaz.");
            userInput = input.nextLine();
        }
        return userInput;
    }
}