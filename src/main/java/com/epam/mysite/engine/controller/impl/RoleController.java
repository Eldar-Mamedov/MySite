package com.epam.mysite.engine.controller.impl;

import com.epam.mysite.domain.entity.RoleEntity;
import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.engine.controller.api.IRoleController;
import com.epam.mysite.engine.database.repository.api.IRoleRepository;
import com.epam.mysite.engine.database.repository.impl.RoleRepository;
import lombok.SneakyThrows;

public class RoleController implements IRoleController {

    private final IRoleRepository roleRepository = new RoleRepository();

    @SneakyThrows
    @Override
    public Role getUserRole(User user) {
        RoleEntity roleEntity = roleRepository.findRoleByUserLogin(user.getLogin());
        return Role.valueOf(roleEntity.getRole());
    }
}
