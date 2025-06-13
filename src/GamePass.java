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

    //setter, menambahkan game yang masuk ke list gamepass
    public void tambahGame(GameBerbayar game) {
        daftarGame.add(game);
    }

    //print list game dalam gamepass
    public void tampilkanDaftarGame() {
        System.out.println("Game dalam GamePass:");
        for (GameBerbayar g : daftarGame) {
            g.tampilkanInfo();
        }
    }
}
