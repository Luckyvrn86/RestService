package org.ssemchenko.restservice.util;

import java.sql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionManager {
    public static final String URL_KEY = PropertiesUtil.get("db.url");
    public static final String USERNAME_KEY = PropertiesUtil.get("db.username");
    public static final String PASSWORD_KEY = PropertiesUtil.get("db.password");

    private ConnectionManager(){
    }
    public static Connection open() {
        try {
            return DriverManager.getConnection(URL_KEY, USERNAME_KEY, PASSWORD_KEY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
