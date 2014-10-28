package database;

import java.sql.*;
import java.util.Random;

public class PopulateTables {
	
	private Connection dbConn;
	private String sql = ""; // variable used for sql statements
	private Statement stmt; // statement object used for the inserts
	private Random rand = new Random();

	public PopulateTables(Connection dbConn) {
		this.dbConn = dbConn;
		 populateTables();
	}
	
	private void populateTables() {
		 try {
			stmt = dbConn.createStatement(); // get ready to create
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		populateTitlesTable();
		System.out.println("Titles table populated!");
		populateTypesTable();
		System.out.println("Types table populated!");
		populateStudentTable();
		System.out.println("Student table populated!");
		populateStudentContactTable();
		System.out.println("Student Contact table populated!");
		populateNextOfKinTable();
		System.out.println("Next Of Kin table populated!");
		populateLecturerTable();
		System.out.println("Lecturer table populated!");
		populateModuleTable();
		System.out.println("Module table populated!");
		populateMarksTable();
		System.out.println("Marks table populated!");
	}
	
	private void populateLecturerTable() {
		for(int i = 0; i < 20; i++) {
			switch(i) { // first case adds in realistic data set
				case 0: sql = "INSERT INTO Lecturer (titleID, foreName, familyName) VALUES (5, 'Peter', 'Lewis'), (5, 'Mark', 'Lee'), (5, 'Bob', 'Hendley'), "
						+ "(5, 'Shan', 'He'), (5, 'Volker', 'Sorge'), (5, 'John', 'Rowe'), (5, 'Alan', 'Sexton'), (5, 'Nick', 'Hawes');"; break;
				default: sql = "INSERT INTO Lecturer (titleID, foreName, familyName) VALUES (5, '" + RandomName.getForename() + "', '" + RandomName.getSurname() + "');"; break;
			}

			try {
				stmt.executeUpdate(sql); // execute INSERT statements
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void populateMarksTable() {
		 for(int i = 0; i < 100; i++) {
			 switch(i) {
				 case 0: sql = "INSERT INTO Marks (studentID, moduleID, year, typeID, mark) VALUES (1, 1, 2014, 1, 99), (1, 2, 2014, 1, 98), "
				 		+ "(1, 3, 2014, 1, 89), (1, 4, 2014, 1, 73), (1, 5, 2014, 1, 75), (1, 2, 2014, 1, 98), (2, 1, 2014, 1, 94), (2, 2, 2014, 1, 99), "
				 		+ "(3, 1, 2014, 1, 56), (4, 1, 2014, 1, 75), (5, 1, 2014, 1, 63), (6, 1, 2014, 1, 33), (7, 1, 2014, 1, 41), (8, 1, 2014, 1, 39), "
				 		+ "(9, 1, 2014, 1, 88), (10, 1, 2014, 1, 63), (11, 1, 2014, 1, 67);"; break;
				 default: sql = "INSERT INTO Marks (studentID, moduleID, year, typeID, mark) VALUES (" + (i + 11) + ", 1, 2014, " + (rand.nextInt(3) + 1) + ", " + (rand.nextInt(100) + 1) + ");";
			 }
				try {
					stmt.executeUpdate(sql); // execute INSERT statements
				} catch (SQLException e) {
					e.printStackTrace();
				}		 
		 }
	}

	private void populateModuleTable() {
		for(int i = 0; i < 50; i++) {
			switch(i) { // first case adds in realistic data set
				case 0: sql = "INSERT INTO Module (moduleName, moduleDescription, lecturerID) VALUES ('Software Workshop 1', 'Java Module 1...', 6), "
						+ "('Software Workshop 2', 'Java Module 2...', 6),  ('Robot Programming', 'Learn to Program robots...', 8), "
						+ "('AI', 'AI Module...', 5), ('Sofware Engineering', 'Engineer Software...', 7);"; break;
				default: sql = "INSERT INTO Module (moduleName, moduleDescription, lecturerID) VALUES ('Module" + (i + 5) + "', 'Generic Description', '9');"; break;
			}

			try {
				stmt.executeUpdate(sql); // execute INSERT statements
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void populateNextOfKinTable() {
		for(int i = 1; i < 51; i++) {
				sql = "INSERT INTO NextOfKinContact (studentID, eMailAddress, postalAddress) VALUES (" + i + ", 'studentNextOfKin" + i + "@email.com', 'P0ST C0D3');";
				try {
					stmt.executeUpdate(sql); // execute INSERT statements
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}	
	}
	
	private void populateStudentTable() {
		for(int i = 0; i < 115; i++) {
			switch(i) { // first case adds in realistic data set
				case 0: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (1, 'Connor', 'Cartwright', '1995-08-24'), "
						+ "(3, 'Helen', 'Crump', '1994-11-02'), (1, 'Liam', 'Smith', '1994-09-11'), (1, 'Alexandros', 'Hamilakis', '1995-08-12'), "
						+ "(1, 'Michael', 'Dewsbury', '1995-08-18'), (3, 'Gina', 'OConnor', '1995-01-30'), (3, 'Abigail', 'Callaghan', '1994-09-20'), "
						+ "(1, 'Daniel', 'Rowland', '1994-09-29'), (2, 'Laura', 'White', '1995-06-24'), (1, 'Liam', 'Tierney', '1995-02-09'), "
						+ "(1, 'John', 'Barnard', '1994-12-28');"; break;
				default: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (" + RandomName.getTitle() + ", '" + 
									RandomName.getForename() + "', '" + RandomName.getSurname() + "', '2000-01-01');"; break;
			}

			try {
				stmt.executeUpdate(sql); // execute INSERT statements
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
	}
	
	private void populateStudentContactTable() {
		for(int i = 0; i < 40; i++) {
			switch(i) { // first case adds in realistic data set
				case 0: sql = "INSERT INTO StudentContact (studentID, eMailAddress, postalAddress) VALUES (1, 'connorcart@gmail.com', 'B15 2RU'), "
						+ "(2, 'hnelepls@gmail.com', 'B15 2RU'), (3, 'smithyl@hotmail.com', 'WS12 4NF'), (4, 'alexandrosh@gmail.com', 'WS14 5AS'), "
						+ "(5, 'mikeydews@hotmail.co.uk', 'FB55 7YU'), (6, 'goc@yahoo.com', 'DA15 3YA'), (7, 'abic@hotmail.com', 'UL12 1AR'), "
						+ "(8, 'danrow@aol.com', 'LV14 1DA'), (9, 'lauraw@hotmail.com', 'FB55 7YU'), (10, 'liamtiern@gmail.com', 'WS11 2JC'), "
						+ "(11, 'johnjoe@gmail.com', 'WS11 2JC');"; break;
						
				default: sql = "INSERT INTO StudentContact (studentID, eMailAddress, postalAddress) VALUES (" + (i + 11) + ", 'student" + (i + 11) + "@bham.ac.uk', 'BA11 1NN');";
			}
			
			try {
				stmt.executeUpdate(sql); // execute INSERT statements
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public void populateTitlesTable() {
			 sql = "INSERT INTO Titles (titleString) VALUES ('Mr'), ('Mrs'), ('Miss'), ('Ms'), ('Dr'), ('Madam')  ;";
				try {
					stmt.executeUpdate(sql); // execute INSERT statements
				} catch (SQLException e) {
					e.printStackTrace();
				}		 
	}

	private void populateTypesTable() {
				 sql = "INSERT INTO Types (typeString) VALUES ('Sit'), ('Resit'), ('Repeat');";
				 try {
					stmt.executeUpdate(sql); // execute INSERT statements
				} catch (SQLException e) {
					e.printStackTrace();
				}	
	}
	
}
