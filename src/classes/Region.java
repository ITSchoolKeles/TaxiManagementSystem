package classes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Region {
    private UUID id;
    private String name;
    private ArrayList<City> cities;
    public Region(){

    }

    public Region(UUID id, String name, ArrayList<City> cities) {
        this.id = id;
        this.name = name;
        this.cities = cities;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(id, region.id) && Objects.equals(name, region.name) && Objects.equals(cities, region.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cities);
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cities=" + cities +
                '}';
    }
}
