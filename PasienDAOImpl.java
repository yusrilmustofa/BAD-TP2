package TP2.GUI;

import java.util.ArrayList;
import java.util.List;

public class PasienDAOImpl implements PasienDAO {
    private List<Pasien> daftarPasien;

    public PasienDAOImpl() {
        daftarPasien = new ArrayList<>();
    }

    @Override
    public void tambahPasien(Pasien pasien) {
        daftarPasien.add(pasien);
    }

    @Override
    public void updatePasien(int index, Pasien pasien) {
        daftarPasien.set(index, pasien);
    }

    @Override
    public void hapusPasien(int index) {
        daftarPasien.remove(index);
    }

    @Override
    public List<Pasien> getDaftarPasien() {
        return daftarPasien;
    }

    @Override
    public Pasien cariPasienByNIK(long nik) {
        for (Pasien pasien : daftarPasien) {
            if (pasien.getNik() == nik) {
                return pasien;
            }
        }
        return null;
    }
}

