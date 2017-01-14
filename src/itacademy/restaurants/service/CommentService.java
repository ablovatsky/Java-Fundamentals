package itacademy.restaurants.service;


import itacademy.restaurants.model.Comment;
import java.util.List;

/**
 * Created by aVa on 07.01.2017.
 */
public interface CommentService  extends ModelService<Comment>  {

    List<Comment> getListCommentsByUserId(long id);

    List<Comment> getListCommentsByRestaurantId(long id);
}
