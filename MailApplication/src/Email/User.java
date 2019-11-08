package Email;

public class User {
    private String usersMailAdress;
    private String usersPassword;

    public User(String usersMailAdress,String usersPassword){
        this.usersMailAdress=usersMailAdress;
        this.usersPassword=usersPassword;
    }

    public void setUsersMailAdress(String usersMailAdress) {
        this.usersMailAdress = usersMailAdress;
    }

    public void setUsersPassword(String usersPassword) {
        this.usersPassword = usersPassword;
    }

    public String getUsersMailAdress() {
        return usersMailAdress;
    }

    public String getUsersPassword() {
        return usersPassword;
    }
}
