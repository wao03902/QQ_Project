package com.ivisoft.salon.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/qqdb?autoReconnect=true&useSSL=false";
    private static final String USER = "qq";
    private static final String PASSWORD = "qqqq";
    
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
