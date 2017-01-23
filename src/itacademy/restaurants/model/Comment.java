package itacademy.restaurants.model;

import java.time.LocalDate;


public class Comment extends Model {

    private String comment;

    private LocalDate date;

    private User user;

    private Restaurant restaurant;

    public Comment(String comment, User user, Restaurant restaurant) {
        this.comment = comment;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Comment(long id, String comment, LocalDate date, User user, Restaurant restaurant) {
        super(id);
        this.comment = comment;
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + super.toString() + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
