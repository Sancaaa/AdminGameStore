import java.io.*;
import java.util.ArrayList;

public class FileOperation {
    private static final String FILE_NAME = "games.txt";

    // CREATE
    public void addGame(Game game) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            if (game instanceof GameBerbayar) {
                GameBerbayar g = (GameBerbayar) game;
                writer.write("BERBAYAR|" + g.getIdGame() + "|" + g.getNama() + "|" + g.getDeskripsi()
                        + "|" + g.getKategori() + "|" + g.getHarga());
            } else if (game instanceof GameGratis) {
                GameGratis g = (GameGratis) game;
                writer.write("GRATIS|" + g.getIdGame() + "|" + g.getNama() + "|" + g.getDeskripsi()
                        + "|" + g.getKategori());
            }
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Gagal menambah game: " + e.getMessage());
        }
    }

    // READ
    public ArrayList<Game> loadGames() {
        ArrayList<Game> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p[0].equals("GRATIS")) {
                    list.add(new GameGratis(new String[]{p[1], p[2], p[3], p[4]}));
                } else if (p[0].equals("BERBAYAR")) {
                    double harga = Double.parseDouble(p[5]);
                    list.add(new GameBerbayar(new String[]{p[1], p[2], p[3], p[4]}, harga));
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
        return list;
    }

    // UPDATE
    public boolean updateGame(int id, Game updatedGame) {
        ArrayList<Game> games = loadGames();
        boolean found = false;
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getIdGame() == id) {
                games.set(i, updatedGame);
                found = true;
                break;
            }
        }
        if (found) saveAll(games);
        return found;
    }

    // DELETE
    public boolean deleteGame(int id) {
        ArrayList<Game> games = loadGames();
        boolean removed = games.removeIf(game -> game.getIdGame() == id);
        if (removed) saveAll(games);
        return removed;
    }

    // save semua state program ke file
    public void saveAll(ArrayList<Game> games) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Game game : games) {
                if (game instanceof GameBerbayar) {
                    GameBerbayar g = (GameBerbayar) game;
                    writer.write("BERBAYAR|" + g.getIdGame() + "|" + g.getNama() + "|" + g.getDeskripsi()
                            + "|" + g.getKategori() + "|" + g.getHarga());
                } else if (game instanceof GameGratis) {
                    GameGratis g = (GameGratis) game;
                    writer.write("GRATIS|" + g.getIdGame() + "|" + g.getNama() + "|" + g.getDeskripsi()
                            + "|" + g.getKategori());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan ulang file: " + e.getMessage());
        }
    }
}
