package TP2.GUI;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class PasienTableModel extends AbstractTableModel {
    private List<Pasien> daftarPasien;
    private final String[] columns = {"Nama", "Alamat", "NIK", "Tanggal Lahir"};

    public PasienTableModel(List<Pasien> daftarPasien) {
        this.daftarPasien = daftarPasien;
    }

    @Override
    public int getRowCount() {
        return daftarPasien.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pasien pasien = daftarPasien.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pasien.getNama();
            case 1:
                return pasien.getAlamat();
            case 2:
                return pasien.getNik();
            case 3:
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
                return dateFormat.format(pasien.getTanggalLahir());
            default:
                return null;
        }
    }
}

