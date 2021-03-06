package main.java.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import main.java.dao.EventDAO;
import main.java.dao.PersonDAO;
import main.java.dao.impl.EventDAOImpl;
import main.java.dao.impl.PersonDAOImpl;

//singelton
public abstract class ConnectionManager {
	private static Connection connection;

	private static synchronized Properties getProperties() throws IOException {		
//	    /Users/shimatacb/Desktop/work/web_dev/Revature/Code/ShimataCRepo/adivineh2oeventmanager
	    FileInputStream fis = new FileInputStream("/Users/shimatacb/Desktop/work/web_dev/Revature/Code/ShimataCRepo/adivineh2oeventmanager/connection.prop");
		Properties p = new Properties();
		p.load(fis);
		return p;
	}

	public static synchronized Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				Properties p = getProperties();
				if (connection == null) {
					Class.forName("org.postgresql.Driver");
				}
				if (connection == null ||connection.isClosed()) {
					connection = DriverManager.getConnection(p.getProperty("hostname"), p.getProperty("username"),
							p.getProperty("password"));
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("can't find postgresql driver");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

