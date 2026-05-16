package interfaces;

import classes.Region;

import java.util.ArrayList;
import java.util.UUID;

public interface RegionService {
    void addRegion(String name);
    void editRegion(UUID id, String name);
    ArrayList<Region> getAllRegions();
    Region getRegionById(UUID id);
}
