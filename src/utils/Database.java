package utils;

import classes.City;
import classes.Region;
import classes.User;

import java.util.ArrayList;

public interface Database {
    ArrayList<User> usersDb = new ArrayList<>();
    ArrayList<Region> regionsDb = new ArrayList<>();
    ArrayList<City> citiesDb = new ArrayList<>();
}
