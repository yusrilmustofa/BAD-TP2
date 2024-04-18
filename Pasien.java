package TP2.GUI;

import java.util.Date;

public class Pasien {
    private String nama;
    private String alamat;
    private long nik;
    private Date tanggalLahir;

    public Pasien(String nama, String alamat, long nik, Date tanggalLahir) {
        this.nama = nama;
        this.alamat = alamat;
        this.nik = nik;
        this.tanggalLahir = tanggalLahir;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public long getNik() {
        return nik;
    }

    public void setNik(long nik) {
        this.nik = nik;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
}


