package dto;

import itacademy.restaurants.Json;
import itacademy.restaurants.model.Cuisine;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by aVa on 29.01.2017.
 */
public class CuisinesListDto {

    private Set<Cuisine> cuisines;

    public CuisinesListDto() {
        cuisines = new LinkedHashSet<>();
    }

    public CuisinesListDto(Set<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public String toJson() {
        return Json.newBuilder().addObject("cuisines", cuisines).getJson();
    }

    public Set<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(Set<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }
}
