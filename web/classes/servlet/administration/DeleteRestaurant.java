package servlet.administration;

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

@WebServlet(urlPatterns = "/delete")
public class DeleteRestaurant extends HttpServlet{

    private RestaurantService restaurantService;

    private static final String RESTAURANTS_URL = "/WEB-INF/views/content/new_restaurants.jsp";

    public DeleteRestaurant() {
        restaurantService = RestaurantDatabaseService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        restaurantService.remove(new Restaurant(Long.parseLong(id)));

        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(RESTAURANTS_URL);
        session.setAttribute("routing", "edit");
        dispatcher.forward(req, resp);
    }
}
