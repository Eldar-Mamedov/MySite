package com.epam.mysite.engine.webservice.servlets.filter;

import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.engine.webservice.servlets.UserRoleRequestWrapper;
import com.epam.mysite.engine.webservice.util.SecurityUtils;
import com.epam.mysite.engine.webservice.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.mysite.engine.webservice.util.SecurityUtils.hasPermission;
import static com.epam.mysite.engine.webservice.util.WebUtils.getLoggedIndUser;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        User loggedInUser = getLoggedIndUser(request.getSession(), request.getCookies());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loggedInUser != null) {
            String login = loggedInUser.getLogin();
            Cookie roleCookie = WebUtils.getRoleCookie(request);
            wrapRequest = new UserRoleRequestWrapper(login, Role.valueOf(roleCookie.getValue()), request);
        }

        if (SecurityUtils.isSecurityPage(request)) {
            if (loggedInUser == null) {
                response.sendRedirect(String.format("%s/main", wrapRequest.getContextPath()));
                return;
            }

            boolean hasPermission = hasPermission(wrapRequest);
            if (!hasPermission) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/main");
                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(wrapRequest, response);
    }
}
