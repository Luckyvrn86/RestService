package org.ssemchenko.restservice.db;

import org.ssemchenko.restservice.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManagerImpl implements ConnectionManager{
    private String url = PropertiesUtil.get("db.url");
    private String username = PropertiesUtil.get("db.username");
    private String password = PropertiesUtil.get("db.password");

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionManagerImpl(){
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
