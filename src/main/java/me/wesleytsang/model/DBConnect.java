package me.wesleytsang.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Class DBConnect.
 */
public class DBConnect {
	
	/** The driver. */
	final String DRIVER = "com.mysql.jdbc.Driver";
	
	/** The path. */
	String path = null;
	
	/** The user name. */
	String userName = null;
	
	/** The password. */
	String password = null;
	
	/** The connection. */
	Connection conn = null;
	
	/** The statement. */
	Statement statement = null;
	
	/**
	 * Instantiates a new DB connect.
	 *
	 * @param path the path
	 * @param name the name
	 * @param pass the pass
	 */
	public DBConnect(String path, String name, String pass) {
		this.path = path;
		userName = name;
		password = pass;
	}
	
	/**
	 * Connect.
	 *
	 * @throws SQLException the SQL exception
	 * @throws Exception the exception
	 */
	public void connect() throws SQLException, Exception {
		Class.forName(DRIVER);
		System.out.println("Connecting...");
		conn = (Connection) DriverManager.getConnection(path, userName, password);	//login and form connection to the server 
		statement = conn.createStatement();
	}
	
	/**
	 * Close connection.
	 *
	 * @throws SQLException the SQL exception
	 * @throws Exception the exception
	 */
	public void closeConnection() throws SQLException, Exception {
		statement.close();
		conn.close();
	}
	
	/**
	 * Gets the statement.
	 *
	 * @return the statement
	 */
	public Statement getStatement() {
		return statement;
	}
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		return conn;
	}
}
