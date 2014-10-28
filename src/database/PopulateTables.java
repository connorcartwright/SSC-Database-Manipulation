package database;

import java.sql.*;

public class PopulateTables {
	
	private Connection dbConn;
	private String sql = ""; // variable used for sql statements
	private Statement stmt; // statement object used for the inserts

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
		populateTypesTable();
		populateStudentTable();
	}
	
	private void populateLecturerTable() {
		
	}
	
	private void populateMarksTable() {
		
	}

	private void populateModuleTable() {
		
	}
	
	private void populateNextOfKinTable() {
		
	}
	
	private void populateStudentTable() {
		for(int i = 0; i < 500; i++) {
			System.out.println(i);
			switch(i) {
				case 0: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (1, 'Connor', 'Cartwright', '1995-08-24');"; break;
				case 1: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (3, 'Helen', 'Crump', '1994-11-02');"; break; 
				case 2: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (1, 'Liam', 'Smith', '1994-09-11');"; break; 
				case 3: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (1, 'Alexandros', 'Hamilakis', '1995-08-12');"; break; 
				case 4: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (1, 'Michael', 'Dewsbury', '1995-08-18');"; break; 
				case 5: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (3, 'Gina', 'O'Connor', '1995-01-30');"; break; 
				case 6: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (3, 'Abigail', 'Callaghan', '1994-09-20');"; break; 
				case 7: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (1, 'Daniel', 'Rowland', '1994-09-29');"; break; 
				case 8: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (2, 'Laura', 'White', '1995-06-24');"; break; 
				case 9: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (1, 'Liam', 'Tierney', '1995-02-09');"; break; 
				case 10: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES (1, 'John', 'Barnard', '1994-12-28');"; break; 
				default: sql = "INSERT INTO Student (titleID, forename, familyName, dateOfBirth) VALUES ("
									+ RandomName.getTitle() + ", " + RandomName.getForename() + ", " + RandomName.getSurname() + ", '2000-01-01'"; break;
			}
			
			try {
				stmt.executeUpdate(sql); // execute INSERT statements
				System.out.println("Populated Student Table: " + sql);
			} catch (SQLException e) {
				System.out.println("in catch clause");
				e.printStackTrace();
			}	
		}
		
	}
	
	private void populateStudentContactTable() {
		
	}
	
	public void populateTitlesTable() {
		 for(int i = 0; i < 6; i++) {
			 switch(i) {
				 case 0: sql = "INSERT INTO Titles (titleString) VALUES ('Mr');"; break;
				 case 1: sql = "INSERT INTO Titles (titleString) VALUES ('Mrs');"; break;
				 case 2: sql = "INSERT INTO Titles (titleString) VALUES ('Miss');"; break;
				 case 3: sql = "INSERT INTO Titles (titleString) VALUES ('Ms');"; break;
				 case 4: sql = "INSERT INTO Titles (titleString) VALUES ('Dr');"; break;
				 case 5: sql = "INSERT INTO Titles (titleString) VALUES ('Madam');"; break;
			 }
			 
				try {
					stmt.executeUpdate(sql); // execute INSERT statements
					System.out.println("Populated Titles Table: " + sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}		 
		 }
	}

	private void populateTypesTable() {
		for(int i = 0; i < 3; i++) {
			 switch(i) {
				 case 0: sql = "INSERT INTO Types (typeString) VALUES ('Sit');"; break;
				 case 1: sql = "INSERT INTO Types (typeString) VALUES ('Resit');"; break;
				 case 2: sql = "INSERT INTO Types (typeString) VALUES ('Repeat');"; break;
			 }
			 
				try {
					stmt.executeUpdate(sql); // execute INSERT statements
					System.out.println("Populated Types Table: " + sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}	
		}
	}
	
}
