package database;
import java.sql.*;

public class Build {
	
	private Connect connect;
	private static Connection dbConn;
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
			System.out.println("Commited Table creation!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void populateTables() {
		createTables.populateTables();
		try {
			dbConn.commit();
			System.out.println("Commited Table population!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clearDatabase() {
		createTables.clearDatabase();
		try {
			dbConn.commit();
			System.out.println("Commited database flush!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addNewStudent(int titleID, String forename, String familyName, String dob) {
		String newStudent = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (" + titleID + ", '" + forename + "', '" + familyName + "', '" + dob + "');"; 	
		System.out.println("SQL executed: " + newStudent);
		try {
			PreparedStatement stmt = dbConn.prepareStatement(newStudent);
			stmt.execute();
			dbConn.commit();
			System.out.println("Added new student: " + forename + " " + familyName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void markStudent(int studentID, int moduleID, int year, int typeID, int mark, String notes) {
		String newMark = "INSERT INTO Marks (studentID, moduleID, year, typeID, mark, notes) VALUES (" + studentID + ", " + moduleID + ", " + year + ", " + typeID + ", " + mark + ", '" + notes + "');";
		System.out.println("SQL executed: " + newMark);
		try {
			PreparedStatement stmt = dbConn.prepareStatement(newMark);
			stmt.execute();
			dbConn.commit();
			System.out.println("New Marks added for Student ID: " + studentID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getAllStudents() {
		String[] allStudents = null;
		String sql = "SELECT COUNT(*) AS count FROM Student";
		int count = 0;
		Statement stmt;
		ResultSet result;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(sql);
			while(result.next()) {
				  count = result.getInt("count");
				}
			allStudents = new String[count+1];
			dbConn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < (count + 1); i++) {
			sql = "SELECT forename, familyName FROM Student WHERE studentID = " + i + ";";
			try {
				stmt = dbConn.createStatement();
				result = stmt.executeQuery(sql);
				while(result.next()) {
					  allStudents[i] = result.getString("forename") + " " + result.getString("familyName");
					  dbConn.commit();
					}
				}
			catch (SQLException e) {
				e.printStackTrace();
				}
			}
		return allStudents;
	}
	
	public String[] getAllModules() {
		String[] allModules = null;
		String sql = "SELECT COUNT(*) AS count FROM Module";
		int count = 0;
		Statement stmt;
		ResultSet result;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(sql);
			while(result.next()) {
				  count = result.getInt("count");
				}
			allModules = new String[count+1];
			dbConn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < (count + 1); i++) {
			sql = "SELECT moduleName FROM Module WHERE moduleID = " + i + ";";
			try {
				stmt = dbConn.createStatement();
				result = stmt.executeQuery(sql);
				while(result.next()) {
					  allModules[i] = result.getString("moduleName");
					  dbConn.commit();
					}
				}
			catch (SQLException e) {
				e.printStackTrace();
				}
			}
		return allModules;
	}
}
