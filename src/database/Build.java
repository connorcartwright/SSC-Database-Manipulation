package database;
import java.sql.*;

public class Build {
	
	private Connect connect;
	private Connection dbConn;
	private BuildTables createTables;
	
	public static void main(String args[]) {
		Build build = new Build();
	}
	
	public Build() {
		connect = new Connect();
		dbConn = connect.getConnection();
		createTables = new BuildTables(dbConn);
	}
	
	public void createTables() {
		createTables.createTables();
		try {
			dbConn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void populateTables() {
		createTables.populateTables();
		try {
			dbConn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clearDatabase() {
		createTables.clearDatabase();
		try {
			dbConn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addNewStudent(int titleID, String forename, String familyName, String dob) {
		String newStudent = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (" + titleID + ", '" + forename + "', '" + familyName + "', '" + dob + "');"; 	
		try {
			PreparedStatement stmt = dbConn.prepareStatement(newStudent);
			stmt.execute();
			System.out.println("Added new student: " + forename + " " + familyName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
