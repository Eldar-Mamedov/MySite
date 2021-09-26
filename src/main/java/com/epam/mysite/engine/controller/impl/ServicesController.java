package com.epam.mysite.engine.controller.impl;

import com.epam.mysite.domain.entity.content.ServicesEntity;
import com.epam.mysite.domain.webservice.content.Services;
import com.epam.mysite.engine.controller.api.IServicesController;
import com.epam.mysite.engine.database.repository.content.api.IServicesRepository;
import com.epam.mysite.engine.database.repository.content.impl.ServicesRepository;
import com.google.gson.reflect.TypeToken;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.mysite.util.IParser.fromJson;
import static com.epam.mysite.util.IParser.toJson;

public class ServicesController implements IServicesController {
    private final IServicesRepository servicesRepository = new ServicesRepository();

    @Override
    public List<Services> getAllServices() {
        List<ServicesEntity> servicesEntities;
        List<Services> services;
        try {
            servicesEntities = servicesRepository.findAllServices();
            services = fromJson(toJson(servicesEntities), new TypeToken<List<Services>>() {
            }.getType());
        } catch (SQLException e) {
            services = new ArrayList<>();
        }
        return services;
    }
}
