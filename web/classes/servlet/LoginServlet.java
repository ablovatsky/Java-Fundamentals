package servlet;

import itacademy.restaurants.dao.ExceptionDao;
import itacademy.restaurants.model.User;
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
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserDatabaseService userDatabaseService;

    public LoginServlet() {
        userDatabaseService = new UserDatabaseService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = null;
        try {
            user = userDatabaseService.getUserByNameAndPassword(username, password);
        } catch (ExceptionDao | SQLException exceptionDao) {
            exceptionDao.printStackTrace();
        }
        if (user != null) {
            session.setAttribute("USER", user);
            resp.sendRedirect("/views/content/restaurants.jsp");
            return;
        }
        resp.sendRedirect("/views/login.jsp?error=error");
    }
}
