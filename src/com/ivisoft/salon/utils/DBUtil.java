package com.ivisoft.salon.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost/qqdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "7309";
    
    public Connection connection;
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        
        Connection conn = null;
        
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}
