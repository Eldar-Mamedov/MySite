package com.epam.mysite.engine.database.repository.content.impl;

import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.content.HeaderQuery;
import com.epam.mysite.engine.database.repository.content.api.IHeaderRepository;
import com.epam.mysite.engine.database.repository.converter.EntityConverter;
import com.epam.mysite.entity.content.Header;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Log4j
public class HeaderRepository implements IHeaderRepository {
    private final Connection connection;

    public HeaderRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }
    @Override
    public List<Header> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Header> headerList = new LinkedList<>();
        try {
            ps = connection.prepareStatement(HeaderQuery.GET_ALL.getQuery());
            ps.setString(1,System.getProperty("locale"));
            rs = ps.executeQuery();
            if(rs.next()){
                Header header = EntityConverter.convert(rs,Header.class);
                headerList.add(header);
            }
        } catch (SQLException exception) {
            log.info(exception.toString());
        }finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return headerList;
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
