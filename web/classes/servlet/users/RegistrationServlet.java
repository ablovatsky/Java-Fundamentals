package servlet.users;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.model.User;
import itacademy.restaurants.service.ExceptionService;
import itacademy.restaurants.service.impl.UserDatabaseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by aVa on 14.01.2017.
 */
@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private UserDatabaseService userDatabaseService;

    public RegistrationServlet() {
        userDatabaseService = new UserDatabaseService();
    }

    private static final String REGISTRATION_URL = "/WEB-INF/views/registration.jsp";
    private static final String RESTAURANTS_URL = "/content/restaurants";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(REGISTRATION_URL);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        if (password.equals(confirmPassword)) {
            User user = new User(username, password, confirmPassword);
            try {
                userDatabaseService.add(user);
                session.setAttribute("USER", user);
                dispatcher = getServletContext().getRequestDispatcher(RESTAURANTS_URL);
                dispatcher.forward(req,resp);

            } catch (ExceptionService | ExceptionDao | SQLException exceptionService) {
                exceptionService.printStackTrace();
            }
        }
        dispatcher = getServletContext().getRequestDispatcher(REGISTRATION_URL);
        dispatcher.forward(req,resp);
    }
}
