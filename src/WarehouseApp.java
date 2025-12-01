public class WarehouseApp {
    public static void main(String[] args) {

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    Lütfen yapmak istediğini işlemi seçiniz\
                    
                    1-)Ürün tanımlama\
                    
                    2-)Ürün listeleme\
                    
                    3-)Ürün girişi\
                    
                    4-)Ürün çıkışı\
                    
                    5-)Ürünü rafa koyma\
                    
                    6-)Sistem Çıkış""");

            int selection = WarehouseService.getValidInteger();

            switch (selection) {
                case 1: // Ürün tanımlama
                    WarehouseService.defineProduct();
                    break;
                case 2: // Ürün listeleme
                    WarehouseService.listProducts();
                    WarehouseService.pressEnter();
                    break;
                case 3: // Ürün girişi
                    WarehouseService.stockIn();
                    break;
                case 4: // Ürün çıkışı
                    WarehouseService.stockOut();
                    break;
                case 5: // Ürünü rafa koyma
                    WarehouseService.assignShelf();
                    break;
                case 6: // Sistemden Çıkış
                    isRunning = false;
                    break;
                default:
                    System.out.println("Lütfen geçerli bir sayı giriniz");
                    WarehouseService.pressEnter();
            }
        }
    }
}