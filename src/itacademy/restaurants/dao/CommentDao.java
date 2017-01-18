package itacademy.restaurants.dao;


import itacademy.restaurants.model.Comment;
import java.util.List;

/**
 * Created by aVa on 07.01.2017.
 */
public interface CommentDao extends ModelDao<Comment> {

    List<Comment> getListCommentsByUserId(long id) throws ExceptionDao;

    List<Comment> getListCommentsByRestaurantId(long id) throws ExceptionDao;

}
