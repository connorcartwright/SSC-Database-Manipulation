package database;
import java.sql.*;

public class Connect {

	private String dbName = "jdbc:postgresql://dbteach2.cs.bham.ac.uk/cpc455";
	private String user = "cpc455";
	private String password = "tispipru";
	private Connection dbConn = null;
	
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
			dbConn.setAutoCommit(false);
		}
		catch (SQLException ex) {
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