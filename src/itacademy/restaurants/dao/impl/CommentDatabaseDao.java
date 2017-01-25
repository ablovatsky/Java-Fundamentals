package itacademy.restaurants.dao.impl;

import itacademy.restaurants.dao.CommentDao;
import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.dao.connection.MySQL;
import itacademy.restaurants.model.Comment;
import itacademy.restaurants.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        List<Comment> comments = new ArrayList<>();
        try(Connection connection = connections.getConnection()) {
            String sqlQuery = "SELECT t1.comment, t1.date, t3.username " +
                    "FROM comments t1 " +
                    "INNER JOIN restaurants t2 ON t1.restaurant_id = t2.id " +
                    "INNER JOIN users t3 ON t1.user_id = t3.id " +
                    "WHERE t2.id = ?;";
            try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setLong(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Comment comment = new Comment();
                        comment.setComment(resultSet.getString("comment"));
                        comment.setDate(resultSet.getDate("date"));
                        comment.setUser(new User(resultSet.getString("name")));
                        comments.add(comment);
                    }
                    return comments;
                }
            }
        } catch (SQLException e) {
            throw new ExceptionDao("", e);
        }
    }
}
