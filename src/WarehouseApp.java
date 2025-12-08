public class WarehouseApp {
    public static void main(String[] args) {

        Warehouse<Product> centerWarehouse = new Warehouse<>("Merkez Depo", "İstanbul");

        // Sol taraf (Referans) Interface, Sağ taraf (Obje) Servis
        InventoryService service = new WarehouseService(centerWarehouse); // Depoyu servise veriyoruz (Enjeksiyon)

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    Lütfen yapmak istediğini işlemi seçiniz\
                    
                    1-)Ürün tanımlama\
                    
                    2-)Ürün listeleme\
                    
                    3-)Ürün girişi\
                    
                    4-)Ürün çıkışı\
                    
                    5-)Ürünü rafa koyma\
                    
                    6-)Kritik Stokları Listele (<5)\
                    
                    0-)Sistem Çıkış""");

            int selection = ScannerUtils.getValidInteger();

            switch (selection) {
                case 1: // Ürün tanımlama
                    service.defineProduct();
                    break;
                case 2: // Ürün listeleme
                    service.listProducts();
                    ScannerUtils.pressEnter();
                    break;
                case 3: // Ürün girişi
                    service.stockIn();
                    break;
                case 4: // Ürün çıkışı
                    service.stockOut();
                    break;
                case 5: // Ürünü rafa koyma
                    service.assignShelf();
                    break;
                case 6: //Kritik stokları listeleme
                    service.listLowStocks();
                    break;
                case 0: // Sistemden Çıkış
                    isRunning = false;
                    break;
                default:
                    System.out.println("Lütfen geçerli bir sayı giriniz");
                    ScannerUtils.pressEnter();
            }
        }
    }
}