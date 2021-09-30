package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.engine.controller.api.IUserController;
import com.epam.mysite.engine.controller.impl.UserController;
import com.epam.mysite.util.converter.RequestConverter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.mysite.util.HttpServletRequestHelper.sendResponse;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private final IUserController userController = new UserController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current_page", "registration");
        req.setAttribute("current_lang", System.getProperty("locale"));
        RequestDispatcher rd = req.getRequestDispatcher("pages/registration.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = RequestConverter.convertFromBody(req, User.class);
        Response response = userController.saveUser(user, Role.Client);
        if (response.getStatus() == 201) {
            response.setRedirect(String.format("%s/login", getServletContext().getContextPath()));
        }
        sendResponse(resp, response);
    }
}
