package servlet.administration;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import itacademy.restaurants.model.Restaurant;
import itacademy.restaurants.service.RestaurantService;
import itacademy.restaurants.service.impl.RestaurantDatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@MultipartConfig(maxFileSize = 169999999)
@WebServlet(urlPatterns = "/edit/restaurant")
public class EditRestaurant extends HttpServlet {

    private RestaurantService restaurantService;

    public EditRestaurant() {
        restaurantService = RestaurantDatabaseService.getInstance();
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getParameter("json");
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
        reader.setLenient(true);
        Restaurant restaurant = new Gson().fromJson(reader, Restaurant.class);
        Part partFile = req.getPart("image");
        InputStream image = null;
        if (partFile != null) {
            long fileSize = partFile.getSize();
            String fileContent = partFile.getContentType();
            image = partFile.getInputStream();
        }
        restaurant.setLoadingImage(image);
        restaurantService.update(restaurant);

    }

}
