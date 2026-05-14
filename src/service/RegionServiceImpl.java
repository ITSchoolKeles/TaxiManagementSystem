package service;

import classes.Region;
import exceptions.DataAlreadyException;
import exceptions.InvalidInputException;
import interfaces.RegionService;
import utils.Database;

import java.util.ArrayList;
import java.util.UUID;

public class RegionServiceImpl implements RegionService {
    private final ArrayList<Region> regionsDb = Database.regionsDb;
    {
        regionsDb.add(
                new Region(
                        UUID.randomUUID(),
                        "Toshkent",
                        new ArrayList<>()
                )
        );
        regionsDb.add(
                new Region(
                        UUID.randomUUID(),
                        "Samarqand",
                        new ArrayList<>()
                )
        );

    }
    @Override
    public void addRegion(String name) {
       if(name.length()< 3){
           throw new InvalidInputException("Region nomi kamida 3 ta harfdan iborat bolishi kerak");
       }
       if(isRegionNameExists(name)){
           throw new DataAlreadyException("Region nomi allaqachon mavjud");
       }

       regionsDb.add(
               new Region(
                          UUID.randomUUID(),
                          name,
                          new ArrayList<>()
               )
       );
    }

    @Override
    public void editRegion(UUID id, String name) {

    }

    @Override
    public ArrayList<Region> getAllRegions() {
        return regionsDb;
    }

    private boolean isRegionNameExists(String name) {
        for (Region region : regionsDb) {
            if (region.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
