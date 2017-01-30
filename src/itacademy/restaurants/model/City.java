package itacademy.restaurants.model;

public class City extends Model{

    private String name;

    private Country country;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(long id, String name) {
        super(id);
        this.name = name;
    }

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public City(long id, String name, Country country) {
        super(id);
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name + ", " + country.getName() ;
    }
}
