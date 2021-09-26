package com.epam.mysite.engine.database.repository.content.impl;

import com.epam.mysite.domain.entity.content.ServiceItemEntity;
import com.epam.mysite.domain.entity.content.ServicesEntity;
import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.content.ServicesQuery;
import com.epam.mysite.engine.database.repository.content.api.IServicesRepository;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.mysite.util.converter.EntityConverter.convert;

@Log4j
public class ServicesRepository implements IServicesRepository {
    private final Connection connection;

    public ServicesRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public List<ServicesEntity> findAllServices() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ServicesEntity> servicesEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(ServicesQuery.GET_SERVICES_BY_LOCALE.getQuery());
            ps.setString(1, System.getProperty("locale"));
            rs = ps.executeQuery();
            while (rs.next()) {
                ServicesEntity servicesEntity = convert(rs, ServicesEntity.class);
                ServiceItemEntity serviceItemEntity = convert(rs, ServiceItemEntity.class);
                addServicesEntityToList(servicesEntities, servicesEntity, serviceItemEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return servicesEntities;
    }

    private void addServicesEntityToList(List<ServicesEntity> servicesEntities, ServicesEntity servicesEntity, ServiceItemEntity serviceItem) {
        ServicesEntity fromList = servicesEntities.stream().filter(e -> e.getName().equals(servicesEntity.getName())).findFirst().orElse(new ServicesEntity());
        if (fromList.getName() == null) {
            fromList = servicesEntity;
            servicesEntities.add(fromList);
        }
        fromList.addServiceItem(serviceItem);
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
