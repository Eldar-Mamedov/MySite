package com.epam.mysite.engine.database;

import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

@Log4j
public class DataBaseConnection {
    private static DataBaseConnection dbManager;
    private Connection connection;

    private DataBaseConnection() {
        try (InputStream inputStream = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("application.properties")).getPath())) {
            Properties properties = new Properties();
            properties.load(inputStream);
            connection = getConnection(properties.getProperty("connection.url"));
        } catch (SQLException | IOException e) {
            log.info(e.toString());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataBaseConnection getInstance() {
        if (dbManager == null) {
            dbManager = new DataBaseConnection();
        }
        return dbManager;
    }

    private Connection getConnection(String connectionUrl) throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }
}
