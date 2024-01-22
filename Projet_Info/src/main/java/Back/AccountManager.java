package Back;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private Map<String, User> users;
    private User currentUser;
    private DatabaseAccess databaseAccess;

    public AccountManager(DatabaseAccess databaseAccess) {
        this.users = new HashMap<>();
        this.createAccount("user");
        this.currentUser = users.get("user"); // Set currentUser to the initial user
        this.databaseAccess = databaseAccess;
    }    

    public boolean createAccount(String username) {
        if (username == null || users.containsKey(username)) {
            return false;
        }
        User newUser = new User(username, databaseAccess);
        users.put(username, newUser);
        return true;
    }

    public User switchAccount(String username) {
        if (users.containsKey(username)) {
            currentUser = users.get(username);
            return currentUser; // Return the selected user
        }
        return null;
    }
    public User getCurrentUser() {
        return currentUser;
    }

    // Method to get a fridge by username
    public Frigo getFridge(User user) {
        return user != null ? user.getFridge() : null;
    }
}
