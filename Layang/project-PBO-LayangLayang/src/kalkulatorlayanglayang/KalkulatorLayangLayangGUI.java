package kalkulatorlayanglayang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class KalkulatorLayangLayangGUI extends JFrame {

    // Komponen Input
    private JTextField tfD1, tfD2, tfSisiPendek, tfSisiPanjang;
    private JTextField tfTinggiPrisma, tfTinggiLimas;
    
    // Komponen Hasil
    private JLabel lblLuas, lblKeliling;
    private JLabel lblVolumePrisma, lblLuasPermukaanPrisma;
    private JLabel lblVolumeLimas, lblLuasPermukaanLimas;
    
    // Tombol
    private JButton btnHitung, btnRandom, btnReset;

    public KalkulatorLayangLayangGUI() {
        setTitle("Kalkulator Layang-layang");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Panel Utama dengan padding
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(240, 240, 245));
        
        mainPanel.add(buatPanelInput());
        mainPanel.add(buatPanelHasil());
        
        add(mainPanel, BorderLayout.CENTER);
        add(buatFooter(), BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    // ==================== PANEL INPUT ====================
    private JPanel buatPanelInput() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Title
        JLabel title = new JLabel("INPUT DATA");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));
        
        // === LAYANG-LAYANG ===
        panel.add(buatLabel("LAYANG-LAYANG", new Color(70, 130, 200)));
        panel.add(buatInputRow("Diagonal 1 (d1):", tfD1 = new JTextField()));
        panel.add(buatInputRow("Diagonal 2 (d2):", tfD2 = new JTextField()));
        panel.add(buatInputRow("Sisi Pendek:", tfSisiPendek = new JTextField()));
        panel.add(buatInputRow("Sisi Panjang:", tfSisiPanjang = new JTextField()));
        panel.add(Box.createVerticalStrut(15));
        
        // === PRISMA ===
        panel.add(buatLabel("PRISMA LAYANG-LAYANG", new Color(70, 130, 200)));
        panel.add(buatInputRow("Tinggi Prisma:", tfTinggiPrisma = new JTextField()));
        panel.add(Box.createVerticalStrut(15));
        
        // === LIMAS ===
        panel.add(buatLabel("LIMAS LAYANG-LAYANG", new Color(70, 130, 200)));
        panel.add(buatInputRow("Tinggi Limas:", tfTinggiLimas = new JTextField()));
        panel.add(Box.createVerticalStrut(25));
        
        // === TOMBOL ===
        JPanel tombolPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        tombolPanel.setOpaque(false);
        tombolPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        btnHitung = new JButton("HITUNG");
        btnHitung.setBackground(new Color(34, 139, 34));
        btnHitung.setForeground(Color.WHITE);
        btnHitung.setFocusPainted(false);
        btnHitung.addActionListener(e -> hitung());
        
        btnRandom = new JButton("RANDOM");
        btnRandom.setBackground(new Color(70, 130, 200));
        btnRandom.setForeground(Color.WHITE);
        btnRandom.setFocusPainted(false);
        btnRandom.addActionListener(e -> randomData());
        
        btnReset = new JButton("RESET");
        btnReset.setBackground(new Color(169, 169, 169));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(e -> reset());
        
        tombolPanel.add(btnHitung);
        tombolPanel.add(btnRandom);
        tombolPanel.add(btnReset);
        
        panel.add(tombolPanel);
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    // ==================== PANEL HASIL ====================
    private JPanel buatPanelHasil() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel title = new JLabel("HASIL PERHITUNGAN");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));
        
        // === LAYANG-LAYANG ===
        panel.add(buatHasilCard("LAYANG-LAYANG",
            new String[]{"Luas:", "Keliling:"},
            new JLabel[]{lblLuas = new JLabel("0"), lblKeliling = new JLabel("0")}));
        panel.add(Box.createVerticalStrut(15));
        
        // === PRISMA ===
        panel.add(buatHasilCard("PRISMA LAYANG-LAYANG",
            new String[]{"Volume:", "Luas Permukaan:"},
            new JLabel[]{lblVolumePrisma = new JLabel("0"), lblLuasPermukaanPrisma = new JLabel("0")}));
        panel.add(Box.createVerticalStrut(15));
        
        // === LIMAS ===
        panel.add(buatHasilCard("LIMAS LAYANG-LAYANG",
            new String[]{"Volume:", "Luas Permukaan:"},
            new JLabel[]{lblVolumeLimas = new JLabel("0"), lblLuasPermukaanLimas = new JLabel("0")}));
        
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    // ==================== FOOTER ====================
    private JPanel buatFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setBackground(new Color(220, 220, 230));
        footer.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        JLabel status = new JLabel("✓ Siap. Masukkan nilai atau klik RANDOM.");
        status.setFont(new Font("Arial", Font.PLAIN, 11));
        footer.add(status);
        
        return footer;
    }
    
    // ==================== HELPER ====================
    private JPanel buatInputRow(String label, JTextField field) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl.setPreferredSize(new Dimension(120, 25));
        
        field.setFont(new Font("Arial", Font.PLAIN, 12));
        
        row.add(lbl, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);
        
        return row;
    }
    
    private JPanel buatHasilCard(String title, String[] labels, JLabel[] values) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(245, 245, 250));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(new Color(70, 130, 200));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(8));
        
        for (int i = 0; i < labels.length; i++) {
            JPanel row = new JPanel(new BorderLayout());
            row.setOpaque(false);
            
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(new Font("Arial", Font.PLAIN, 12));
            
            values[i].setFont(new Font("Arial", Font.BOLD, 14));
            values[i].setForeground(new Color(34, 139, 34));
            values[i].setHorizontalAlignment(SwingConstants.RIGHT);
            
            row.add(lbl, BorderLayout.WEST);
            row.add(values[i], BorderLayout.EAST);
            
            card.add(row);
            if (i < labels.length - 1) card.add(Box.createVerticalStrut(5));
        }
        
        return card;
    }
    
    private JPanel buatLabel(String text, Color color) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 11));
        label.setForeground(color);
        
        p.add(label, BorderLayout.WEST);
        return p;
    }
    
    private double getDouble(JTextField tf) {
    try {
        String text = tf.getText().trim();
        if (text.isEmpty()) return 0;
        
        // SOLUSI: Mengubah karakter koma (,) menjadi titik (.) sebelum di-parse
        text = text.replace(',', '.');
        
        return Double.parseDouble(text);
    } catch (NumberFormatException e) {
        return 0;
    }
}
    
    private void hitung() {
        double d1 = getDouble(tfD1);
        double d2 = getDouble(tfD2);
        double sp = getDouble(tfSisiPendek);
        double sj = getDouble(tfSisiPanjang);
        double tp = getDouble(tfTinggiPrisma);
        double tl = getDouble(tfTinggiLimas);
        
        // Layang-layang
        double luas = (d1 * d2) / 2;
        double keliling = 2 * (sp + sj);
        
        // Prisma
        double volPrisma = luas * tp;
        double lpPrisma = (2 * luas) + (keliling * tp);
        
        // Limas
        double volLimas = (1.0/3.0) * luas * tl;
        double rataSisi = keliling / 4;
        double tinggiSisiTegak = Math.sqrt((tl * tl) + (rataSisi/2)*(rataSisi/2));
        double lpLimas = luas + (0.5 * keliling * tinggiSisiTegak);
        
        // Tampilkan hasil
        lblLuas.setText(String.format("%.2f", luas));
        lblKeliling.setText(String.format("%.2f", keliling));
        lblVolumePrisma.setText(String.format("%.2f", volPrisma));
        lblLuasPermukaanPrisma.setText(String.format("%.2f", lpPrisma));
        lblVolumeLimas.setText(String.format("%.2f", volLimas));
        lblLuasPermukaanLimas.setText(String.format("%.2f", lpLimas));
    }
    
    private void randomData() {
        Random rand = new Random();
        double min = 5, max = 50;
        
        tfD1.setText(String.format("%.2f", min + rand.nextDouble() * (max - min)));
        tfD2.setText(String.format("%.2f", min + rand.nextDouble() * (max - min)));
        tfSisiPendek.setText(String.format("%.2f", min + rand.nextDouble() * (max - min)));
        tfSisiPanjang.setText(String.format("%.2f", min + rand.nextDouble() * (max - min)));
        tfTinggiPrisma.setText(String.format("%.2f", min + rand.nextDouble() * (max - min)));
        tfTinggiLimas.setText(String.format("%.2f", min + rand.nextDouble() * (max - min)));
        
        hitung(); // Langsung hitung setelah random
    }
    
    private void reset() {
        tfD1.setText("");
        tfD2.setText("");
        tfSisiPendek.setText("");
        tfSisiPanjang.setText("");
        tfTinggiPrisma.setText("");
        tfTinggiLimas.setText("");
        
        lblLuas.setText("0");
        lblKeliling.setText("0");
        lblVolumePrisma.setText("0");
        lblLuasPermukaanPrisma.setText("0");
        lblVolumeLimas.setText("0");
        lblLuasPermukaanLimas.setText("0");
    }
    
    // ==================== MAIN ====================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KalkulatorLayangLayangGUI());
    }
}