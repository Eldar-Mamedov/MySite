package com.epam.mysite.engine.webservice.servlets;

import com.epam.mysite.domain.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private final String user;
    private final Role role;
    private final HttpServletRequest httpServletRequest;

    public UserRoleRequestWrapper(String user, Role role, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.role = role;
        this.httpServletRequest = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (this.role == null) {
            return this.httpServletRequest.isUserInRole(role);
        }
        return this.role.name().equals(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return httpServletRequest.getUserPrincipal();
        }
        return () -> user;
    }
}
