package edu.cmu.tsp6.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import sun.security.jca.GetInstance;

/**
 * A Singleton class which holds connection to the 
 * underlying database. The DAOs get {@link Statement} from
 * this class and use it to execute SQL statements
 * 
 * @author Varokas
 */
public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://open.owlab.com:3306/CALENDARDB";
	private static final String USERNAME = "tsp6";
	private static final String PASSWORD = "tsp6";
	
	private static DatabaseConnection instance;
	private Connection connection;
	
	private DatabaseConnection() {
		try {
			connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static DatabaseConnection getInstance() {
		if(instance == null)
			instance = new DatabaseConnection();
		
		return instance;
	}
	
	/**
	 * Get the current connection
	 * 
	 * @return
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * Get a new statement to execute SQL commands
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public Statement getStatement() {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}
