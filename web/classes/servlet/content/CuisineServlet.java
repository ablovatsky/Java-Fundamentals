package servlet.content;

import itacademy.restaurants.model.Cuisine;
import itacademy.restaurants.service.CuisineService;
import itacademy.restaurants.service.impl.CuisineDatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = "/cuisine")
public class CuisineServlet extends HttpServlet{

    private CuisineService cuisineService;

    public CuisineServlet() {
        cuisineService = CuisineDatabaseService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Cuisine> cuisines = cuisineService.getAll();
        req.setAttribute("cuisines", cuisines);
    }
}
