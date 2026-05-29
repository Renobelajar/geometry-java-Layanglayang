package kalkulatorlayanglayang;

public abstract class LayangLayang implements Runnable, Geometri2D {
 // Atribut (ciri-ciri layang-layang)
    public double d1;           // diagonal 1
    public double d2;           // diagonal 2
    public double sisiPendek;   // sisi pendek
    public double sisiPanjang;  // sisi panjang
    
    // Atribut hasil perhitungan (langsung, bukan hasilXXX)
    public double luas;
    public double keliling;
    
    // Constructor
    public LayangLayang(double d1, double d2, double sisiPendek, double sisiPanjang) {
        this.d1 = d1;
        this.d2 = d2;
        this.sisiPendek = sisiPendek;
        this.sisiPanjang = sisiPanjang;
    }
    
    // Method hitung luas (pakai parameter dari atribut)
    @Override
    public double hitungLuas() {
        luas = (d1 * d2) / 2;
        return luas;
    }
    
    // Method hitung keliling (pakai parameter dari atribut)
    @Override
    public double hitungKeliling() {
        keliling = 2 * (sisiPendek + sisiPanjang);
        return keliling;
    }
    
    // Method run() dari Runnable - dieksekusi saat thread.start()
    @Override
    public void run() {
        // Menghitung luas dan keliling dalam thread
        hitungLuas();
        hitungKeliling();
        System.out.println("Thread Layanglayang selesai: d1=" + d1 + ", d2=" + d2);
    }
}