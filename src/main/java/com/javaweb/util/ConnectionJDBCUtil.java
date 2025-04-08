package com.javaweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBCUtil {
	static final String DB_URL = "jdbc:mysql://localhost:3306/building_rental";
    static final String USER = "root";
    static final String PASS = "Binh25072005";

	    public static Connection getConnection() {
	        Connection conn=null;
	        
	        try {
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return conn;
	    }
}
