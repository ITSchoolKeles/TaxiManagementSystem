package interfaces;
import classes.User;

import java.math.BigDecimal;

public interface ProfileService {
    void showProfileInfo(User currentUser);
    void editProfile(User currentUser, String newName);
    void changePassword(User currentUser, String oldPassword, String newPassword);
    void addBalance(User currentUser, BigDecimal amount);
}