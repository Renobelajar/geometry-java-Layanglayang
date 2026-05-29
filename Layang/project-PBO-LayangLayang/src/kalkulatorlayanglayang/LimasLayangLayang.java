package kalkulatorlayanglayang;

/**
 * LIMAS LAYANG-LAYANG
 * - Alas berbentuk layang-layang
 * - Volume = 1/3 × Luas Alas × Tinggi Limas
 * - Luas Permukaan = Luas Alas + Jumlah 4 sisi tegak (segitiga)
 */
public class LimasLayangLayang extends LayangLayang implements Geometri3D, Runnable {
    public double tinggiLimas;
    public double volume;
    public double luasPermukaan;
    
    // Constructor
    public LimasLayangLayang(double d1, double d2, double sisiPendek, double sisiPanjang, double tinggiLimas) {
        super(d1, d2, sisiPendek, sisiPanjang);
        this.tinggiLimas = tinggiLimas;
    }
    
    @Override
    public double hitungVolume() {
        double luasAlas = super.hitungLuas();
        volume = (1.0/3.0) * luasAlas * tinggiLimas;
        return volume;
    }
    
    @Override
    public double hitungLuasPermukaan() {
        double luasAlas = super.hitungLuas();
        double kelilingAlas = super.hitungKeliling();
        
        // Tinggi sisi tegak (apotema) untuk limas layang-layang
        // Rata-rata sisi alas = kelilingAlas / 4
        double rataSisi = kelilingAlas / 4;
        double tinggiSisiTegak = Math.sqrt((tinggiLimas * tinggiLimas) + (rataSisi/2)*(rataSisi/2));
        
        // Luas 4 sisi tegak = 1/2 × keliling × tinggi sisi tegak
        double luasSisiTegak = 0.5 * kelilingAlas * tinggiSisiTegak;
        luasPermukaan = luasAlas + luasSisiTegak;
        return luasPermukaan;
    }
    
    @Override
    public void run() {
        hitungLuas();
        hitungKeliling();
        hitungVolume();
        hitungLuasPermukaan();
        System.out.println("Thread LimasLayanglayang selesai");
    }
}