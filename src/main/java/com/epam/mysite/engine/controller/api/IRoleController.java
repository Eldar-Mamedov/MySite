package com.epam.mysite.engine.controller.api;

import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.User;

public interface IRoleController {
    Role getUserRole(User user);
}
