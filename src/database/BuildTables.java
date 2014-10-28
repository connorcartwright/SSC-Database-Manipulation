package database;

import java.sql.*;

public class BuildTables {
	
	// individual sql strings for each table to be executed
	private String createLecturer = "CREATE TABLE Lecturer (lecturerID SERIAL PRIMARY KEY, titleID int REFERENCES Titles ON DELETE CASCADE, foreName varchar(30) NOT NULL, familyName varchar(30) NOT NULL);";
	private String createMarks = "CREATE TABLE Marks (studentID int REFERENCES Student ON DELETE CASCADE, moduleID int REFERENCES Module ON DELETE CASCADE, year int CHECK (year BETWEEN 1990 AND 2014), typeID int REFERENCES Types ON DELETE CASCADE, mark smallint CHECK (mark BETWEEN 0 AND 100) NOT NULL, notes text);";
	private String createModule = "CREATE TABLE Module (moduleID SERIAL PRIMARY KEY, moduleName varchar(100) UNIQUE NOT NULL, moduleDescription text NOT NULL, lecturerID int REFERENCES Lecturer ON DELETE CASCADE);";
	private String createNextOfKin = "CREATE TABLE NextOfKinContact (studentID int REFERENCES Student ON DELETE CASCADE, eMailAddress varchar(50) NOT NULL, postalAddress varchar(10) NOT NULL);";
	private String createStudent = "CREATE TABLE Student (studentID SERIAL PRIMARY KEY, titleID int REFERENCES Titles ON DELETE CASCADE, forename varchar(30) NOT NULL, familyName varchar(30) NOT NULL, dateOfBirth date);";
	private String createStudentContact = "CREATE TABLE StudentContact (studentID int REFERENCES Student ON DELETE CASCADE, eMailAddress varchar(50) NOT NULL, postalAddress varchar(10) NOT NULL)";
	private String createTitles = "CREATE TABLE Titles (titleID SERIAL PRIMARY KEY, titleString varchar(20) UNIQUE NOT NULL);";
	private String createTypes = "CREATE TABLE Types (typeID SERIAL PRIMARY KEY, typeString varchar(20) UNIQUE NOT NULL);";
	// connection object
	private Connection dbConn;
	private PopulateTables populateTables;

	public BuildTables(Connection dbConn) {
		this.dbConn = dbConn;
		createTables();
		populateTables = new PopulateTables(dbConn);
	}
	
	private void createTables() {
		createTitlesTable();
		createTypesTable();
		createStudentTable();
		createStudentContactTable();
		createNextOfKinTable();
		createLecturerTable();
		createModuleTable();
		createMarksTable();
	}
	
	private void createLecturerTable() {
		try {
			PreparedStatement stmt = dbConn.prepareStatement(createLecturer);
			stmt.execute();
			System.out.println("Created Lecturer table");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}			
	}

	private void createMarksTable() {
		try {
			PreparedStatement stmt = dbConn.prepareStatement(createMarks);
			stmt.execute();
			System.out.println("Created Marks table");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createModuleTable() {
		try {
			PreparedStatement stmt = dbConn.prepareStatement(createModule);
			stmt.execute();
			System.out.println("Created Module table");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createNextOfKinTable() {
		try {
			PreparedStatement stmt = dbConn.prepareStatement(createNextOfKin);
			stmt.execute();
			System.out.println("Created Next Of Kin Contact table");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createTitlesTable() {
		try {
			PreparedStatement stmt = dbConn.prepareStatement(createTitles);
			stmt.execute();
			System.out.println("Created Titles table");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createTypesTable() {
		try {
			PreparedStatement stmt = dbConn.prepareStatement(createTypes);
			stmt.execute();
			System.out.println("Created Types table");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createStudentTable() {
		
		try {
			PreparedStatement stmt = dbConn.prepareStatement(createStudent);
			stmt.execute();
			System.out.println("Created Student table");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createStudentContactTable() {
		try {
			PreparedStatement stmt = dbConn.prepareStatement(createStudentContact);
			stmt.execute();
			System.out.println("Created Student Contact table");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void clearDatabase() {
		String sql = "";
		Statement stmt = null;
		 try {
			stmt = dbConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// this loop executes the DROP TABLE statements one at a time
		for (int i = 0; i < 8; i++) {
			switch(i) {
			case 0: sql = "DROP TABLE Lecturer CASCADE"; break;
			case 1: sql = "DROP TABLE Titles CASCADE"; break;
			case 2: sql = "DROP TABLE Marks CASCADE"; break;
			case 3: sql = "DROP TABLE Module CASCADE"; break;
			case 4: sql = "DROP TABLE NextOfKinContact CASCADE"; break;
			case 5: sql = "DROP TABLE Types CASCADE"; break;
			case 6: sql = "DROP TABLE Student CASCADE"; break;
			case 7: sql = "DROP TABLE StudentContact CASCADE"; break;
			}
				
			try {
				stmt.executeUpdate(sql); // execute DROP statements
				System.out.println(sql + "executed.");
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
		
}
