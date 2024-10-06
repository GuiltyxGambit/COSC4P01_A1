public class Account {
    public String username;
    private final String password;
    private int loginAttempts;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        loginAttempts = 0;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void addAttempt(){
        loginAttempts++;
    }

    public boolean verifyPassword(String password) {
        return this.password.compareTo(password) == 0;
    }
}
