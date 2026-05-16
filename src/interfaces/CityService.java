package interfaces;

import classes.City;

import java.util.ArrayList;
import java.util.UUID;

public interface CityService {
    void addCity(City city);
    void editCity(UUID id, String name);
    ArrayList<City> getCities();
    City getCity(UUID id);

}
