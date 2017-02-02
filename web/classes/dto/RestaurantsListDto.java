package dto;

import itacademy.restaurants.Json;
import itacademy.restaurants.model.Restaurant;

import java.util.LinkedHashSet;
import java.util.Set;


public class RestaurantsListDto {

    private Set<RestaurantShortInformation> restaurants;

    public RestaurantsListDto() {
        restaurants = new LinkedHashSet<>();
    }

    public RestaurantsListDto(Set<Restaurant> restaurants) {
        this.restaurants = new LinkedHashSet<>();
        restaurants.forEach(restaurant -> this.restaurants.add(new RestaurantShortInformation(restaurant)));
    }

    public String toJson(){
        return  Json.newBuilder().addObject("restaurants", restaurants).getJson();
    }

    public Set<RestaurantShortInformation> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<RestaurantShortInformation> restaurants) {
        this.restaurants = restaurants;
    }

    public class RestaurantShortInformation {

        private long id;

        private String name;

        private String shortInformation;

        private String website;

        private byte [] image;

        public RestaurantShortInformation(Restaurant restaurant) {
            id = restaurant.getId();
            name = restaurant.getName();
            shortInformation = restaurant.getShortInformation();
            website = restaurant.getWebsite();
            image = restaurant.getImage();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShortInformation() {
            return shortInformation;
        }

        public void setShortInformation(String shortInformation) {
            this.shortInformation = shortInformation;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public byte[] getImage() {
            return image;
        }

        public void setImage(byte[] image) {
            this.image = image;
        }
    }
}
