package com.bankingapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DaoConnection {

	private static final Logger log = Logger.getLogger(DaoConnection.class);
	private static final String url = "jdbc:postgresql://"+System.getenv("TRAINING_DB_URL")+"/bankingapp";
	private static final String user = System.getenv("TRAINING_DB_USERNAME");
	private static final String password = System.getenv("TRAINING_DB_PASSWORD");
	private static Connection conn = null;
	
	public static Connection getConnection(){
		
		if(conn == null) {
			try {
				Class.forName("org.postgresql.Driver");
				log.info("Driver registered successfully");
			} catch (ClassNotFoundException e) {
				System.out.println("could not register driver");
				log.info("Driver could not resister");
				e.printStackTrace();
			}
			
			try {
				conn = DriverManager.getConnection(url, user, password);
				log.info("Database connected successfully");
			} catch (SQLException e) {
				System.out.println("connection failed");
				log.info("Database connection failed");
				e.printStackTrace();
			}
			
		}
		
		try {
			if(conn.isClosed()) {
				conn = DriverManager.getConnection(url, user, password);
				log.info("Database re-connected successfully");
			}
		} catch (SQLException e) {
			log.info("Database re-connection failed");
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
}




