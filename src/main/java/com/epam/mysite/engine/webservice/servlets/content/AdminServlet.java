package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.domain.webservice.UserDelete;
import com.epam.mysite.engine.controller.api.IEmployeeController;
import com.epam.mysite.engine.controller.api.IServicesController;
import com.epam.mysite.engine.controller.api.IUserController;
import com.epam.mysite.engine.controller.impl.EmployeeController;
import com.epam.mysite.engine.controller.impl.ServicesController;
import com.epam.mysite.engine.controller.impl.UserController;
import com.epam.mysite.engine.webservice.util.WebUtils;
import com.epam.mysite.util.converter.RequestConverter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.mysite.util.HttpServletRequestHelper.sendResponse;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    private final IServicesController servicesController = new ServicesController();
    private final IUserController userController = new UserController();
    private final IEmployeeController employeeController = new EmployeeController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current_page", "admin");
        req.setAttribute("current_lang", System.getProperty("locale"));
        req.setAttribute("services", servicesController.getAllServiceItems());
        req.setAttribute("employeeStaff", employeeController.getEmployeeStaffExcludeCurrentUser(WebUtils.getLoggedIndUser(req.getSession(), req.getCookies())));
        RequestDispatcher rd = req.getRequestDispatcher("pages/admin.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = RequestConverter.convertFromBody(req, User.class);
        Response response = userController.saveUser(user, Role.valueOf(user.getRole()));
        if (response.getStatus() == 201 && Role.valueOf(user.getRole()) == Role.Master) {
            response = employeeController.addSpecialityToEmployee(user);
            response.setStatus(201);
        }
        if (response.getStatus() == 201) {
            response.setRedirect(String.format("%s/admin", getServletContext().getContextPath()));
        }
        sendResponse(resp, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        UserDelete userDelete = RequestConverter.convertFromBody(req, UserDelete.class);
        Response response = userController.deleteUser(userDelete.getUserIds());
        if (response.getStatus() == 200) {
            response.setRedirect(String.format("%s/admin", getServletContext().getContextPath()));
        }
        sendResponse(resp, response);
    }
}