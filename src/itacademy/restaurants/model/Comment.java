package itacademy.restaurants.model;

import java.sql.Date;
import java.time.LocalDate;


public class Comment extends Model {

    private String comment;

    private Date date;

    private User user;

    private Restaurant restaurant;

    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
