package service;

import classes.User;
import enums.UserType;
import exceptions.DataAlreadyException;
import exceptions.InvalidInputException;
import exceptions.LoginException;
import interfaces.AuthService;
import utils.Database;

import java.util.ArrayList;
import java.util.UUID;

public class AuthServiceImpl implements AuthService {
    private final ArrayList<User> usersDatabase = Database.usersDb;
    {
        usersDatabase.add(
                new User(
                        UUID.randomUUID(),
                        "Admin",
                        "+998999999999",
                        null,
                        UserType.ADMIN,
                        null,
                        "ea1202"
                )
        );
    }
    @Override
    public User login(String phoneNumber, String password) {
        if(!phoneNumber.startsWith("+998") || phoneNumber.length() != 13){
            throw new InvalidInputException("Telefon raqam +998 dan boshlanishi va uzunligi 13 ta bolishi kerak");
        }
        if(password.length() < 6){
            throw new InvalidInputException("Parol uzunligi kamida 6 ta belgidan iborat bolishi kerak");
        }
        User userByPhoneNumber = getUserByPhoneNumber(phoneNumber);

        if(userByPhoneNumber == null){
           throw new LoginException("Bunday telefon raqamli foydalanuvchi mavjud emas");
        }
        if(!userByPhoneNumber.getPassword().equals(password)){
            throw new LoginException("Parol xato kiritildi");
        }

        return userByPhoneNumber;
    }

    @Override
    public User register(User user) {
        if(user.getFullName().length() < 3){
            throw new InvalidInputException("To'liq ism kamida 3 ta belgidan iborat bo'lishi kerak");
        }
        if(!user.getPhoneNumber().startsWith("+998") || user.getPhoneNumber().length() != 13){
            throw new InvalidInputException("Telefon raqam +998 dan boshlanishi va uzunligi 13 ta bolishi kerak");
        }
        if(user.getPassword().length() < 6){
            throw new InvalidInputException("Parol uzunligi kamida 6 ta belgidan iborat bolishi kerak");
        }

        // Phone numberni mavjudligiga tekshiramiz
        User userByPhoneNumber = getUserByPhoneNumber(user.getPhoneNumber());
        if(userByPhoneNumber != null){
            throw new DataAlreadyException("Bunday telefon raqam %s allaqachon mavjud".formatted(user.getPhoneNumber()));
        }

        //userni id generatsiya qilib saqlash
        user.setId(UUID.randomUUID());
         usersDatabase.add(user);
        return user;
    }
    private User getUserByPhoneNumber(String phoneNumber){
        for (User user : usersDatabase) {
            if(user.getPhoneNumber().equals(phoneNumber)){
                return user;
            }
        }
        return null;
    }
    // barcha userlarni aylanamiz phoneNUmber mavjud bo'lsa exception otamiz

    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (User user : usersDatabase) {
            if (user.getUserType() != UserType.ADMIN) {
                users.add(user);
            }
        }
        return users;
    }

}
