package itacademy.restaurants.model;




import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 04.01.2017.
 */
public class Restaurant extends Model {

    private String name;

    private Set<City> addresses;

    private Set<Cuisine> cuisines;

    private String information;

    private List<Comment> comments;

    public Restaurant(String name, String information) {
        this.name = name;
        this.information = information;
    }

    public Restaurant(long id, String name, String information) {
        super(id);
        this.name = name;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<City> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<City> addresses) {
        this.addresses = addresses;
    }

    public Set<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(Set<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + super.toString() + '\'' +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
}
