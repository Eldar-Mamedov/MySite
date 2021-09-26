package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.engine.controller.api.IRoleController;
import com.epam.mysite.engine.controller.api.IUserController;
import com.epam.mysite.engine.controller.impl.RoleController;
import com.epam.mysite.engine.controller.impl.UserController;
import com.epam.mysite.util.HttpServletRequestHelper;
import com.epam.mysite.util.converter.RequestConverter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.mysite.engine.webservice.util.WebUtils.storeLoggedInUser;
import static com.epam.mysite.util.converter.RequestConverter.convertFromBody;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final IUserController userController = new UserController();
    private final IRoleController roleController = new RoleController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current_page", "login");
        req.setAttribute("current_lang", System.getProperty("locale"));
        RequestDispatcher rd = req.getRequestDispatcher("pages/login.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = convertFromBody(req, User.class);
        Response response = userController.login(user);
        if (response.getStatus() == 200) {
            user = userController.getLoggedUser();
            response.setRedirect(String.format("%s/main", getServletContext().getContextPath()));
            Role role = roleController.getUserRole(user);
            Cookie roleCookie = new Cookie("Role", role.name());
            roleCookie.setMaxAge(60 * 30);
            Cookie userCookie = new Cookie("User", user.getLogin());
            userCookie.setMaxAge(60 * 30);
            resp.addCookie(roleCookie);
            resp.addCookie(userCookie);
            storeLoggedInUser(req.getSession(), user);
        }
        HttpServletRequestHelper.sendResponse(resp, response);
    }
}
