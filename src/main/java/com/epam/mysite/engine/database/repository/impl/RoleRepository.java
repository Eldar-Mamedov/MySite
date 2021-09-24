package com.epam.mysite.engine.database.repository.impl;

import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.repository.api.IRoleRepository;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;

@Log4j
public class RoleRepository implements IRoleRepository {
    private final Connection connection;

    public RoleRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }

}
