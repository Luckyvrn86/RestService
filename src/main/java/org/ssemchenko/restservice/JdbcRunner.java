package org.ssemchenko.restservice;

import com.mysql.jdbc.Driver;
import org.ssemchenko.restservice.util.ConnectionManager;

import java.sql.Connection;

import java.sql.SQLException;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = ConnectionManager.open()) {
            System.out.println(connection.getTransactionIsolation());
        }
    }
}
