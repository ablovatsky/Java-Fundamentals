package servlet.administration;

import com.google.gson.Gson;
import itacademy.restaurants.Json;
import itacademy.restaurants.service.CountryService;
import itacademy.restaurants.service.CuisineService;
import itacademy.restaurants.service.RestaurantService;
import itacademy.restaurants.service.impl.CountryDatabaseService;
import itacademy.restaurants.service.impl.CuisineDatabaseService;
import itacademy.restaurants.service.impl.RestaurantDatabaseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/administration/add-restaurants")
public class AddRestaurantServlet extends HttpServlet {

    private RestaurantService restaurantService;
    private CuisineService cuisineService;
    private CountryService countryService;

    public AddRestaurantServlet() {
        restaurantService = RestaurantDatabaseService.getInstance();
        cuisineService = CuisineDatabaseService.getInstance();
        countryService = CountryDatabaseService.getInstance();
    }

    private static final String RESTAURANTS_URL = "/WEB-INF/views/content/administration/add-restaurant.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(RESTAURANTS_URL);
        //req.setAttribute("cuisines", cuisineService.getAll());
        //req.setAttribute("countries", countryService.getAll());
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Json.Builder builder = Json.newBuilder();
        String json = builder.addObject("cuisines", cuisineService.getAll())
                .addObject("countries", countryService.getAll())
                .createObject("user")
                .getJson();
        PrintWriter writer = resp.getWriter();
        writer.print(json);


    }
}
