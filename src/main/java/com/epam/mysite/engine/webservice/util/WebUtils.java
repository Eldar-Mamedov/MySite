package com.epam.mysite.engine.webservice.util;

import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.engine.database.repository.api.IRoleRepository;
import com.epam.mysite.engine.database.repository.api.IUserRepository;
import com.epam.mysite.engine.database.repository.impl.RoleRepository;
import com.epam.mysite.engine.database.repository.impl.UserRepository;
import com.epam.mysite.util.IParser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class WebUtils {
    private WebUtils() {
    }

    private static final IUserRepository userRepository = new UserRepository();
    private static final IRoleRepository roleRepository = new RoleRepository();

    public static User getLoggedIndUser(HttpSession session, Cookie[] cookies) {
        User user = (User) session.getAttribute("loggedInUser");
        Cookie userCookie = getUserCookie(cookies);
        if (user == null && userCookie != null) {
            try {
                user = IParser.fromJson(IParser.toJson(userRepository.findUserByLogin(userCookie.getValue())), User.class);
                user.setRole(roleRepository.findRoleByUserLogin(user.getLogin()).getRole());
                storeLoggedInUser(session, user);
            } catch (SQLException e) {
                user = null;
            }
        }
        return user;
    }

    public static Cookie getRoleCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie defaultCookie = new Cookie("Role", Role.Unauthorized.name());
        if (cookies == null) {
            return defaultCookie;
        }
        Cookie currentRole = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("Role")).findFirst().orElse(defaultCookie);
        User user = (User) request.getSession().getAttribute("loggedInUser");
        if (user != null && !Objects.equals(user.getRole(), currentRole.getValue())) {
            currentRole.setValue(user.getRole());
        }
        return currentRole;
    }

    public static void storeLoggedInUser(HttpSession session, User loggedInUser) {
        session.setAttribute("loggedInUser", loggedInUser);
    }

    private static Cookie getUserCookie(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }
        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("User")).findFirst().orElse(null);
    }
}
