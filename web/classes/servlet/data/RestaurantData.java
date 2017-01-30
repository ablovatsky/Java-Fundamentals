package servlet.data;


import itacademy.restaurants.Json;
import itacademy.restaurants.model.Comment;
import itacademy.restaurants.model.Restaurant;
import itacademy.restaurants.service.CountryService;
import itacademy.restaurants.service.CuisineService;
import itacademy.restaurants.service.RestaurantService;
import itacademy.restaurants.service.impl.CountryDatabaseService;
import itacademy.restaurants.service.impl.CuisineDatabaseService;
import itacademy.restaurants.service.impl.RestaurantDatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/getRestaurantData")
public class RestaurantData extends HttpServlet{

    private RestaurantService restaurantService;
    private CuisineService cuisineService;
    private CountryService countryService;

    public RestaurantData() {
        restaurantService = RestaurantDatabaseService.getInstance();
        cuisineService = CuisineDatabaseService.getInstance();
        countryService = CountryDatabaseService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("restaurant");
        Restaurant restaurant = restaurantService.getById(Long.parseLong(id));
        List<Comment> comments = restaurantService.getRestaurantComments(Long.parseLong(id));
        PrintWriter writer = resp.getWriter();
        writer.print(Json.newBuilder()
                        .addObject("cuisines", cuisineService.getAll())
                        .addObject("countries", countryService.getAll())
                        .addObject("restaurant", restaurant)
                        .addObject("comments", comments)
                        .getJson());
    }
}
