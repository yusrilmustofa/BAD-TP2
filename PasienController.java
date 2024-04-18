package TP2.GUI;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class PasienController {
    private PasienView view;
    private PasienDAO dao;

    public PasienController() {
        view = new PasienView();
        dao = new PasienDAOImpl();
        initController();
    }

    private void initController() {
        view.getTambahButton().addActionListener(e -> tambahPasien());
        view.getUpdateButton().addActionListener(e -> updatePasien());
        view.getHapusButton().addActionListener(e -> hapusPasien());
        view.getExitButton().addActionListener(e -> exitProgram());
    }

    public void start() {
        view.getFrame().setVisible(true);
        updateTable();
    }

    private void updateTable() {
        List<Pasien> daftarPasien = dao.getDaftarPasien();
        view.updateTable(daftarPasien);
    }

    private void tambahPasien() {
        // Buat dialog untuk memasukkan data pasien baru
        JFrame frame = view.getFrame();
        JTextField namaField = new JTextField();
        JTextField alamatField = new JTextField();
        JTextField nikField = new JTextField();
        JTextField tanggalLahirField = new JTextField();
    
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nama:"));
        panel.add(namaField);
        panel.add(new JLabel("Alamat:"));
        panel.add(alamatField);
        panel.add(new JLabel("NIK:"));
        panel.add(nikField);
        panel.add(new JLabel("Tanggal Lahir (YYYY-MM-DD):"));
        panel.add(tanggalLahirField);
    
        int result = JOptionPane.showConfirmDialog(frame, panel, "Tambah Pasien",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        // Jika tombol OK ditekan dan data dimasukkan
        if (result == JOptionPane.OK_OPTION) {
            String nama = namaField.getText();
            String alamat = alamatField.getText();
            long nik = Long.parseLong(nikField.getText());
            String tanggalLahirText = tanggalLahirField.getText();
            
            // Parsing tanggal lahir
            Date tanggalLahir = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                tanggalLahir = dateFormat.parse(tanggalLahirText);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(frame, "Format tanggal lahir tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Buat objek pasien baru
            Pasien pasienBaru = new Pasien(nama, alamat, nik, tanggalLahir);
            
            // Cek apakah NIK sudah ada dalam daftar pasien
            if (dao.cariPasienByNIK(nik) != null) {
                JOptionPane.showMessageDialog(frame, "NIK sudah ada dalam database.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Tambahkan pasien baru ke dalam daftar pasien
            dao.tambahPasien(pasienBaru);
            
            // Perbarui tabel pasien pada antarmuka pengguna
            updateTable();
            
            JOptionPane.showMessageDialog(frame, "Pasien berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    

    private void updatePasien() {
        // Dapatkan indeks baris yang dipilih di tabel
        int rowIndex = view.getSelectedRowIndex();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(view.getFrame(), "Pilih pasien yang ingin diupdate.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Dapatkan pasien yang dipilih dari indeks baris
        List<Pasien> daftarPasien = dao.getDaftarPasien();
        Pasien pasienTerpilih = daftarPasien.get(rowIndex);
    
        // Buat dialog untuk memperbarui data pasien
        JFrame frame = view.getFrame();
        JTextField namaField = new JTextField(pasienTerpilih.getNama());
        JTextField alamatField = new JTextField(pasienTerpilih.getAlamat());
        JTextField nikField = new JTextField(Long.toString(pasienTerpilih.getNik()));
        JTextField tanggalLahirField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(pasienTerpilih.getTanggalLahir()));
    
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nama:"));
        panel.add(namaField);
        panel.add(new JLabel("Alamat:"));
        panel.add(alamatField);
        panel.add(new JLabel("NIK:"));
        panel.add(nikField);
        panel.add(new JLabel("Tanggal Lahir (YYYY-MM-DD):"));
        panel.add(tanggalLahirField);
    
        int result = JOptionPane.showConfirmDialog(frame, panel, "Update Pasien",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        // Jika tombol OK ditekan dan data dimasukkan
        if (result == JOptionPane.OK_OPTION) {
            String nama = namaField.getText();
            String alamat = alamatField.getText();
            long nik = Long.parseLong(nikField.getText());
            String tanggalLahirText = tanggalLahirField.getText();
    
            // Parsing tanggal lahir
            Date tanggalLahir = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                tanggalLahir = dateFormat.parse(tanggalLahirText);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(frame, "Format tanggal lahir tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Buat objek pasien baru
            Pasien pasienUpdate = new Pasien(nama, alamat, nik, tanggalLahir);
    
            // Perbarui pasien di dalam daftar pasien
            dao.updatePasien(rowIndex, pasienUpdate);
    
            // Perbarui tabel pasien pada antarmuka pengguna
            updateTable();
    
            JOptionPane.showMessageDialog(frame, "Pasien berhasil diupdate.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    

    private void hapusPasien() {
        // Dapatkan indeks baris yang dipilih di tabel
        int rowIndex = view.getSelectedRowIndex();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(view.getFrame(), "Pilih pasien yang ingin dihapus.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Konfirmasi pengguna untuk menghapus pasien
        int option = JOptionPane.showConfirmDialog(view.getFrame(), "Apakah Anda yakin ingin menghapus pasien ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Hapus pasien dari daftar pasien
            dao.hapusPasien(rowIndex);
    
            // Perbarui tabel pasien pada antarmuka pengguna
            updateTable();
    
            JOptionPane.showMessageDialog(view.getFrame(), "Pasien berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void exitProgram() {
        int option = JOptionPane.showConfirmDialog(view.getFrame(), "Apakah Anda yakin ingin keluar dari program?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
}

