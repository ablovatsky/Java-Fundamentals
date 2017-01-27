package servlet.administration;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import itacademy.restaurants.model.City;
import itacademy.restaurants.model.Country;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/administration/getCities")
public class GetCitiesServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JsonReader reader = new JsonReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        reader.setLenient(true);
        Country country = new Gson().fromJson(reader, Country.class);
        Gson gson = new Gson();
        String cities = gson.toJson(country.getCities());
        reader.close();
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("OK");
        printWriter.flush();
    }
}
