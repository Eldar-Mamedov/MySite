package com.epam.mysite.engine.controller.api;


import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;

import java.util.List;

public interface IUserController {
    User getLoggedUser();

    Response saveUser(User user, Role role);

    Response login(User user);

    Response deleteUser(List<Integer> userIds);
}
