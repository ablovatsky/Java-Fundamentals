package servlet.data;

import dto.CuisinesListDto;
import itacademy.restaurants.service.CuisineService;
import itacademy.restaurants.service.impl.CuisineDatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/getCuisinesData")
public class CuisinesData extends HttpServlet{

    private CuisineService cuisineService;

    public CuisinesData() {
        cuisineService = CuisineDatabaseService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CuisinesListDto cuisines = cuisineService.getCuisines();
        PrintWriter writer = resp.getWriter();
        writer.print(cuisines.toJson());
    }
}
