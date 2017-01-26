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

@WebServlet(urlPatterns = "/administration/search")
public class AdministrationSearchRestaurantServlet extends HttpServlet {

    private static final String RESTAURANTS_URL = "/WEB-INF/views/content/administration/restaurants-administration.jsp";

    private RestaurantService restaurantService;

    public AdministrationSearchRestaurantServlet() {
        restaurantService = RestaurantDatabaseService.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(RESTAURANTS_URL);
        String name = req.getParameter("name");
        Set<Restaurant> restaurants = null;
        switch (req.getParameter("search_type")){
            case "name":
                restaurants = restaurantService.getRestaurantsByName(name);
                break;
            case "city":
                restaurants = restaurantService.getRestaurantsByCity(name);
                break;
            case "country":
                restaurants = restaurantService.getRestaurantsByCountry(name);
                break;
        }
        session.setAttribute("restaurants", restaurants);
        dispatcher.forward(req, resp);

    }
}
