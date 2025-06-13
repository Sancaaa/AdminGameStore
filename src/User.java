public class User {
    protected int idUser; //atribut
    protected String username;
    protected String email;
    protected String password;

    //setter
    public User(int id, String username, String email, String password) {
        this.idUser = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
