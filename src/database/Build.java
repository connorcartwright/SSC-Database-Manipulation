package database;
import java.sql.*;

import javax.sql.*;

public class Build {
	
	private Connect connect;
	private Statement createStudentTable;
	private Connection dbConn;
	public String createTitles = "CREATE TABLE Titles (titleID int PRIMARY KEY, titleString varchar(20) NOT NULL);";
	private String createStudent = "CREATE TABLE Student (studentID int PRIMARY KEY, titleID int FOREIGN KEY, forename varchar(30), familyName varchar(30), dateOfBirth date);";

	public static void main(String args[]) {
		Build build = new Build();
	}
	
	
	
	public Build() {
		connect = new Connect();
		dbConn = connect.getConnection();
		createTitlesTable(); // ------------------------ I SHOULD POPULATE THE TABLES IN THE INDIVIDUAL CREATE METHODS
		Statement stmt = null;
//		try {
//			stmt = dbConn.createStatement();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			stmt.executeUpdate("DROP TABLE Titles");
//			System.out.println("TABLE DROPPED WEEEW");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void createTables() {
		// create Titles, Student, Lecturer, Module, Type, Marks, StudentContact, NextOfKinContact
		// use PREPARED statements
	}
	
	public void joinTables() {
		// not needed if I create the tables in the correct order
	}
	
	public void createTitlesTable() {
		try {
			PreparedStatement createTitlesTable = dbConn.prepareStatement(createTitles);
			createTitlesTable.execute();
			System.out.println("Created titles table");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createStudentTable() {
		
		try {
			PreparedStatement createStudentTable = dbConn.prepareStatement("CREATE TABLE Student (studentID int PRIMARY KEY, titleID int, forename varchar(30), familyName varchar(30), dateOfBirth date);");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void populateTitlesTable() {
		// bla bla bla
	}

}
