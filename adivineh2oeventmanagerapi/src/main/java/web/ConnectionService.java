package main.java.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.dao.EventDAO;
import main.java.dao.PersonDAO;
import main.java.dao.impl.EventDAOImpl;
import main.java.dao.impl.PersonDAOImpl;

//singelton
public abstract class ConnectionService {
	private static Connection connection;

	public static synchronized Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				if (connection == null) {
					Class.forName("org.postgresql.Driver");
				}
				if (connection == null ||connection.isClosed()) {
					connection = DriverManager.getConnection(DBProps.url, 
							DBProps.username, DBProps.password);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return connection;
	}
	
	public static void closeResources(Statement stmt, Connection connection) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
	
	public static PersonDAO getPersonDAO() {
		return new PersonDAOImpl();
	}

	public static EventDAO getEventDAO() {
		return new EventDAOImpl();
	}

}

