import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static final Admin admin = new Admin(1, "admin", "admin@steam.com", "1234"); //konstan untuk informasi admin
    static Scanner scanner = new Scanner(System.in); //scanner keyboard
    static ArrayList<Game> daftarGame = new ArrayList<>(); //array list "daftarGame" untuk menyimpas class Game dan childrennya
    static GamePass gamePass = new GamePass(1, "Premium Pass", 50000);
    static FileOperation fileOperation = new FileOperation();

    public static void main(String[] args) {
        daftarGame = fileOperation.loadGames(); //memasukan data pada file ke list array

        int pilihan; //variabel untuk pilihan menu

        do {
            //tampilan menu
            System.out.println("\n==== MENU ADMIN ====");
            System.out.println("1. Tambah Game Gratis");
            System.out.println("2. Tambah Game Berbayar");
            System.out.println("3. Tampilkan Semua Game");
            System.out.println("4. Ganti Nama Game");
            System.out.println("5. Hapus Game");
            System.out.println("6. Tambah Game ke Gamepass");
            System.out.println("7. Tampilkan GamePass");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            //validasi input
            while (!scanner.hasNextInt()) {
                System.out.println("Input harus berupa angka!");
                System.out.print("Pilih: ");
                scanner.next();
            }

            //input user
            pilihan = scanner.nextInt();
            //kasi jeda biar menu tidak langsung keprint
            scanner.nextLine();

            switch (pilihan) {
                case 1 :
                    //bikin object baru sekaligus input ke array list, berdasarkan informasi pada fungsi inputGameInfo()
                    GameGratis gg = new GameGratis(inputGameInfo());
                    admin.tambahGame(gg);
                    fileOperation.addGame(gg);  //masukan ke file
                    pause();
                    break;
                case 2 :
                    String[] info = inputGameInfo(); //masukan info
                    System.out.print("Harga: ");
                    while (!scanner.hasNextDouble()) {  //validasi harus angka (double)
                        System.out.println("Harga harus berupa angka!");
                        System.out.print("Harga: ");
                        scanner.next();
                    }
                    double harga = scanner.nextDouble();  //inputan harga game
                    scanner.nextLine();
                    GameBerbayar gb = new GameBerbayar(info, harga);  //membuat objek baru
                    admin.tambahGame(gb);  //memanggil fungsi pada admin menambah game ke list
                    fileOperation.addGame(gb); //memasukan game ke file
                    pause();
                    break;
                case 3 :
                    admin.tampilkanDaftarGame();  //memanggil fungsi untuk menampilkan seluruh list game
                    pause();
                    break;
                case 4 :
                    System.out.print("Masukkan ID game yang ingin diubah: ");
                    int idUbah = scanner.nextInt(); scanner.nextLine();
                    Game gameUbah = cariGame(idUbah);

                    if (gameUbah != null) {
                        System.out.print("Nama baru: ");
                        String namaBaru = scanner.nextLine();
                        gameUbah.setNama(namaBaru);
                        fileOperation.updateGame(idUbah, gameUbah); //update file
                        System.out.println("Nama game berhasil diubah.");
                    } else {
                        System.out.println("Game tidak ditemukan.");
                    }
                    pause();
                    break;
                case 5 :
//                    admin.tampilkanDaftarGame();   //memanggil fungsi untuk menampilkan seluruh list game
                    System.out.print("Masukkan ID game yang ingin dihapus: ");
                    int idHapus = scanner.nextInt(); scanner.nextLine();  //masukan id game yang ingin dihapus
                    Game gameHapus = cariGame(idHapus);
                    if (gameHapus != null) {                //cek, id harus ada (bukan null)
                        daftarGame.remove(gameHapus);       //menghapus dari list game
                        fileOperation.deleteGame(idHapus);  //menghapus pada file
                        if (gameHapus instanceof GameBerbayar) {
                            gamePass.hapusGame((GameBerbayar) gameHapus);
                        }
                        System.out.println("Game berhasil dihapus.");
                    } else {
                        System.out.println("Game tidak ditemukan.");
                    }
                    pause();
                    break;
                case 6 :
//                    admin.tampilkanDaftarGame();  //memanggil fungsi untuk menampilkan seluruh list game
                    System.out.print("Masukkan ID game yang ingin dimasukkan ke GamePass: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("ID harus berupa angka!");
                        System.out.print("Masukkan ID: ");
                        scanner.next();
                    }
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    boolean ditemukan = false; //variabel boolean
                    for (Game g : daftarGame) {
                        if (g instanceof GameBerbayar && g.getIdGame() == id) { //ngecek apa bener ada di game berbayar
                            gamePass.tambahGame((GameBerbayar) g);  //memasukan ke list gamepass
                            g.setTersediaDiGamePass(true);          //set status tersedua
                            System.out.println("Game berhasil ditambahkan ke GamePass.");
                            ditemukan = true; //kalau game ditemukan maka akan true, kalo tidak, akan berakhir dengan false
                            break;
                        }
                    }
                    if (!ditemukan) {  //kondisi game tidak ditemukan
                        System.out.println("Game tidak ditemukan atau bukan game berbayar.");
                    }
                    pause();
                    break;
                case 7 :
                    gamePass.tampilkanDaftarGame();  //menampilkan semua game dalam gamepass
                    pause();
                    break;
                case 0 :
                    //case keluar
                    fileOperation.saveAll(daftarGame);   //save semua state program ke file
                    System.out.println("Data berhasil disimpan ke file.");
                    System.out.println("\n==== SESI BERAKHIR ====");
                    break;
                default :
                    //case pilihan tidak benar
                    System.out.println("Pilihan tidak valid!");
                    pause();
            }
        } while (pilihan != 0); //selama bukan 0, maka akan tetap diloop
    }

    //fungsi untuk minta informasi game
    private static String[] inputGameInfo() {
        System.out.print("ID Game: ");
        while (!scanner.hasNextInt()) {
            System.out.println("ID harus berupa angka!");
            System.out.print("ID Game: ");
            scanner.next();
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nama Game: ");
        String nama = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Kategori: ");
        String kategori = scanner.nextLine();
        return new String[]{String.valueOf(id), nama, deskripsi, kategori};
        //konversi int ke string
    }

    //kasi jeda biar menu tidak langsung keprint
    private static void pause() {
        System.out.print("\nTekan ENTER untuk kembali ke menu...");
        scanner.nextLine();
    }

    //mencari id game
    private static Game cariGame(int id) {
        for (Game g : daftarGame) {             //itterate list; jika temukan id direturn, jika tidak return null
            if (g.getIdGame() == id) return g;
        }
        return null;
    }

    private static void sinkronkanGamePass() {  //melakukan sinkronisasi gamepass
        for (Game g : daftarGame) {
            if (g instanceof GameBerbayar) {
                GameBerbayar gb = (GameBerbayar) g;
                if (gb.isTersediaDiGamePass()) {    //jika status tersedia, dimasukan ke gamepass
                    gamePass.tambahGame(gb);
                }
            }
        }
    }
}
