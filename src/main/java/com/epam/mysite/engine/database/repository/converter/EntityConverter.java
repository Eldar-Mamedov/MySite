package com.epam.mysite.engine.database.repository.converter;


import com.epam.mysite.core.exception.converter.ConverterException;
import lombok.extern.log4j.Log4j;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.epam.mysite.util.IParser.fromJson;
import static com.epam.mysite.util.IParser.toJson;


@Log4j
public class EntityConverter {
    private EntityConverter() {
    }

    public static <T> T convert(ResultSet resultSet, Class<T> clazz) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            Map<String, String> values = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                values.put(metaData.getColumnName(i), resultSet.getString(i));
            }
            return fromJson(toJson(values), clazz);
        } catch (SQLException exception) {
            log.info(exception.toString());
        }
        throw new ConverterException(String.format("Cannot convert to %s", clazz.getName()));
    }
}
