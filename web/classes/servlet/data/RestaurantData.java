package servlet.data;


import itacademy.restaurants.Json;
import itacademy.restaurants.model.Restaurant;
import itacademy.restaurants.service.RestaurantService;
import itacademy.restaurants.service.impl.RestaurantDatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/getRestaurantData")
public class RestaurantData extends HttpServlet{

    private RestaurantService restaurantService;

    public RestaurantData() {
        restaurantService = RestaurantDatabaseService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("id");
        Restaurant restaurant = restaurantService.getById(id);
        PrintWriter writer = resp.getWriter();
        writer.print(Json.newBuilder().addObject("restaurant", restaurant).getJson());
    }
}
