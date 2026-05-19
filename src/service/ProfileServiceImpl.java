package service;

import classes.User;
import exceptions.InvalidInputException;
import interfaces.ProfileService;

import java.math.BigDecimal;

public class ProfileServiceImpl implements ProfileService {

    @Override
    public void showProfileInfo(User currentUser) {
        System.out.println(currentUser);
    }

    @Override
    public void editProfile(User currentUser, String newName) {
        if (newName == null || newName.trim().length() < 3) {
            throw new InvalidInputException("To'liq ism kamida 3 ta belgidan iborat bo'lishi kerak");
        }

        currentUser.setFullName(newName);
        System.out.println("Profil muvaffaqiyatli yangilandi");
    }

    @Override
    public void changePassword(User currentUser, String oldPassword, String newPassword) {
        if (!currentUser.getPassword().equals(oldPassword)) {
            throw new InvalidInputException("Eski parol xato kiritildi");
        }

        if (newPassword.length() < 6) {
            throw new InvalidInputException("Parol uzunligi kamida 6 ta belgidan iborat bolishi kerak");
        }

        currentUser.setPassword(newPassword);
        System.out.println("Parol muvaffaqiyatli o'zgartirildi");
    }

    @Override
    public void addBalance(User currentUser, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Kiritilgan summa noldan katta bo'lishi kerak");
        }

        BigDecimal newBalance = currentUser.getBalance().add(amount);
        currentUser.setBalance(newBalance);
        System.out.println("Hisobingizga "+amount+" so'm qo'shildi. Hozirgi balans: "+currentUser.getBalance());
    }
}