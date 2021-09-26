package com.epam.mysite.engine.database;

import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Log4j
public class DataBaseConnection {
    private static DataBaseConnection dbManager;
    private Connection connection;

    private DataBaseConnection() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = getConnection(resourceBundle.getString("connection.url"));
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
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
