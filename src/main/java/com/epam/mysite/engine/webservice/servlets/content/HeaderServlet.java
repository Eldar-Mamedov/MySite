package com.epam.mysite.engine.webservice.servlets.content;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.epam.mysite.engine.webservice.util.WebUtils.getRoleCookie;

public class HeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentLang = System.getProperty("locale");
        String secondLang = Objects.equals(currentLang, "ru") ? "en" : "ru";
        req.setAttribute("current_lang", currentLang);
        req.setAttribute("second_lang", secondLang);
        req.setAttribute("menu_items", createMenu(req));
        req.getParameterMap().forEach((key, value) -> req.setAttribute(key, value[0]));
        RequestDispatcher rd = req.getRequestDispatcher("components/header.jsp");
        rd.include(req, resp);
    }

    private Map<String, String> createMenu(HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("header", new Locale(System.getProperty("locale")));
        Map<String, String> menuItems = new LinkedHashMap<>();
        Cookie role = getRoleCookie(request);
        if (role.getValue().equals("Unauthorized")) {
            menuItems.put(String.format("%s/login", getServletContext().getContextPath()), resourceBundle.getString("menu.login"));
            menuItems.put(String.format("%s/registration", getServletContext().getContextPath()), resourceBundle.getString("menu.register"));
        } else {
            menuItems.put(String.format("%s/profile", getServletContext().getContextPath()), resourceBundle.getString("menu.profile"));
            switch (role.getValue()) {
                case "Client": {
                    menuItems.put(String.format("%s/history", getServletContext().getContextPath()), resourceBundle.getString("menu.history"));
                    break;
                }
                case "Admin": {
                    menuItems.put(String.format("%s/admin", getServletContext().getContextPath()), resourceBundle.getString("menu.admin"));
                }
                case "Master": {
                    menuItems.put(String.format("%s/orders", getServletContext().getContextPath()), resourceBundle.getString("menu.orders"));
                    break;
                }
            }
            menuItems.put(String.format("%s/logout", getServletContext().getContextPath()), resourceBundle.getString("menu.logout"));
        }
        return menuItems;
    }
}
