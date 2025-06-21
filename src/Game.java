//abtract berarti cuma bisa diinisialisasi dari children
public abstract class Game {
    protected int idGame;   //list atribut
    protected String nama;
    protected String deskripsi;
    protected String kategori;
    protected boolean tersediaDiGamePass;

    //constructor parent, dipanggil saat melakukan pemanggilan array info di children
    public Game(String[] info) {
        this.idGame = Integer.parseInt(info[0]); //konversi string ke int
        this.nama = info[1];
        this.deskripsi = info[2];
        this.kategori = info[3];
        this.tersediaDiGamePass = false;
    }

    public abstract void tampilkanInfo(); //diubah pada children

    public void setTersediaDiGamePass(boolean status) { //setter status
        this.tersediaDiGamePass = status;
    }

    //setter
    public void setNama(String nama) {
        this.nama = nama;
    }

    //getter
    public int getIdGame() { return idGame; }
    public String getNama() { return nama; }
    public String getDeskripsi() { return deskripsi; }
    public String getKategori() { return kategori; }
    public boolean isTersediaDiGamePass() { return tersediaDiGamePass; }
}
