package service;

import classes.City;
import classes.Region;
import exceptions.DataAlreadyException;
import exceptions.DataNotFound;
import exceptions.InvalidInputException;
import interfaces.CityService;
import utils.Database;

import java.util.ArrayList;
import java.util.UUID;

public class CityServiceImpl implements CityService {
    private final ArrayList<City> citiesDb = Database.citiesDb;
    private final ArrayList<Region> regionsDb = Database.regionsDb;
    @Override
    public void addCity(City city) {
        if(city.getName().length() < 3){
            throw new InvalidInputException("Shahar nomi kamida 3 ta harfdan iborat bolishi kerak");
        }

        //city name aynan shu regionda mavjud yoki yo'qligini tekshiramiz
        for (City regionCity : city.getRegion().getCities()) {
            if(regionCity.getName().equalsIgnoreCase(city.getName())){
                throw new DataAlreadyException("Bunday nomli shahar bu regionda allaqachon mavjud");
            }
        }
        city.setId(UUID.randomUUID());
        city.getRegion().getCities().add(city);
        citiesDb.add(city);
    }

    @Override
    public void editCity(UUID id, String name) {
          City cityById = getCity(id);
          if(cityById == null){
              throw new DataNotFound("Bunday id li shahar mavjud emas");
          }
        if(name.length() < 3){
            throw new InvalidInputException("Shahar nomi kamida 3 ta harfdan iborat bolishi kerak");
        }
        for (City regionCity : cityById.getRegion().getCities()) {
            if(regionCity.getName().equalsIgnoreCase(name)){
                throw new DataAlreadyException("Bunday nomli shahar bu regionda allaqachon mavjud");
            }
        }
        cityById.setName(name);
    }

    @Override
    public ArrayList<City> getCities() {
        return citiesDb;
    }

    @Override
    public City getCity(UUID id) {
        for (City city : citiesDb) {
            if(city.getId().equals(id)){
                return city;
            }
        }
        return null;
    }
}
