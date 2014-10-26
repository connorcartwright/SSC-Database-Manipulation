package database;
import java.sql.*;
import javax.sql.*;

public class Connect {

	String dbName = "jdbc:postgresql://dbteach2.cs.bham.ac.uk/cpc455";
	String user = "cpc455";
	String password = "tispipru";
	Connection dbConn = null;
	static Connect hello = null;
	
	public static void main(String args[]) {
		hello = new Connect();
	}
	
	
	
	public Connect()
	{
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("PostgreSQL driver registered.");
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Driver not found!");
			
		}
		
		try {
			dbConn = DriverManager.getConnection(dbName, user, password);
		}
		catch (SQLException ex) {
			System.out.println("hello olleh");
			ex.printStackTrace();	
		}
		
		if (dbConn != null)  {
			System.out.println("Database accessed!");
		}
		else {
			System.out.println("Failed to make connection!");
		}
	}
	
	public void closeConnection() {
		try {
			dbConn.close();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return dbConn;
	}
	

}