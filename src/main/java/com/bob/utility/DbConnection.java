package com.bob.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	
	private static final String URL= "jdbc:mysql://localhost:3306/project";
	private static final String USER = "root";
	private static final String PASSWORD="root123";
	
	
	public final static Connection getConnection()
	{
	
		Connection connection = null;
		
		try {
			
			//Load the Driver 
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish the connection
			
			 connection =	DriverManager.getConnection(URL,USER,PASSWORD);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}


	
