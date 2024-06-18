package perfil;

import java.util.ArrayList;
import Client;
public abstract class AbstractUserHandler {
    protected ArrayList<User> users;

    public AbstractUserHandler() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public abstract void showUserDetails(User user);
}
