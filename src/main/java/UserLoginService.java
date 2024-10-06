import java.util.HashMap;

public class UserLoginService {

    private HashMap<String,Account> accountMap;

    public UserLoginService() {
        Account firstAccount = new Account("admin","password123");
        accountMap = new HashMap<>();
        accountMap.put(firstAccount.username,firstAccount);

    }

    public String login(String username, String password) {

        if (username.length() == 0 || password.length() == 0) {
            return "Username or password cannot be empty";
        }

        Account user = accountMap.get(username);
        if (user == null) {
            return "Incorrect username or password";
        }
        else if (user.getLoginAttempts() > 2){
            return "Account locked due to too many failed attempts";
        }
        else {
            if (user.verifyPassword(password)){
                return "Login successful! Redirecting to dashboard...";
            } else {
                user.addAttempt();
                return "Incorrect username or password";
            }
        }
    }

}
