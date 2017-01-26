package servlet.content;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import itacademy.restaurants.model.Comment;
import itacademy.restaurants.service.RestaurantService;
import itacademy.restaurants.service.impl.RestaurantDatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(urlPatterns = "/addComment")
public class CommentServlet extends HttpServlet {

    private RestaurantService restaurantService;

    public CommentServlet() {
        restaurantService = RestaurantDatabaseService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JsonReader reader = new JsonReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        reader.setLenient(true);
        Comment comment = new Gson().fromJson(reader, Comment.class);
        restaurantService.addCommentToRestaurant(comment);
        reader.close();
    }
}
