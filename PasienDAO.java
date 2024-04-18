package TP2.GUI;

import java.util.List;

public interface PasienDAO {
    void tambahPasien(Pasien pasien);
    void updatePasien(int index, Pasien pasien);
    void hapusPasien(int index);
    List<Pasien> getDaftarPasien();
    Pasien cariPasienByNIK(long nik);
}

