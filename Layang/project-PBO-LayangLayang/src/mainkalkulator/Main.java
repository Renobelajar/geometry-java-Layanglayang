/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainkalkulator;

import kalkulatorlayanglayang.LayangLayang;
import kalkulatorlayanglayang.PrismaLayangLayang;
import kalkulatorlayanglayang.LimasLayangLayang;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==========================================");
        System.out.println("   KALKULATOR LAYANG-LAYANG (MULTITHREAD)");
        System.out.println("==========================================");
        
        // ========== INPUT DATA LAYANG-LAYANG ==========
        System.out.println("\n--- LAYANG-LAYANG (Bangun Datar) ---");
        System.out.print("Masukkan diagonal 1 (d1): ");
        double d1 = scanner.nextDouble();
        System.out.print("Masukkan diagonal 2 (d2): ");
        double d2 = scanner.nextDouble();
        System.out.print("Masukkan sisi pendek: ");
        double sisiPendek = scanner.nextDouble();
        System.out.print("Masukkan sisi panjang: ");
        double sisiPanjang = scanner.nextDouble();
        
        // Buat objek LayangLayang
        LayangLayang layang = new LayangLayang(d1, d2, sisiPendek, sisiPanjang) {};
        
        // Jalankan thread untuk layang-layang
        System.out.println("\n>>> Menjalankan thread Layanglayang...");
        Thread threadLayang = new Thread(layang);
        threadLayang.start();
        
        try {
            threadLayang.join();
        } catch (InterruptedException e) {
            System.out.println("Thread layang-layang terganggu: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Luas Layang-layang: " + layang.luas);
        System.out.println("Keliling Layang-layang: " + layang.keliling);
        
        // ========== INPUT PRISMA LAYANG-LAYANG ==========
        System.out.println("\n--- PRISMA LAYANG-LAYANG ---");
        System.out.print("Masukkan tinggi prisma: ");
        double tinggiPrisma = scanner.nextDouble();
        
        PrismaLayangLayang prisma = new PrismaLayangLayang(d1, d2, sisiPendek, sisiPanjang, tinggiPrisma);
        
        System.out.println(">>> Menjalankan thread PrismaLayanglayang...");
        Thread threadPrisma = new Thread(prisma);
        threadPrisma.start();
        
        try {
            threadPrisma.join();
        } catch (InterruptedException e) {
            System.out.println("Thread prisma terganggu: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Volume Prisma: " + prisma.volume);
        System.out.println("Luas Permukaan Prisma: " + prisma.luasPermukaan);
        
        // ========== INPUT LIMAS LAYANG-LAYANG ==========
        System.out.println("\n--- LIMAS LAYANG-LAYANG ---");
        System.out.print("Masukkan tinggi limas: ");
        double tinggiLimas = scanner.nextDouble();
        
        LimasLayangLayang limas = new LimasLayangLayang(d1, d2, sisiPendek, sisiPanjang, tinggiLimas);
        
        System.out.println(">>> Menjalankan thread LimasLayanglayang...");
        Thread threadLimas = new Thread(limas);
        threadLimas.start();
        
        try {
            threadLimas.join();
        } catch (InterruptedException e) {
            System.out.println("Thread limas terganggu: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Volume Limas: " + limas.volume);
        System.out.println("Luas Permukaan Limas: " + limas.luasPermukaan);
        
        // ========== OUTPUT RINGKASAN ==========
        System.out.println("\n==========================================");
        System.out.println("              RINGKASAN HASIL             ");
        System.out.println("==========================================");
        System.out.printf("Layang-layang : Luas = %.2f, Keliling = %.2f\n", layang.luas, layang.keliling);
        System.out.printf("Prisma        : Volume = %.2f, Luas Permukaan = %.2f\n", prisma.volume, prisma.luasPermukaan);
        System.out.printf("Limas         : Volume = %.2f, Luas Permukaan = %.2f\n", limas.volume, limas.luasPermukaan);
        
        scanner.close();
    }
}