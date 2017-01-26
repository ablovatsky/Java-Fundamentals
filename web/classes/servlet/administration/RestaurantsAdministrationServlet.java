package servlet.administration;

import itacademy.restaurants.model.Restaurant;
import itacademy.restaurants.service.RestaurantService;
import itacademy.restaurants.service.impl.RestaurantDatabaseService;
import servlet.content.CuisineServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;


@WebServlet(urlPatterns = "/administration/restaurants", name = "administration-restaurants")
public class RestaurantsAdministrationServlet extends HttpServlet {

    private RestaurantService restaurantService;

    public RestaurantsAdministrationServlet() {
        restaurantService = RestaurantDatabaseService.getInstance();
    }

    private static final String RESTAURANTS_URL = "/WEB-INF/views/content/administration/restaurants-administration.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(RESTAURANTS_URL);
        Set<Restaurant> restaurants = restaurantService.getAll();
        session.setAttribute("restaurants", restaurants);
        dispatcher.forward(req, resp);
    }
}
