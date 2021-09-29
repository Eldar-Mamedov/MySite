package com.epam.mysite.engine.controller.api;

import com.epam.mysite.domain.webservice.content.ServiceItem;
import com.epam.mysite.domain.webservice.content.Services;

import java.util.List;

public interface IServicesController {
    List<Services> getAllServices();

    List<ServiceItem> getAllServiceItems();
}
