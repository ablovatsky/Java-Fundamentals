package itacademy.restaurants.model;

import java.io.Serializable;

/**
 * Created by aVa on 12.01.2017.
 */
public class Model implements Serializable {

    private long id;

    public Model() {
    }

    public Model(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
