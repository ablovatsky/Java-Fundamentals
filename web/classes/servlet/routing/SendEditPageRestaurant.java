package servlet.routing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/administration/restaurant")
public class SendEditPageRestaurant extends HttpServlet{

    private static final String RESTAURANTS_URL = "/WEB-INF/views/content/edit_restaurant.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(RESTAURANTS_URL);
        session.setAttribute("routing", "edit");
        String id = req.getParameter("id");
        session.setAttribute("restaurant", id);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(RESTAURANTS_URL);
        session.setAttribute("routing", "edit");
        String id = req.getParameter("id");
        session.setAttribute("restaurant", id);
        dispatcher.forward(req, resp);
    }

}
