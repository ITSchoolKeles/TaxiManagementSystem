package interfaces;

import classes.User;

public interface AuthService {
    User login(String phoneNumber,String password);
    User register(User user);
}
