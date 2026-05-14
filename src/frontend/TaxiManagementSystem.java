package frontend;

import classes.User;
import enums.Car;
import enums.UserType;
import exceptions.DataAlreadyException;
import exceptions.FormatException;
import exceptions.InvalidInputException;
import exceptions.LoginException;
import interfaces.AuthService;
import service.AuthServiceImpl;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaxiManagementSystem {

    private static final Scanner numberScanner = new Scanner(System.in);
    private static final Scanner textScanner = new Scanner(System.in);

    private static final AuthService authService = new AuthServiceImpl();

    private static User currentUser = null;

    public static void main(String[] args) {

        while (true) {

            displayAuthenticationMenus();

            int menuNumber = getMenuNumberInSafeWay("Menu raqamini kiriting");

            switch (menuNumber) {

                case 1 -> login();

                case 2 -> register();

                case 3 -> {
                    System.out.println("Dastur tugatildi");
                    System.exit(0);
                }

                default -> System.out.println("Noto'g'ri menu");
            }
        }
    }

    public static void login() {

        System.out.println("------------------------------------------");
        System.out.println("Bu login sahifasi");
        System.out.println("------------------------------------------");

        System.out.print("Telefon raqamingizni kiriting -> ");
        String phoneNumber = textScanner.nextLine();

        System.out.print("Parolni kiriting -> ");
        String password = textScanner.nextLine();

        try {

            currentUser = authService.login(phoneNumber, password);

            System.out.println("Login muvaffaqqiyatli amalga oshirildi");

            if (currentUser.getUserType() == UserType.ADMIN) {

                adminDashboard();

            } else if (currentUser.getUserType() == UserType.USER) {

                userDashboard();

            } else if (currentUser.getUserType() == UserType.DRIVER) {

                driverDashboard();
            }

        } catch (InvalidInputException | LoginException e) {

            System.err.println(e.getMessage());
        }
    }

    public static void register() {

        System.out.println("------------------------------------------");
        System.out.println("|     Bu ro'yxatdan o'tish sahifasi      |");
        System.out.println("------------------------------------------");

        System.out.println("1.USER");
        System.out.println("2.DRIVER");
        System.out.println("3.Back");

        int roleMenuNumber = getMenuNumberInSafeWay("Role ni tanlang");

        switch (roleMenuNumber) {

            case 1 -> registerUser();

            case 2 -> registerDriver();

            case 3 -> {
                return;
            }

            default -> System.out.println("Noto'g'ri tanlov");
        }
    }

    private static void registerUser() {

        System.out.println("-------------------------------------------");
        System.out.println("|  Bu userni ro'yxatdan o'tkazish menusi  |");
        System.out.println("-------------------------------------------");

        System.out.print("To'liq ismingizni kiriting -> ");
        String fullName = textScanner.nextLine();

        System.out.print("Telefon raqamingizni kiriting -> ");
        String phoneNumber = textScanner.nextLine();

        System.out.print("Parolni kiriting -> ");
        String password = textScanner.nextLine();

        User newUser = new User();

        newUser.setFullName(fullName);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setPassword(password);
        newUser.setUserType(UserType.USER);
        newUser.setBalance(BigDecimal.valueOf(100000));

        try {

            currentUser = authService.register(newUser);

            System.out.println("Registratsiya muvaffaqqiyatli tugadi");

            userDashboard();

        } catch (InvalidInputException | DataAlreadyException e) {

            System.err.println(e.getMessage());
        }
    }

    public static void registerDriver() {

        System.out.println("-------------------------------------------");
        System.out.println("|  Bu driverni ro'yxatdan o'tkazish menu  |");
        System.out.println("-------------------------------------------");

        System.out.print("To'liq ismingizni kiriting -> ");
        String fullName = textScanner.nextLine();

        System.out.print("Telefon raqamingizni kiriting -> ");
        String phoneNumber = textScanner.nextLine();

        System.out.print("Parolni kiriting -> ");
        String password = textScanner.nextLine();

        Car driverCar = chooseCar();

        User newDriver = new User();

        newDriver.setFullName(fullName);
        newDriver.setPhoneNumber(phoneNumber);
        newDriver.setPassword(password);
        newDriver.setUserType(UserType.DRIVER);
        newDriver.setBalance(BigDecimal.ZERO);
        newDriver.setCar(driverCar);

        try {

            currentUser = authService.register(newDriver);

            System.out.println("Registratsiya muvaffaqqiyatli tugadi");

            driverDashboard();

        } catch (InvalidInputException | DataAlreadyException e) {

            System.err.println(e.getMessage());
        }
    }

    public static void adminDashboard() {

        while (true) {

            System.out.println();
            System.out.println("========== ADMIN DASHBOARD ==========");
            System.out.println("1. Region and City");
            System.out.println("2. All users");
            System.out.println("3. Travel history");
            System.out.println("4. Driver history");
            System.out.println("5. User history");
            System.out.println("0. Logout");

            int choice = getMenuNumberInSafeWay("Tanlang");

            switch (choice) {

                case 1 -> System.out.println("Region and City management");

                case 2 -> System.out.println("All users");

                case 3 -> System.out.println("Travel history");

                case 4 -> System.out.println("Driver history");

                case 6 -> System.out.println("User history");

                case 0 -> {
                    currentUser = null;
                    return;
                }

                default -> System.out.println("Noto'g'ri menu");
            }
        }
    }

    public static void userDashboard() {

        while (true) {

            System.out.println();
            System.out.println("========== USER DASHBOARD ==========");
            System.out.println("1. My travel history");
            System.out.println("2. Balance management");
            System.out.println("3. Profile");
            System.out.println("0. Logout");

            int choice = getMenuNumberInSafeWay("Tanlang");

            switch (choice) {

                case 1 -> System.out.println("My travel history");

                case 2 -> System.out.println("Balance management");

                case 3 -> System.out.println(currentUser);

                case 0 -> {
                    currentUser = null;
                    break;
                }

                default -> System.out.println("Noto'g'ri menu");
            }
        }
    }

    public static void driverDashboard() {

        while (true) {

            System.out.println();
            System.out.println("========== DRIVER DASHBOARD ==========");
            System.out.println("1. My history");
            System.out.println("2. Calculate balance");
            System.out.println("3. Profile");
            System.out.println("0. Logout");

            int choice = getMenuNumberInSafeWay("Tanlang");

            switch (choice) {

                case 1 -> System.out.println("Driver history");

                case 2 -> System.out.println("Driver balance");

                case 3 -> System.out.println(currentUser);

                case 0 -> {
                    currentUser = null;
                    return;
                }

                default -> System.out.println("Noto'g'ri menu");
            }
        }
    }

    public static void displayAuthenticationMenus() {

        System.out.println();
        System.out.println("===== TAXI MANAGEMENT SYSTEM =====");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }

    private static int getMenuNumber(String message) {

        int menuNumber;

        try {

            System.out.print(message + " -> ");

            menuNumber = numberScanner.nextInt();

        } catch (InputMismatchException e) {

            throw new FormatException("Iltimos faqat raqam kiriting");
        }

        return menuNumber;
    }

    private static int getMenuNumberInSafeWay(String message) {

        while (true) {

            try {

                return getMenuNumber(message);

            } catch (FormatException e) {

                numberScanner.nextLine();

                System.err.println(e.getMessage());
            }
        }
    }

    public static Car chooseCar() {

        Car[] cars = Car.values();

        for (int i = 0; i < cars.length; i++) {

            System.out.println((i + 1) + ". " + cars[i]);
        }

        int chooseYourCar = getMenuNumberInSafeWay("Choose your car");

        return cars[chooseYourCar - 1];
    }
}