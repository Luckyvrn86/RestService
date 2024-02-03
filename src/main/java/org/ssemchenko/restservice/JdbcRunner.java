package org.ssemchenko.restservice;

import org.ssemchenko.restservice.db.ConnectionManager;
import org.ssemchenko.restservice.db.ConnectionManagerImpl;
import org.ssemchenko.restservice.util.SqlString;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        String a = null;
        String b = "3";
        System.out.println(Integer.valueOf(b));
        System.out.println(Integer.valueOf(a));


    }
}
