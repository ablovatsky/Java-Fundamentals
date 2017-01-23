package itacademy.restaurants.model;


public class Role extends Model {

    private String name;


    public Role(String name) {
        this.name = name;
    }

    public Role(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
