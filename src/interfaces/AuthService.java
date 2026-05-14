package interfaces;

import classes.User;

import java.util.ArrayList;

public interface AuthService {
    User login(String phoneNumber,String password);
    User register(User user);
    ArrayList<User> getUsers();
}
