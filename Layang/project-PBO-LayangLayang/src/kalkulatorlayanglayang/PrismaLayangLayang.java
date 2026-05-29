package kalkulatorlayanglayang;

/**
 * PRISMA LAYANG-LAYANG
 * - Alas berbentuk layang-layang
 * - Volume = Luas Alas × Tinggi Prisma
 * - Luas Permukaan = (2 × Luas Alas) + (Keliling Alas × Tinggi Prisma)
 */
public class PrismaLayangLayang extends LayangLayang implements Geometri3D, Runnable {
    public double tinggiPrisma;
    public double volume;
    public double luasPermukaan;
    
    // Constructor
    public PrismaLayangLayang(double d1, double d2, double sisiPendek, double sisiPanjang, double tinggiPrisma) {
        super(d1, d2, sisiPendek, sisiPanjang);
        this.tinggiPrisma = tinggiPrisma;
    }
    
    @Override
    public double hitungVolume() {
        // Luas alas sudah dihitung dari class induk
        double luasAlas = super.hitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }
    
    @Override
    public double hitungLuasPermukaan() {
        double luasAlas = super.hitungLuas();
        double kelilingAlas = super.hitungKeliling();
        luasPermukaan = (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
        return luasPermukaan;
    }
    
    @Override
    public void run() {
        // Eksekusi dalam thread
        hitungLuas();
        hitungKeliling();
        hitungVolume();
        hitungLuasPermukaan();
        System.out.println("Thread PrismaLayanglayang selesai");
    }
}