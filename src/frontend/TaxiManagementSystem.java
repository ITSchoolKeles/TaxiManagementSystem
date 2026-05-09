package frontend;

import classes.User;
import enums.UserType;
import exceptions.DataAlreadyException;
import exceptions.FormatException;
import exceptions.InvalidInputException;
import interfaces.AuthService;
import service.AuthServiceImpl;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaxiManagementSystem {
    private  static final Scanner numberScanner = new Scanner(System.in);
    private  static final Scanner textScanner = new Scanner(System.in);
    private static final AuthService authService = new AuthServiceImpl();
    private static User currentUser = null;
    public static void main(String[] args) {
        // 1. Authentification ==> register/ login
        // Dashboards ==> admin, user, driver
        // Admin Dashboard ==>
        // Region and City management, travel history, driverHistory, userHistory, profile

        // User Dashboard ==> travel, history, balanceManagement,profil

        // Driver Dashboard ==> history, calculateBalance, profil


        // Register -->
        // 1. ROLE

        // USER --> fullName, phoneNumber,password
        // DRIVER --> fullName, phoneNumber, password, Car car

        // Login --> phoneNumber password



        System.out.println("Bizning taksi loyihamizga xush kelibsiz");
        while (true){
        displayAuthentificationMenus();
        int menuNumber = getMenuNumberInSafeWay("Menu raqamini kiriting");
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
        System.out.println("1.USER");
        System.out.println("2.DRIVER");
        System.out.println("3.Asosiy menuga qaytish");
        int roleMenuNumber = getMenuNumberInSafeWay("Role ni tanlang");

        switch (roleMenuNumber){
            case 1-> registerUser();
            case 2-> registerDriver();
            case 3 -> {
                return;
            }
        }
    }

    private static void registerUser() {
        System.out.println("Bu userni ro'yxatdan o'tkazish menusi ");
        System.out.println("-----------------------------");
        // USER --> fullName, phoneNumber,password

        System.out.print("To'liq ismingizni kiriting -> ");
        String fullName = textScanner.nextLine();

        System.out.print("Telefon raqamingizni kiriting -> ");
        String phoneNumber = textScanner.nextLine();

        System.out.print("Parolni kiriting (kamida 6 ta bolishi kerak)");
        String password = textScanner.nextLine();

        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setUserType(UserType.USER);
        newUser.setBalance(BigDecimal.valueOf(10000000));
        newUser.setPassword(password);

       try{
         currentUser =  authService.register(newUser);
       }catch (InvalidInputException | DataAlreadyException e){
           System.err.println(e.getMessage());
           return;
       }
        System.out.println("Registratsiya muvaffaqqiyatli tugadi");


    }
    public static void registerDriver() {
        System.out.println("Bu driverni ro'yxatdan o'tkasiz menusi");

        // USerni register pagei bilan bir xil bo'ladi va faqat car tanlash qoshimcha menu qoshiladi
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
    private static int getMenuNumberInSafeWay(String message){
        boolean isFormatException = true;
        int menuNumber = 0;
        while (isFormatException) {
            try {
                menuNumber = getMenuNumber(message);
                isFormatException = false;
            } catch (FormatException e) {
                numberScanner.next();
                System.err.println(e.getMessage());
            }
        }
        return menuNumber;
    }

}
