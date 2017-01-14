package itacademy.restaurants.model;

/**
 * Created by aVa on 05.01.2017.
 */
public class Cuisine extends Model {

    private String name;

    public Cuisine(String name) {
        this.name = name;
    }

    public Cuisine(long id, String name) {
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
        return "Cuisine{" +
                "id='" + super.toString() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
