package com.epam.mysite.engine.database.repository.content.impl;


import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.content.ServiceItemQuery;
import com.epam.mysite.engine.database.repository.content.api.IServiceItemRepository;
import com.epam.mysite.engine.database.repository.converter.EntityConverter;
import com.epam.mysite.entity.content.ServiceItem;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Log4j
public class ServiceItemRepository implements IServiceItemRepository {
    private final Connection connection;

    public ServiceItemRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public List<ServiceItem> findAllBySubcategory(String subcategory) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ServiceItem> serviceItems = new LinkedList<>();
        try {
            ps = connection.prepareStatement(ServiceItemQuery.GET_ALL_BY_SUBCATEGORY.getQuery());
            ps.setString(1, subcategory);
            ps.setString(2, System.getProperty("locale"));
            rs = ps.executeQuery();
            if (rs.next()) {
                ServiceItem serviceItem = EntityConverter.convert(rs, ServiceItem.class);
                serviceItems.add(serviceItem);
            }
        } catch (SQLException exception) {
            log.info(exception.toString());
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return serviceItems;
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.info(e.toString());
            }
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.info(e.toString());
            }
        }
    }
}
