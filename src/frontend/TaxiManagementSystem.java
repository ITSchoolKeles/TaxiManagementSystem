package frontend;

import classes.City;
import classes.Region;
import classes.User;
import enums.Car;
import enums.UserType;
import exceptions.*;
import interfaces.AuthService;
import interfaces.CityService;
import interfaces.RegionService;
import service.AuthServiceImpl;
import service.CityServiceImpl;
import service.RegionServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaxiManagementSystem {
    private static final Scanner numberScanner = new Scanner(System.in);
    private static final Scanner textScanner = new Scanner(System.in);
    private static final AuthService authService = new AuthServiceImpl();
    private static final RegionService regionService = new RegionServiceImpl();
    private static final CityService cityService = new CityServiceImpl();
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


        while (true) {
            displayAuthentificationMenus();
            int menuNumber = getMenuNumberInSafeWay("Menu raqamini kiriting");
            switch (menuNumber) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> System.exit(0);
            }
        }

    }

    public static void login() {
        System.out.println("------------------------------------------");
        System.out.println("Bu login sahifasi");
        System.out.println("------------------------------------------");

        // phone Number va paroll

        System.out.print("Telefon raqamingizni kiriting -> ");
        String phoneNumber = textScanner.nextLine();

        System.out.print("Parolni kiriting -> ");
        String password = textScanner.nextLine();

        try {
            currentUser = authService.login(phoneNumber, password);
        } catch (InvalidInputException | LoginException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Login muvaffaqqiyatli amalga oshirildi");

        switch (currentUser.getUserType()) {
            case ADMIN -> adminDashboard();
            case USER -> userDashboard();
            case DRIVER -> driverDashboard();
        }
    }

    private static void userDashboard() {
    }

    private static void driverDashboard() {

    }

    public static void register() {
        System.out.println("------------------------------------------");
        System.out.println("|     Bu ro'yxatdan o'tish sahifasi      |");
        System.out.println("------------------------------------------");
        System.out.println();
        System.out.println("1.USER");
        System.out.println("2.DRIVER");
        System.out.println("3.Asosiy menuga qaytish");
        System.out.println();
        int roleMenuNumber = getMenuNumberInSafeWay("Role ni tanlang");

        switch (roleMenuNumber) {
            case 1 -> registerUser();
            case 2 -> registerDriver();
            case 3 -> {
                return;
            }
        }
    }

    private static void registerUser() {
        System.out.println("-------------------------------------------");
        System.out.println("|  Bu userni ro'yxatdan o'tkazish menusi  |");
        System.out.println("-------------------------------------------");
        // USER --> fullName, phoneNumber,password

        System.out.println();
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

        try {
            currentUser = authService.register(newUser);
        } catch (InvalidInputException | DataAlreadyException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Registratsiya muvaffaqqiyatli tugadi");


    }

    public static void registerDriver() {
        System.out.println("-------------------------------------------");
        System.out.println("|  Bu driverni ro'yxatdan o'tkasiz menusi |");
        System.out.println("-------------------------------------------");
        System.out.println();

        System.out.print("To'liq ismingizni kiriting -> ");
        String fullName = textScanner.nextLine();

        System.out.print("Telefon raqamingizni kiriting -> ");
        String phoneNumber = textScanner.nextLine();

        System.out.print("Parolni kiriting (kamida 6 ta bolishi kerak)");
        String password = textScanner.nextLine();


        // Car tanlash

        Car driverCar = chooseCar();

        User newDriver = new User();
        newDriver.setFullName(fullName);
        newDriver.setPhoneNumber(phoneNumber);
        newDriver.setUserType(UserType.DRIVER);
        newDriver.setBalance(BigDecimal.valueOf(0));
        newDriver.setPassword(password);
        newDriver.setCar(driverCar);
        try {
            currentUser = authService.register(newDriver);
        } catch (InvalidInputException | DataAlreadyException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("-------------------------------------------");
        System.out.println("|  Registratsiya muvaffaqqiyatli tugadi   |");
        System.out.println("-------------------------------------------");
        System.out.println();

        // USerni register pagei bilan bir xil bo'ladi va faqat car tanlash qoshimcha menu qoshiladi
    }

    public static void displayAuthentificationMenus() {
        System.out.println("Bizning taksi loyihamizga xush kelibsiz");
        System.out.println("-------------------------------------------");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println();
    }

    private static int getMenuNumber(String message) {

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

    private static int getMenuNumberInSafeWay(String message) {
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

    public static Car chooseCar() {
        Car[] cars = Car.values();
        for (int i = 0; i < cars.length; i++) {
            System.out.println((i + 1) + ". " + cars[i]);
        }
        int chooseYourCar = getMenuNumber("Choose your car ");
        return cars[chooseYourCar - 1];
    }

    public static void adminDashboard() {
        boolean isBack = true;
        while (isBack) {
            displayAdminDashboard();
            int adminMenuNumber = getMenuNumberInSafeWay("Admin menu raqam kiriting");
            switch (adminMenuNumber) {
                case 1 -> regionAndCity();
                case 2 -> allUsers();
                case 3 -> travelHistory();
                case 4 -> driverHistory();
                case 5 -> userHistory();
                case 6 -> profile();
                case 0 -> {
                    isBack = false;
                    return;
                }
            }
        }


    }

    private static void displayAdminDashboard() {
        System.out.println("-------------------------------------------");
        System.out.println("|       Admin menusiga xushkelibsiz       |");
        System.out.println("-------------------------------------------");
        System.out.println();
        System.out.println("1. Region and City");
        System.out.println("2. All users");
        System.out.println("3. Travel history");
        System.out.println("4. Driver history");
        System.out.println("5. User history");
        System.out.println("6. Profile");
        System.out.println("0. Back");
        System.out.println();
    }

    private static void profile() {
    }

    private static void userHistory() {

    }

    private static void travelHistory() {

    }

    private static void allUsers() {
        System.out.println("-------------------------------------------");
        System.out.println("|        Barcha foydalanuvchilar          |");
        System.out.println("-------------------------------------------");
        System.out.println();

        ArrayList<User> users = authService.getUsers();

        for (User user : users) {
            System.out.println(user);
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        }
        System.out.println();
    }

    private static void driverHistory() {

    }

    private static void regionAndCity() {
        boolean isBack = true;
        while (isBack) {
            displayRegionAndCityMenu();
            int getRegionMenuNumber = getMenuNumberInSafeWay("Region va City menulardan birini tanlang: ");
            switch (getRegionMenuNumber) {
                case 1 -> allRegions();
                case 2 -> addRegion();
                case 3 -> addCity();
                case 4 -> editRegion();
                case 5 -> editCity();
                case 0 -> {
                    isBack = false;
                    return;
                }

            }
        }
    }

    private static void editCity() {
         ArrayList<City> allCities = cityService.getCities();

         for(int i = 0; i < allCities.size(); i++) {
             System.out.println((i +1) + "." + allCities.get(i).getRegion().getName() + "---" + allCities.get(i).getName());
         }
         int cityNumber = getMenuNumberInSafeWay("Tegishli shaharlardan birini tanlang: ");
         City city = allCities.get(cityNumber - 1);

        System.out.print("Yangi shahar nomini kiriting -> ");
        String updatedCityName = textScanner.nextLine();

        if(updatedCityName.equalsIgnoreCase(city.getName())){
            return;
        }
        try{
            cityService.editCity(city.getId(), updatedCityName);
        }catch(DataNotFound | DataAlreadyException | InvalidInputException e){
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Shahar nomi muvaffaqqiyatli saqlanidi");


    }

    private static void editRegion() {
        Region region = getRegion();
        System.out.print("Yangi region nomini kiriting -> ");
        String updatedName = textScanner.nextLine();

        if(updatedName.equalsIgnoreCase(region.getName())) {
            return;
        }
        try{
            regionService.editRegion(region.getId(), updatedName);
        }catch (DataAlreadyException | DataNotFound | InvalidInputException e){
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Region nomi muvaffaqqiyatli saqlanidi");
    }

    private static Region getRegion() {
        ArrayList<Region> allRegions = regionService.getAllRegions();
        for (int i = 0; i < allRegions.size(); i++) {
            System.out.println((i + 1) + ". " + allRegions.get(i).getName());
        }
        int regionNumber = getMenuNumberInSafeWay("Tegishli regionlardan birini tanlang: ");
        return allRegions.get(regionNumber - 1);
    }

    private static void addCity() {
        // City qo'shish
// Region tanlashni
// City qo'shish
// City Serice bilan boglaymiz

        // Regionlarni ko'rsatish
        Region region = getRegion();

        System.out.print("Yangi shahar nomini kiriting -> ");
        String cityName = textScanner.nextLine();


        City newCity = new City();
        newCity.setName(cityName);
        newCity.setRegion(region);


        try{
            cityService.addCity(newCity);
        }catch (InvalidInputException | DataAlreadyException e){
            System.err.println(e.getMessage());
            return;
        };
        System.out.println("Yangi shahar muvaffaqiyatli qo'shildi!");



    }

    private static void addRegion() {
        System.out.println("Region qo'shish menusi");
        System.out.print("Yangi region nomini kiriting -> ");
        String regionName = textScanner.nextLine();

        try{
            regionService.addRegion(regionName);
        }catch (DataAlreadyException | InvalidInputException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Region muvafaqqiyatli qo'shildi");


    }

    private static void allRegions() {
        System.out.println("Barcha regionlar");

        for (int i = 0; i < regionService.getAllRegions().size(); i++) {
            System.out.println((i + 1) + ". " + regionService.getAllRegions().get(i).getName());
            System.out.println("-----Shaharlari--------");
            for(int j = 0; j < regionService.getAllRegions().get(i).getCities().size(); j++){
                System.out.println("\t" + (j + 1) + ". " + regionService.getAllRegions().get(i).getCities().get(j).getName());
            }
        }

    }

    public static void displayRegionAndCityMenu() {
        System.out.println("-----------------------------------------");
        System.out.println("|       Region va City menusi            |");
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("1. Regionlarni ko'rsatish");
        System.out.println("2. Region qo'shish");
        System.out.println("3. City qo'shish");
        System.out.println("4. Region nomini o'zgartirish");
        System.out.println("5. City nomini o'zgartirish");
        System.out.println("0. Back");
        System.out.println();
    }
}


