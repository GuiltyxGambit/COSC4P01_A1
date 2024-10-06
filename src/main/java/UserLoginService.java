import java.util.HashMap;

public class UserLoginService {

    private HashMap<String,String> userPasswords;
    private HashMap<String,Integer> userLoginAttempts;

    public UserLoginService() {
        userPasswords = new HashMap<>();
        userLoginAttempts = new HashMap<>();

        userPasswords.put("admin","password123");
        userLoginAttempts.put("admin",0);
    }

    public String login(String username, String password) {

        if (username.length() == 0 || password.length() == 0) {
            return "Username or password cannot be empty";
        }
        else {
            if (userLoginAttempts.get(username) > 2) {
                return "Account locked due to too many failed attempts";
            }
            if (verifyUser(username, password)) {
                return "Login successful! Redirecting to dashboard...";
            } else {
                userLoginAttempts.replace(username, userLoginAttempts.get(username) + 1);
                return "Incorrect username or password";
            }
        }
    }

    private Boolean verifyUser(String username, String password) {
        String str = userPasswords.get(username);
        if (str.compareTo(password)==0) {
            return true;
        } else {
            return false;
        }
    }
}
