package itacademy.restaurants.model;

import java.util.Set;

/**
 * Created by aVa on 08.01.2017.
 */
public class Country extends Model {

    private String name;

    Set<City> cities;

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
}
