package TP2.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PasienView {
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton tambahButton;
    private JButton updateButton;
    private JButton hapusButton;
    private JButton exitButton;

    public PasienView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Aplikasi Klinik");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        tambahButton = new JButton("Tambah Pasien");
        panel.add(tambahButton);

        updateButton = new JButton("Update Pasien");
        panel.add(updateButton);

        hapusButton = new JButton("Hapus Pasien");
        panel.add(hapusButton);

        exitButton = new JButton("Keluar");
        panel.add(exitButton);

        scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getTambahButton() {
        return tambahButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getHapusButton() {
        return hapusButton;
    }

    public void updateTable(List<Pasien> daftarPasien) {
        PasienTableModel model = new PasienTableModel(daftarPasien);
        table.setModel(model);
    }

    public int getSelectedRowIndex() {
        return table.getSelectedRow();
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
