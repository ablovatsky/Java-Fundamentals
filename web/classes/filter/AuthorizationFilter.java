package filter;

import itacademy.restaurants.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aVa on 14.01.2017.
 */
@WebFilter(urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        if (url.contains("/resources") || url.contains("/setLang")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute("USER");
        Set<AuthenticationRoles> roles = new HashSet<>();

        if (user != null) {
            roles.add(AuthenticationRoles.USER);
            if (url.contains("/administration")) {
                roles.add(AuthenticationRoles.ADMIN);
            }
            if (new Authentication<>().isAccess(user, roles)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                String newUrl = ((HttpServletRequest) servletRequest).getHeader("referer").replaceAll("http://localhost:8080", "");
                ((HttpServletResponse) servletResponse).sendRedirect(newUrl);
                return;
            }
        }
        if (url.contains("/login") || url.contains("/registration")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        ((HttpServletResponse) servletResponse).sendRedirect("/login");
    }

    @Override
    public void destroy() {

    }
}
