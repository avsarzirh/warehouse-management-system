import java.util.Scanner;

public class ScannerUtils {

    private static final Scanner input = new Scanner(System.in);

    // 2. Private Constructor:
    // Kimse "new ScannerUtils()" diyemesin diye. Bu bir araç kutusu, nesne değil.
    private ScannerUtils() {
    }

    /**
     * Kullanıcının enter tuşuna basmasını bekler.
     * Akışı durdurmak için kullanılır.
     */
    public static void pressEnter() {
        System.out.println("Devam etmek için ENTER tuşuna basınız...");
        input.nextLine();
    }

    /**
     * Kullanıcıdan güvenli bir şekilde tamsayı (int) alır.
     * Kullanıcı harf girerse program çökmez, tekrar sorar.
     */
    public static int getValidInteger() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Hatalı giriş! Lütfen sadece sayısal değer giriniz.");
            }
        }
    }

    /**
     * Kullanıcıdan String alır. Boş geçilmesini engeller.
     * @param fieldName Kullanıcıya hangi alanı girmesi gerektiğini söyler.
     */
    public static String getValidString(String fieldName) {
        String userInput = "";

        while (true) {
            userInput = input.nextLine().trim();

            if (!userInput.isEmpty()) {
                return userInput; // Doluysa döndür ve metodu bitir.
            }
            // 3. Hatalıysa Uyar ve Başa Dön
            System.err.println("Hata: " + fieldName + " alanı boş bırakılamaz. Tekrar giriniz:");
        }
    }
}
