package servlet.users;

import itacademy.restaurants.model.User;
import itacademy.restaurants.service.UserService;
import itacademy.restaurants.service.impl.UserDatabaseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by aVa on 18.01.2017.
 */
@WebServlet(urlPatterns = "/administration/users")
public class Users extends HttpServlet {

    private static final String USERS_URL = "/WEB-INF/views/content/administration/users.jsp";
    private UserService userService = UserDatabaseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(USERS_URL);
        Set<User> listUsers = userService.getAll();
        req.setAttribute("listUsers", listUsers);
        dispatcher.forward(req, resp);
    }
}
