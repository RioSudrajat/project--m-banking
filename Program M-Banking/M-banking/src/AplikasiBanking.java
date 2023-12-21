import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Controller.Controller;
import Model.Pengguna;

public class AplikasiBanking {
    private static Pengguna penggunaSaatIni;

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        controller.setUp();
        boolean lanjut = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (lanjut) {
            tampilkanMenuUtama();
            System.out.print("Pilih menu (1: Login, 2: Keluar): ");
            String pilihan = reader.readLine();

            switch (pilihan) {
                case "1":
                    controller.login(reader);
                    penggunaSaatIni = controller.getPenggunaSaatIni();
                    if (penggunaSaatIni != null) {
                        menuBanking(reader, controller);
                    }
                    break;
                case "2":
                    lanjut = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }

        System.out.println("Terima kasih!");
    }

    private static void tampilkanMenuUtama() {
        System.out.println("===== MENU UTAMA =====");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
    }

    private static void menuBanking(BufferedReader input, Controller controller) throws IOException {
        boolean lanjutBanking = true;

        while (lanjutBanking) {
            tampilkanMenuBanking();
            System.out.print("Pilih menu banking (1: Transfer Uang, 2: Lihat Histori, 3: Lihat Saldo, 4: Logout): ");
            String pilihanBanking = input.readLine();

            switch (pilihanBanking) {
                case "1":
                    controller.transferUang(input);
                    break;
                case "2":
                    controller.lihatHistoriTransaksi();
                    break;
                case "3":
                    controller.lihatSaldo();
                    break;
                case "4":
                    lanjutBanking = false;
                    penggunaSaatIni = null;
                    System.out.println("Logout Berhasil.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }

    private static void tampilkanMenuBanking() {
        System.out.println("===== MENU BANKING =====");
        System.out.println("1. Transfer Uang");
        System.out.println("2. Lihat Histori Transaksi");
        System.out.println("3. Lihat Saldo");
        System.out.println("4. Logout");
    }
}
