import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class UserLoginServiceTest {

    private UserLoginService loginService;

    @BeforeEach
    public void setUp() {
        loginService = new UserLoginService();
    }

    // Test Case 1: Valid login credentials
    @Test
    public void testValidLogin() {
        String result = loginService.login("admin", "password123");
        Assertions.assertEquals("Login successful! Redirecting to dashboard...", result);
    }

    // Test Case 2: Invalid login credentials
    @Test
    public void testInvalidLogin() {
        String result = loginService.login("admin", "wrongPassword");
        assertEquals("Incorrect username or password", result);
    }

    // Test Case 3: Lock user after 3 failed login attempts
    @Test
    public void testLockUserAfterThreeFailedAttempts() {
        loginService.login("admin", "wrongPassword"); // 1st failed attempt
        loginService.login("admin", "wrongPassword"); // 2nd failed attempt
        loginService.login("admin", "wrongPassword"); // 3rd failed attempt
        String result = loginService.login("admin", "password123");
        assertEquals("Account locked due to too many failed attempts", result);
    }

    // Test Case 4: Handle empty username/password
    @Test
    public void testEmptyUsernameOrPassword() {
        String result1 = loginService.login("", "password123");
        assertEquals("Username or password cannot be empty", result1);

        String result2 = loginService.login("admin", "");
        assertEquals("Username or password cannot be empty", result2);

        String result3 = loginService.login("", "");
        assertEquals("Username or password cannot be empty", result3);
    }
}

