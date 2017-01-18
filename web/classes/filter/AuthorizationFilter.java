package filter;

import itacademy.restaurants.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aVa on 14.01.2017.
 */
@WebFilter(servletNames = {"restaurants"})
public class AuthorizationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute("USER");
        Set<AuthenticationRoles> roles = new HashSet<>();
        if (user != null) {
            roles.add(AuthenticationRoles.USER);
            if (new Authentication<>().isAccess(user, roles)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        ((HttpServletResponse) servletResponse).sendRedirect("/login");
    }

    @Override
    public void destroy() {

    }
}
