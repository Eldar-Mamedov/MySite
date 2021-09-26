package com.epam.mysite.engine.database.repository.content.api;

import com.epam.mysite.domain.entity.content.ServicesEntity;

import java.sql.SQLException;
import java.util.List;

public interface IServicesRepository {
    List<ServicesEntity> findAllServices() throws SQLException;
}
