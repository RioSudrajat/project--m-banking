package Model;

import java.util.ArrayList;

public class Pengguna {
    private String userId;
    private String password;
    private double saldo;
    private String pin;
    private String namaPengguna;
    private String nomorRekening;
    
    public ArrayList<HistoriTransaksi> historiTransaksi = new ArrayList<>();
    
    
    
    public String getNomorRekening() {
       return this.nomorRekening;
   }

   public Pengguna setNomorRekening(String nomorRekening) {
       this.nomorRekening = nomorRekening;
       return this;
   }
    public String getUserId() {
        return this.userId;
    }
    
    public double getSaldo() {
        return this.saldo;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPin() {
        return this.pin;
    }

    public String getNamaPengguna() {
        return this.namaPengguna;
    }

    public ArrayList<HistoriTransaksi> getHistoriTransaksi() {
        return this.historiTransaksi;
    }
    public void addHistoriTransaksi(HistoriTransaksi historiTransaksi) {
        this.historiTransaksi.add(historiTransaksi);
    }

    public double setSaldo(double saldo) {
        this.saldo = saldo;
        return saldo;
    }

    public Pengguna setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    public Pengguna setPassword(String password) {
        this.password = password;
        return this;
    }
    public Pengguna setPin(String pin) {
        this.pin = pin;
        return this;
    }
    public Pengguna setnamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
        return this;
    }

}
