package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HistoriTransaksi {
    private String jenisTransaksi;
    private double jumlah;
    private LocalDateTime waktuTransaksi;
    private String pengirim;
    private String penerima;
    private String nomorRekening;

    ArrayList<HistoriTransaksi> historiTransaksi = new ArrayList<>();
    
  

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public double getJumlah() {
        return jumlah;
    }

    public LocalDateTime getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public String getPengirim() {
        return pengirim;
    }

    public String getPenerima() {
        return penerima;
    }
    public HistoriTransaksi setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
        return this;
    }
    
    public HistoriTransaksi setJumlah(double jumlah) {
        this.jumlah = jumlah;
        return this;
    }
    
    public HistoriTransaksi setWaktuTransaksi(LocalDateTime waktuTransaksi) {
        this.waktuTransaksi = waktuTransaksi;
        return this;
    }
    
    public HistoriTransaksi setPengirim(String pengirim) {
        this.pengirim = pengirim;
        return this;
    }
    
    public HistoriTransaksi setPenerima(String penerima) {
        this.penerima = penerima;
        return this;
    }
    
    public ArrayList<HistoriTransaksi> getHistoriTransaksi() {
        return this.historiTransaksi;
    }
    public String getNomorRekening() {
        return this.nomorRekening;
    }
 
    public HistoriTransaksi setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
        return this;
    }
}
