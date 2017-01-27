package itacademy.restaurants.model;

import java.util.*;


public class Country extends Model {

    private String name;

    private Set<City> cities;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id='" + super.toString() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }


}
