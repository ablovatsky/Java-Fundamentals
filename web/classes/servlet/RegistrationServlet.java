package servlet;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        if (password.equals(confirmPassword)) {
            User user = new User(username, password, confirmPassword);
            try {
                userDatabaseService.add(user);
                session.setAttribute("USER", user);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/views/content/restaurants.jsp");
                requestDispatcher.forward(req, resp);

            } catch (ExceptionService | ExceptionDao | SQLException exceptionService) {
                exceptionService.printStackTrace();
            }
        }
    }
}
