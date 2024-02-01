package org.ssemchenko.restservice;

import org.ssemchenko.restservice.db.ConnectionManager;
import org.ssemchenko.restservice.db.ConnectionManagerImpl;
import org.ssemchenko.restservice.util.SqlString;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
        try (Connection connection = connectionManager.getConnection()) {
            System.out.println(connection.getTransactionIsolation());
        }

    }
}
