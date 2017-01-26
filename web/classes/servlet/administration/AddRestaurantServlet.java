package servlet.administration;

import itacademy.restaurants.model.Restaurant;
import itacademy.restaurants.service.CuisineService;
import itacademy.restaurants.service.RestaurantService;
import itacademy.restaurants.service.impl.CuisineDatabaseService;
import itacademy.restaurants.service.impl.RestaurantDatabaseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/administration/add-restaurants")
public class AddRestaurantServlet extends HttpServlet {

    private RestaurantService restaurantService;
    private CuisineService cuisineService;

    public AddRestaurantServlet() {
        restaurantService = RestaurantDatabaseService.getInstance();
        cuisineService = CuisineDatabaseService.getInstance();
    }

    private static final String RESTAURANTS_URL = "/WEB-INF/views/content/administration/add-restaurant.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(RESTAURANTS_URL);
        req.setAttribute("cuisines", cuisineService.getAll());
        dispatcher.forward(req, resp);
    }
}
