package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.CommentDao;
import itacademy.restaurants.dao.connection.MySqlConnection;
import itacademy.restaurants.model.Comment;
import itacademy.restaurants.model.Model;
import java.util.List;
import java.util.Set;

/**
 * Created by aVa on 13.01.2017.
 */
public class CommentDatabaseDao extends MySqlConnection implements CommentDao {

    @Override
    public long add(Comment comment) {
        long id = 0;
        return id;
    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void remove(Comment comment) {

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
