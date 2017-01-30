package servlet.json;


import com.google.gson.Gson;
import itacademy.restaurants.service.CountryService;
import itacademy.restaurants.service.CuisineService;
import itacademy.restaurants.service.impl.CountryDatabaseService;
import itacademy.restaurants.service.impl.CuisineDatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/getCountries")
public class CountriesJSONServlet extends HttpServlet{

    private CuisineService cuisineService;
    private CountryService countryService;

    public CountriesJSONServlet() {
        cuisineService = CuisineDatabaseService.getInstance();
        countryService = CountryDatabaseService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String strJSON = "{ \"cuisines\": ";
        strJSON += gson.toJson(cuisineService.getAll());
        strJSON += ", \"countries\": ";
        strJSON += gson.toJson(countryService.getAll());
        strJSON += "}";
        PrintWriter writer = resp.getWriter();
        writer.print(strJSON);
    }
}
