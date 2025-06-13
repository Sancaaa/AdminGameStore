public class GameGratis extends Game {
    //constructor children
    public GameGratis(String[] info) {
        super(info); //super berarti manggil constructor parent, biar info (atribut) pada parent bisa diisi
    }

    @Override //melakukan override pada fungsi kosong di class parent
    public void tampilkanInfo() {
        System.out.printf("[GRATIS] %s (%d) - %s\n", nama, idGame, deskripsi);
    }
}
