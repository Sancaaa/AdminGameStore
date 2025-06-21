import java.util.ArrayList;

public class GamePass {
    private int idPaket; //list atribut
    private String namaPaket;
    private double hargaBulanan;
    private ArrayList<GameBerbayar> daftarGame;

    //constructor
    public GamePass(int id, String nama, double harga) {
        this.idPaket = id;
        this.namaPaket = nama;
        this.hargaBulanan = harga;
        this.daftarGame = new ArrayList<>();
    }

    //menambahkan game yang masuk ke list gamepass
    public void tambahGame(GameBerbayar game) {
        if (!daftarGame.contains(game)) {
            daftarGame.add(game);
        }
    }

    //menghapus game dari list gamepass
    public boolean hapusGame(GameBerbayar game) {
        if (daftarGame.remove(game)) {
            game.setTersediaDiGamePass(false);
            return true;
        }
        return false;
    }

    //print list game dalam gamepass
    public void tampilkanDaftarGame() {
        System.out.println("Game dalam GamePass:");
        for (GameBerbayar g : daftarGame) {
            g.tampilkanInfo();
        }
    }

    //getter
    public int getIdPaket() { return idPaket; }
    public String getNamaPaket() { return namaPaket; }
    public double getHargaBulanan() { return hargaBulanan; }
    public ArrayList<GameBerbayar> getDaftarGame() { return daftarGame; }
}
