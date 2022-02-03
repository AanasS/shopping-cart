package com.aanass.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String HOST = "127.0.0.1";
	private static final int PORT = 3306;
	private static final String DB_NAME = "ecommerce_cart";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	private static Connection connection;
	
	public static Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DB_NAME),USERNAME,PASSWORD);
			
			System.out.println("Connected");
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return connection;
	}
}
