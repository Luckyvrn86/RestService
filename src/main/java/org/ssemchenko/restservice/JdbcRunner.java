package org.ssemchenko.restservice;

import org.ssemchenko.restservice.db.ConnectionManager;
import org.ssemchenko.restservice.db.ConnectionManagerImpl;
import org.ssemchenko.restservice.util.SqlString;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
        String sql = "select * from university.faculty";
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name"));
                System.out.println("-------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
