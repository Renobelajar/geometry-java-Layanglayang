package kalkulatorlayanglayang;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Kelas utama program Kalkulator Layang-layang (Console)
 * 
 * Alur program:
 * 1. User memasukkan jumlah data dan jumlah thread
 * 2. Sistem menghasilkan data random (diagonal dan sisi layang-layang)
 * 3. Sistem menghitung dengan multithreading (ExecutorService)
 * 4. Sistem menampilkan waktu eksekusi dan hasil dalam bentuk tabel
 */
public class KalkulatorLayangLayang {

    // KONSTANTA
    static final double MIN_SISI = 5.0;
    static final double MAX_SISI = 50.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("  KALKULATOR LAYANG-LAYANG - MULTITHREADING ");
        System.out.println("============================================");

        // Input jumlah data
        System.out.print("Masukkan jumlah data yang ingin diolah : ");
        int jumlahData = scanner.nextInt();

        // Input jumlah thread
        System.out.print("Masukkan jumlah thread yang ingin digunakan: ");
        int jumlahThread = scanner.nextInt();

        System.out.println("\nMembuat " + jumlahData + " data random...\n");

        // Generate data random untuk Prisma dan Limas Layang-layang
        List<PrismaLayangLayang> listPrisma = buatDataPrisma(jumlahData);
        List<LimasLayangLayang> listLimas = buatDataLimas(jumlahData);

        // HITUNG PRISMA LAYANG-LAYANG
        System.out.println(">>> Menghitung PRISMA LAYANG-LAYANG dengan " + jumlahThread + " thread...");
        long waktuMulaiPrisma = System.currentTimeMillis();
        hitungDenganThreadPrisma(listPrisma, jumlahThread);
        long waktuSelesaiPrisma = System.currentTimeMillis();
        long durasiPrisma = waktuSelesaiPrisma - waktuMulaiPrisma;

        // HITUNG LIMAS LAYANG-LAYANG
        System.out.println(">>> Menghitung LIMAS LAYANG-LAYANG dengan " + jumlahThread + " thread...");
        long waktuMulaiLimas = System.currentTimeMillis();
        hitungDenganThreadLimas(listLimas, jumlahThread);
        long waktuSelesaiLimas = System.currentTimeMillis();
        long durasiLimas = waktuSelesaiLimas - waktuMulaiLimas;

        // TAMPILKAN HASIL
        System.out.println("\n========== WAKTU EKSEKUSI ==========");
        System.out.println("Prisma Layang-layang : " + durasiPrisma + " ms");
        System.out.println("Limas Layang-layang  : " + durasiLimas + " ms");
        System.out.println("Total                : " + (durasiPrisma + durasiLimas) + " ms");

        tampilkanTabelPrisma(listPrisma);
        tampilkanTabelLimas(listLimas);

        scanner.close();
    }

    // ======================== GENERATE DATA RANDOM ========================

    /**
     * Membuat daftar PrismaLayanglayang dengan nilai random
     * Parameter: d1, d2, sisiPendek, sisiPanjang, tinggiPrisma
     */
    static List<PrismaLayangLayang> buatDataPrisma(int jumlah) {
        Random rng = new Random();
        List<PrismaLayangLayang> list = new ArrayList<>();

        for (int i = 0; i < jumlah; i++) {
            double d1 = acak(rng);
            double d2 = acak(rng);
            double sisiPendek = acak(rng);
            double sisiPanjang = acak(rng);
            double tinggiPrisma = acak(rng);

            list.add(new PrismaLayangLayang(d1, d2, sisiPendek, sisiPanjang, tinggiPrisma));
        }
        return list;
    }

    /**
     * Membuat daftar LimasLayanglayang dengan nilai random
     * Parameter: d1, d2, sisiPendek, sisiPanjang, tinggiLimas
     */
    static List<LimasLayangLayang> buatDataLimas(int jumlah) {
        Random rng = new Random();
        List<LimasLayangLayang> list = new ArrayList<>();

        for (int i = 0; i < jumlah; i++) {
            double d1 = acak(rng);
            double d2 = acak(rng);
            double sisiPendek = acak(rng);
            double sisiPanjang = acak(rng);
            double tinggiLimas = acak(rng);

            list.add(new LimasLayangLayang(d1, d2, sisiPendek, sisiPanjang, tinggiLimas));
        }
        return list;
    }

    static double acak(Random rng) {
        return MIN_SISI + (rng.nextDouble() * (MAX_SISI - MIN_SISI));
    }

    // ======================== MULTITHREADING ========================

    static void hitungDenganThreadPrisma(List<PrismaLayangLayang> list, int jumlahThread) {
        ExecutorService pool = Executors.newFixedThreadPool(jumlahThread);

        for (PrismaLayangLayang obj : list) {
            pool.submit(() -> {
                obj.hitungLuas();
                obj.hitungKeliling();
                obj.hitungVolume();
                obj.hitungLuasPermukaan();
            });
        }

        pool.shutdown();
        try {
            boolean selesai = pool.awaitTermination(60, TimeUnit.SECONDS);
            if (!selesai) {
                System.out.println("Peringatan: Ada thread Prisma yang belum selesai!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread pool Prisma terganggu: " + e.getMessage());
        }
    }

    static void hitungDenganThreadLimas(List<LimasLayangLayang> list, int jumlahThread) {
        ExecutorService pool = Executors.newFixedThreadPool(jumlahThread);

        for (LimasLayangLayang obj : list) {
            pool.submit(() -> {
                obj.hitungLuas();
                obj.hitungKeliling();
                obj.hitungVolume();
                obj.hitungLuasPermukaan();
            });
        }

        pool.shutdown();
        try {
            boolean selesai = pool.awaitTermination(60, TimeUnit.SECONDS);
            if (!selesai) {
                System.out.println("Peringatan: Ada thread Limas yang belum selesai!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread pool Limas terganggu: " + e.getMessage());
        }
    }

    // ======================== TAMPILKAN TABEL ========================

    static void tampilkanTabelPrisma(List<PrismaLayangLayang> list) {
        System.out.println("\n");
        System.out.println("==========================================================================================================");
        System.out.println("                    HASIL PERHITUNGAN - PRISMA LAYANG-LAYANG                                              ");
        System.out.println("==========================================================================================================");
        System.out.printf("%-5s | %-7s | %-7s | %-10s | %-10s | %-10s | %-12s | %-12s | %-14s | %-14s%n",
                "No", "d1", "d2", "Sisi Pendek", "Sisi Panjang", "Tinggi Prisma",
                "Luas Alas", "Keliling", "Volume", "Luas Permukaan");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < list.size(); i++) {
            PrismaLayangLayang p = list.get(i);
            System.out.printf("%-5d | %-7.2f | %-7.2f | %-10.2f | %-10.2f | %-10.2f | %-12.2f | %-12.2f | %-14.2f | %-14.2f%n",
                    (i + 1),
                    p.d1, p.d2, p.sisiPendek, p.sisiPanjang, p.tinggiPrisma,
                    p.luas, p.keliling, p.volume, p.luasPermukaan);
        }
        System.out.println("==========================================================================================================");
    }

    static void tampilkanTabelLimas(List<LimasLayangLayang> list) {
        System.out.println("\n");
        System.out.println("==========================================================================================================");
        System.out.println("                     HASIL PERHITUNGAN - LIMAS LAYANG-LAYANG                                              ");
        System.out.println("==========================================================================================================");
        System.out.printf("%-5s | %-7s | %-7s | %-10s | %-10s | %-10s | %-12s | %-12s | %-14s | %-14s%n",
                "No", "d1", "d2", "Sisi Pendek", "Sisi Panjang", "Tinggi Limas",
                "Luas Alas", "Keliling", "Volume", "Luas Permukaan");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < list.size(); i++) {
            LimasLayangLayang l = list.get(i);
            System.out.printf("%-5d | %-7.2f | %-7.2f | %-10.2f | %-10.2f | %-10.2f | %-12.2f | %-12.2f | %-14.2f | %-14.2f%n",
                    (i + 1),
                    l.d1, l.d2, l.sisiPendek, l.sisiPanjang, l.tinggiLimas,
                    l.luas, l.keliling, l.volume, l.luasPermukaan);
        }
        System.out.println("==========================================================================================================");
    }
}