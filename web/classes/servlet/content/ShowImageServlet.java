package servlet.content;

import itacademy.restaurants.model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.Set;

@WebServlet(urlPatterns = "/image")
public class ShowImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        OutputStream out = resp.getOutputStream();
        int index = Integer.valueOf(req.getParameter("index"));
        LinkedHashSet<Restaurant> restaurants = (LinkedHashSet<Restaurant>) req.getSession(false).getAttribute("restaurants");
        restaurants.forEach((restaurant) -> {
            if (restaurant.getId() == index) {
                resp.setContentLength(restaurant.getImage().length);
                try {
                    out.write(restaurant.getImage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        out.close();
    }
}
