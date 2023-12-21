package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

import Model.Pengguna;
import Model.HistoriTransaksi;

public class Controller {
    
    private static HashMap<String, Pengguna> penggunas = new HashMap<>();
    private static Pengguna penggunaSaatIni;

    public void setUp() {
        Pengguna pengguna1 = new Pengguna();
        Pengguna pengguna2 = new Pengguna();
        Pengguna pengguna3 = new Pengguna();
        Pengguna pengguna4 = new Pengguna();
        pengguna1.setUserId("Rio");
        pengguna1.setPassword("Sudrajat");
        pengguna1.setSaldo(1000000);
        pengguna1.setPin("767676");
        pengguna1.setnamaPengguna("RIO FERDANA SUDRAJAT");
        pengguna1.setNomorRekening("1237050057");
        
        pengguna2.setUserId("Sayyid");
        pengguna2.setPassword("Maula");
        pengguna2.setSaldo(1000000);
        pengguna2.setPin("787878");
        pengguna2.setnamaPengguna("SAYYID MAULA RAFSANJANI");
        pengguna2.setNomorRekening("1237050126");
        
        pengguna3.setUserId("Qasim");
        pengguna3.setPassword("imam al-jabar");
        pengguna3.setSaldo(1000000);
        pengguna3.setPin("757575");
        pengguna3.setnamaPengguna("RIO FADHIL RIZQULLAH");
        pengguna3.setNomorRekening("1237050099");
        
        pengguna4.setUserId("amam");
        pengguna4.setPassword("fernanda");
        pengguna4.setSaldo(1000000);
        pengguna4.setPin("797979");
        pengguna4.setnamaPengguna("PILAR ILHAM FERNANDA");
        pengguna4.setNomorRekening("1237050091");

        penggunas.put(pengguna1.getUserId(), pengguna1);
        penggunas.put(pengguna2.getUserId(), pengguna2);
        penggunas.put(pengguna3.getUserId(), pengguna3);
        penggunas.put(pengguna4.getUserId(), pengguna4);
    }

    public void login(BufferedReader input) throws IOException {
        System.out.print("Masukkan username: ");
        String username = input.readLine();
        System.out.print("Masukkan pin: ");
        String pin = input.readLine();

        if (penggunas.containsKey(username)) {
            Pengguna pengguna = penggunas.get(username);
            if (pengguna.getPin().equals(pin)) {
                penggunaSaatIni = pengguna;
                System.out.println("Login berhasil. Selamat datang, " + penggunaSaatIni.getNamaPengguna() + "!");
            } else {
                System.out.println("Password salah. Login gagal.");
            }
        } else {
            System.out.println("Username tidak ditemukan. Login gagal.");
        }
    }

    public void transferUang(BufferedReader input) throws IOException {
        if (penggunaSaatIni == null) {
            System.out.println("Silakan login terlebih dahulu.");
            return;
        }
    
        System.out.print("Masukkan username penerima: ");
        String usernamePenerima = input.readLine();
    
        if (penggunas.containsKey(usernamePenerima)) {
            Pengguna penerima = penggunas.get(usernamePenerima);
            System.out.println("Masukkan nomor rekening yang dituju: ");
            String noRek = input.readLine();

            if(!noRek.equals(penerima.getNomorRekening())){
                System.out.println("Nomor rekening tidak sesuai. ");
                return;
            }
            System.out.print("Masukkan jumlah transfer: ");
            double jumlahTransfer = Double.parseDouble(input.readLine());
            System.out.print("Masukkan password untuk konfirmasi: ");
            String passwordKonfirmasi = input.readLine();
            
            // Check password for security
            if (passwordKonfirmasi.equals(penggunaSaatIni.getPassword())) {
                // Check if there is enough balance for the transfer
                if (jumlahTransfer <= penggunaSaatIni.getSaldo()) {
                    // Update balances for sender and receiver
                    penggunaSaatIni.setSaldo(penggunaSaatIni.getSaldo() - jumlahTransfer);
                    penerima.setSaldo(penerima.getSaldo() + jumlahTransfer);
    
                    // Add transaction history for sender and receiver
                    HistoriTransaksi historiPengirim = new HistoriTransaksi()
                        .setJenisTransaksi("Transfer Keluar")
                        .setJumlah(jumlahTransfer)
                        .setWaktuTransaksi(LocalDateTime.now())
                        .setPengirim(penggunaSaatIni.getNamaPengguna())
                        .setPenerima(penerima.getNamaPengguna())
                        .setNomorRekening(penerima.getNomorRekening());
                        
                        HistoriTransaksi historiPenerima = new HistoriTransaksi()
                        .setJenisTransaksi("Transfer Masuk")
                        .setJumlah(jumlahTransfer)
                        .setWaktuTransaksi(LocalDateTime.now())
                        .setPengirim(penggunaSaatIni.getNamaPengguna())
                        .setPenerima(penerima.getNamaPengguna())
                        .setNomorRekening(penerima.getNomorRekening());
                        
                        
                        penggunaSaatIni.addHistoriTransaksi(historiPengirim);
                        penerima.addHistoriTransaksi(historiPenerima);
    
                    System.out.println("Transfer berhasil. Saldo tersisa: " + penggunaSaatIni.getSaldo());
                } else {
                    System.out.println("Saldo tidak mencukupi untuk transfer.");
                }
            } else {
                System.out.println("Password salah. Transfer uang gagal.");
            }
        } else {
            System.out.println("Username penerima tidak ditemukan.");
        }
    }
    

    public void lihatHistoriTransaksi() {
        if (penggunaSaatIni == null) {
            System.out.println("Silakan login terlebih dahulu.");
            return;
        }
        if (penggunaSaatIni.getHistoriTransaksi() != null) {
        System.out.println("===== HISTORI TRANSAKSI =====");
        for (HistoriTransaksi transaksi : penggunaSaatIni.getHistoriTransaksi()) {
            System.out.println("Jenis Transaksi: " + transaksi.getJenisTransaksi());
            System.out.println("Jumlah: " + transaksi.getJumlah());
            System.out.println("Waktu Transaksi: " + transaksi.getWaktuTransaksi());
            System.out.println("Pengirim: " + transaksi.getPengirim());
            System.out.println("Penerima: " + transaksi.getPenerima());
            System.out.println("No.Rek tujuan: " + transaksi.getNomorRekening());
            System.out.println("----------------------------");
        }
    }
    }

    public void lihatSaldo() {
        if (penggunaSaatIni == null) {
            System.out.println("Silakan login terlebih dahulu.");
            return;
        }

        System.out.println("Saldo saat ini: " + penggunaSaatIni.getSaldo());
    }

    public void setPenggunaSaatIni(Pengguna pengguna) {
        penggunaSaatIni = pengguna;
    }

    public Pengguna getPenggunaSaatIni() {
        return penggunaSaatIni;
    }
}



