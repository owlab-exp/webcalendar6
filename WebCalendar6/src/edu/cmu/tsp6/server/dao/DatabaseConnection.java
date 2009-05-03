package edu.cmu.tsp6.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A Singleton class which holds connection to the 
 * underlying database. The DAOs get {@link Statement} from
 * this class and use it to execute SQL statements
 * 
 * @author Varokas
 */
public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://open.owlab.com:3306/CALENDARDB?autoReconnect=true";
	private static final String USERNAME = "tsp6";
	private static final String PASSWORD = "tsp6";
	private static final String className = "com.mysql.jdbc.Driver";
	private static DatabaseConnection instance;
	private Connection connection;
	
	private DatabaseConnection() {
		try {
			Class.forName(className).newInstance();
			connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
			if(connection == null ) {
				DriverManager.getConnection(URL,USERNAME, PASSWORD);
			}
			return connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}
