public class GameBerbayar extends Game {
    private double harga; //tambahan atribut harga

    //constructor children
    public GameBerbayar(String[] info, double harga) {
        super(info); //super berarti manggil constructor parent, biar info (atribut) pada parent bisa diisi
        this.harga = harga;
    }

    @Override //melakukan override pada fungsi kosong di class parent
    public void tampilkanInfo() {
        System.out.printf("[BERBAYAR] %s, Rp%.2f - (%d) [%s]\n", nama, harga, idGame, tersediaDiGamePass ? "GamePass" : "Beli Langsung");
    }

    public double getHarga() { //getter harga
        return harga;
    }
}
