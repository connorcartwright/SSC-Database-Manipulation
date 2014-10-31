package database;

import java.sql.*;

public class BuildTables {
	
	// individual sql strings for each table to be executed
	private String createLecturer = "CREATE TABLE Lecturer (lecturerID SERIAL PRIMARY KEY, titleID int REFERENCES Titles ON DELETE CASCADE, foreName varchar(30) NOT NULL, familyName varchar(30) NOT NULL);";
	private String createMarks = "CREATE TABLE Marks (studentID int REFERENCES Student ON DELETE CASCADE, moduleID int REFERENCES Module ON DELETE CASCADE, year int CHECK (year BETWEEN 1980 AND 2014) NOT NULL, typeID int REFERENCES Types ON DELETE CASCADE, mark smallint CHECK (mark BETWEEN 0 AND 100) NOT NULL, notes text);";
	private String createModule = "CREATE TABLE Module (moduleID SERIAL PRIMARY KEY, moduleName varchar(100) UNIQUE NOT NULL, moduleDescription text NOT NULL, lecturerID int REFERENCES Lecturer ON DELETE CASCADE);";
	private String createNextOfKin = "CREATE TABLE NextOfKinContact (studentID int REFERENCES Student ON DELETE CASCADE, eMailAddress varchar(50) NOT NULL, postalAddress varchar(10) NOT NULL);";
	private String createStudent = "CREATE TABLE Student (studentID SERIAL PRIMARY KEY, titleID int REFERENCES Titles ON DELETE CASCADE, forename varchar(30) NOT NULL, familyName varchar(30) NOT NULL, dateOfBirth date);";
	private String createStudentContact = "CREATE TABLE StudentContact (studentID int REFERENCES Student ON DELETE CASCADE, eMailAddress varchar(50) NOT NULL, postalAddress varchar(10) NOT NULL)";
	private String createTitles = "CREATE TABLE Titles (titleID SERIAL PRIMARY KEY, titleString varchar(20) UNIQUE NOT NULL);";
	private String createTypes = "CREATE TABLE Types (typeID SERIAL PRIMARY KEY, typeString varchar(20) UNIQUE NOT NULL);";
	// string to drop tables
	private String dropTables = "DROP TABLE Lecturer, Titles, Marks, Module, NextOfKinContact, Types, Student, StudentContact CASCADE;";
	// connection object
	private Connection dbConn;
	private PopulateTables populateTables;

	public BuildTables(Connection dbConn) {
		this.dbConn = dbConn;
		populateTables = new PopulateTables(dbConn);
	}
	public void createTables() {
		createTitlesTable();
		createTypesTable();
		createStudentTable();
		createStudentContactTable();
		createNextOfKinTable();
		createLecturerTable();
		createModuleTable();
		createMarksTable();
	}
	
	public void populateTables() {
		populateTables.populateTables();
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
		 try {
			PreparedStatement stmt = dbConn.prepareStatement(dropTables);
			stmt.execute();
			System.out.println("Dropped all tables!");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}
