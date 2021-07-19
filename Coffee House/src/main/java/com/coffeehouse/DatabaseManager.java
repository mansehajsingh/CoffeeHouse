package com.coffeehouse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	private Connection connection;
	
	public DatabaseManager(String databasePath) {
		
		try {
			
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			this.connection = DriverManager.getConnection(databasePath);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Connection getConnection() {
		return this.connection;
	}

}
