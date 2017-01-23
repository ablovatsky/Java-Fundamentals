package servlet.content;

import itacademy.restaurants.model.Restaurant;
import itacademy.restaurants.service.RestaurantService;
import itacademy.restaurants.service.impl.RestaurantDatabaseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Created by VAblovatsky on 17.01.2017.
 */

@WebServlet(urlPatterns = "/restaurants", name = "restaurants")
public class RestaurantsServlet extends HttpServlet {

    private RestaurantService restaurantService;

    public RestaurantsServlet() {
        restaurantService = RestaurantDatabaseService.getInstance();
    }

    private static final String RESTAURANTS_URL = "/WEB-INF/views/content/restaurants.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(RESTAURANTS_URL);
        Set<Restaurant> restaurants = restaurantService.getAll();
        session.setAttribute("restaurants", restaurants);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(RESTAURANTS_URL);
        dispatcher.forward(req, resp);
    }
}
