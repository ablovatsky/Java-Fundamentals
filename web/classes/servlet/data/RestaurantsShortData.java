package servlet.data;

import dto.RestaurantsListDto;
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
import java.util.Objects;

@WebServlet(urlPatterns = "/getRestaurantsShortData")
public class RestaurantsShortData extends HttpServlet{

    private RestaurantService restaurantService;

    public RestaurantsShortData() {
        restaurantService = RestaurantDatabaseService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchCriterion = req.getParameter("searchCriterion");
        String value = req.getParameter("value");
        RestaurantsListDto restaurants = null;
        if (searchCriterion != null && !searchCriterion.equals("") && value != null && !value.equals("")) {
            switch (searchCriterion) {
                case "name":
                    restaurants = restaurantService.getRestaurantsByName(value);
                    break;
                case "city":
                    restaurants = restaurantService.getRestaurantsByCity(value);
                    break;
                case "country":
                    restaurants = restaurantService.getRestaurantsByCountry(value);
                    break;
                case "cuisine":
                    restaurants = restaurantService.getRestaurantsByCuisine(value);
                    break;
            }
        } else {
            restaurants = restaurantService.getRestaurants();
        }

        HttpSession session = req.getSession();
        session.setAttribute("restaurants", restaurants);
        PrintWriter writer = resp.getWriter();
        writer.print(restaurants != null ? restaurants.toJson() : null);
    }
}
