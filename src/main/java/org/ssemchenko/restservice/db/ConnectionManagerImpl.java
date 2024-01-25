package org.ssemchenko.restservice.db;

import org.ssemchenko.restservice.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class ConnectionManagerImpl implements ConnectionManager{
    public static final String URL_KEY = PropertiesUtil.get("db.url");
    public static final String USERNAME_KEY = PropertiesUtil.get("db.username");
    public static final String PASSWORD_KEY = PropertiesUtil.get("db.password");

    public ConnectionManagerImpl(){
    }
    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL_KEY, USERNAME_KEY, PASSWORD_KEY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
