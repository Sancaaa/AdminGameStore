public class Admin extends User {
    public Admin(int id, String username, String email, String password) {
        super(id, username, email, password); //super berarti manggil constructor parent, biar atribut pada parent bisa diisi
    }

    public void tambahGame(Game g) {
        Main.daftarGame.add(g); //memasukan game (berbayar dan gratis) ke list
        System.out.println("Game berhasil ditambahkan.");
    }

    public void tampilkanDaftarGame() {
        System.out.println("\nDaftar Game:");
        for (Game g : Main.daftarGame) {
            g.tampilkanInfo();  //menampilkan seluruh list game (berbayar dan gratis)
        }
    }
}
