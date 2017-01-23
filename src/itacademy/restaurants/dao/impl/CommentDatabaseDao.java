package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.CommentDao;
import itacademy.restaurants.dao.connection.MySQL;
import itacademy.restaurants.model.Comment;
import java.util.List;
import java.util.Set;


public class CommentDatabaseDao implements CommentDao {

    private MySQL connections;

    public CommentDatabaseDao() {
        connections = MySQL.CONNECTIONS;
    }

    @Override
    public long add(Comment comment) {
        long id = 0;
        return id;
    }

    @Override
    public boolean update(Comment model) {
        return false;
    }

    @Override
    public boolean remove(Comment model) {
        return false;
    }


    @Override
    public Comment getById(long id) {
        return null;
    }

    @Override
    public Set<Comment> getAll() {
        return null;
    }

    @Override
    public List<Comment> getListCommentsByUserId(long id) {
        return null;
    }

    @Override
    public List<Comment> getListCommentsByRestaurantId(long id) {
        return null;
    }
}
