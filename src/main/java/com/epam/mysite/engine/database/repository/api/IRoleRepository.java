package com.epam.mysite.engine.database.repository.api;

import com.epam.mysite.domain.entity.RoleEntity;

import java.sql.SQLException;

public interface IRoleRepository {
    RoleEntity findRoleByUserLogin(String login) throws SQLException;
}
