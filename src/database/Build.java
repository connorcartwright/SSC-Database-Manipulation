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
			System.out.println("Committed Table creation!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void populateTables() {
		createTables.populateTables();
		try {
			dbConn.commit();
			System.out.println("Committed Table population!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clearDatabase() {
		createTables.clearDatabase();
		try {
			dbConn.commit();
			System.out.println("Committed database flush!");
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
	
	public void moduleTranscript(int moduleID, int year) {
		Statement stmt;
		ResultSet result;
		double failures = 0;
		double total = 0;
		double failurePercentage = 0;
		String countFailures = ("SELECT Count(Student.studentID) as count FROM Student, Marks WHERE Student.studentID = Marks.studentID AND marks.moduleID = "
							 + moduleID + " AND marks.mark < 40 AND Marks.year = " + year + ";"); 
		String countTotal = ("SELECT Count(Student.studentID) as count FROM Student, Marks WHERE Student.studentID = Marks.studentID AND marks.moduleID = "
							 + moduleID + "AND Marks.year = " + year + ";");
		failures = getSQLCount(countFailures);
		total = getSQLCount(countTotal);
		
		failurePercentage = ((failures / total) * 100);
		
		
		String moduleTranscript = ("SELECT Module.moduleID, Module.moduleName, Titles.titleString, Lecturer.foreName, "
								+ "Lecturer.familyName, COUNT(Student.studentID) as StudentMarkCount, AVG(Marks.mark) as AverageMark "
								+ "FROM Lecturer, Marks, Module, Student, Titles WHERE Student.studentID = Marks.studentID AND Titles.titleID = Lecturer.titleID AND marks.moduleID = " 
								+ moduleID + " AND marks.moduleID = module.moduleID AND Lecturer.lecturerID = Module.lecturerID AND marks.year = " + year 
										+ " GROUP BY Module.moduleID, Module.moduleName, Titles.titleString, Lecturer.foreName, Lecturer.familyName;");
		
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(moduleTranscript);
			while(result.next()) {
					System.out.println("Module ID: " + result.getInt(1));
					System.out.println("Module Name: " + result.getString(2));
					System.out.println("Lecturer title: " + result.getString(3));
					System.out.println("Lecturer forename: " + result.getString(4));
					System.out.println("Lecturer family name: " + result.getString(5));
					System.out.println("Number of Students: " + result.getInt(6));
					System.out.println("Average Mark: " + result.getInt(7));
					System.out.println("Failure Percentage: " + failurePercentage + "%");		
				}
			dbConn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void studentTranscript(int studentID) {
		Statement stmt;
		PreparedStatement stmt1;
		ResultSet studentResult;
		ResultSet markResults;
		int year = 0;
		String studentInfo = ("SELECT Student.studentID, Titles.titleString, Student.forename, Student.familyName, Student.dateOfBirth FROM Student, Titles WHERE Student.studentID = " 
						   + studentID + " AND Student.titleID = Titles.titleID;");
		
		String markInfo = ("SELECT Marks.year, Module.moduleID, Module.moduleName, Marks.mark, Types.typeString FROM Marks, Module, Student, Types WHERE Student.studentID = Marks.studentID AND "
						+ "Student.studentID = " + studentID + " AND Module.moduleID = Marks.moduleID AND Marks.typeID = Types.typeID GROUP BY Marks.year, Module.moduleID, Module.moduleName, "
						+ "Marks.mark, Types.typeString ORDER BY Marks.year, Module.moduleID;");
		
		try {
			stmt = dbConn.createStatement();
			stmt1 = dbConn.prepareStatement(markInfo, ResultSet.TYPE_SCROLL_INSENSITIVE, Statement.NO_GENERATED_KEYS);
			studentResult = stmt.executeQuery(studentInfo);
			markResults = stmt1.executeQuery();
			while(studentResult.next()) {
					System.out.println("Student ID: " + studentResult.getInt(1) + ", " + studentResult.getString(2) + " " + studentResult.getString(3) + " "
										+ studentResult.getString(4) + ", Date Of Birth: " + studentResult.getString(5));
					System.out.println();
					while(markResults.next()) {
						if(markResults.getRow() == 1) {
							year = markResults.getInt(1);
							System.out.println("Year: " + markResults.getInt(1) + ", ");
						}
						else if(year != markResults.getInt(1)) {
							System.out.println("Year: " + markResults.getInt(1) + ", ");
							year = markResults.getInt(1);
						}
						System.out.println("    Module ID: " + markResults.getInt(2));
						System.out.println("    Module Name: " + markResults.getString(3));
						System.out.println("    Mark: " + markResults.getString(4));
						System.out.println("    Type: " + markResults.getString(5));
						System.out.println();
					}
					System.out.println();
				}
			dbConn.commit();
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
		count = getSQLCount(sql);
		allStudents = new String[(getSQLCount(sql)+1)];

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
		count = getSQLCount(sql);
		allModules = new String[(getSQLCount(sql)+1)];
		
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
	
	public int getSQLCount(String sql) {
		int count = 0;
		Statement stmt;
		ResultSet result;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(sql);
			while(result.next()) {
				  count = result.getInt("count");
				}
			dbConn.commit();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean isPopulated() {
		try {
			PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM Titles");
			stmt.execute();
			dbConn.commit();
			return true;
		} catch (SQLException e) {
			try {
				dbConn.commit();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}
}
