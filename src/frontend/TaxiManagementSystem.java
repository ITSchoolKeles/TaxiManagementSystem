package frontend;

import exceptions.FormatException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TaxiManagementSystem {
    private  static final Scanner numberScanner = new Scanner(System.in);
    private  static final Scanner textScanner = new Scanner(System.in);
    public static void main(String[] args) {
        // 1. Authentification ==> register/ login
        // Dashboards ==> admin, user, driver
        // Admin Dashboard ==>
        // Region and City management, travel history, driverHistory, userHistory, profile

        // User Dashboard ==> travel, history, balanceManagement,profil

        // Driver Dashboard ==> history, calculateBalance, profil


        System.out.println("Bizning taksi loyihamizga xush kelibsiz");
        while (true){
        displayAuthentificationMenus();
        boolean isFormatException = true;
        int menuNumber = 0;
        while (isFormatException) {
            try {
               int chosenNumber = getMenuNumber("Menu raqamini kiriting");
                menuNumber = chosenNumber;
                isFormatException = false;
            } catch (FormatException e) {
                numberScanner.next();
              System.err.println(e.getMessage());
            }
        }
        switch (menuNumber) {
            case 1 -> login();
            case 2 -> register();
            case 3 -> System.exit(0);
        }
        }

    }
    public static void login(){
        System.out.println("Bu login sahifasi");
    }
    public static void register(){
        System.out.println("Bu ro'yxatdan o'tish sahifasi");
    }


    public static void displayAuthentificationMenus(){
        System.out.println("-----------------------------------------");
        System.out.println("1. Login");
        System.out.println("2.Register");
        System.out.println("3. Exit");
    }
    private static int getMenuNumber(String message){

        int menuNumber;
        try {
            Thread.sleep(500);
            System.out.print(message + " => ");
             menuNumber = numberScanner.nextInt();
        } catch (InputMismatchException e) {
            throw new FormatException("Iltimos raqam kiriting");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return menuNumber;
    }

}
