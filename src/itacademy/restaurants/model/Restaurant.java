package itacademy.restaurants.model;

import java.util.List;
import java.util.Set;


public class Restaurant extends Model {

    private String name;

    private String website;

    private String shortInformation;

    private String phone;

    private String workingHours;

    private byte [] image;

    private Set<City> addresses;

    private Set<Cuisine> cuisines;

    private String information;

    private List<Comment> comments;

    public Restaurant(long id, String name, String website, String shortInformation, String workingHours, byte[] image, Set<City> addresses) {
        super(id);
        this.name = name;
        this.website = website;
        this.shortInformation = shortInformation;
        this.workingHours = workingHours;
        this.image = image;
        this.addresses = addresses;
    }

    public Restaurant() {
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getShortInformation() {
        return shortInformation;
    }

    public void setShortInformation(String shortInformation) {
        this.shortInformation = shortInformation;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] immage) {
        this.image = immage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
