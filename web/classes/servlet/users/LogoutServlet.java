package servlet.users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by aVa on 14.01.2017.
 */
@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private static final String LOGIN_URL = "/login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(LOGIN_URL);
        dispatcher.forward(req,resp);
    }
}
