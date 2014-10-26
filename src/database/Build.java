package database;
import java.sql.*;
import javax.sql.*;

public class Build {
	
	private Connect connect;
	private Statement createStudentTable;
	private Connection dbConn;

	public Build() {
		connect = new Connect();
		dbConn = connect.getConnection();
	}
	
	public void createTables() {
		// create Student, Lecturer, Module, Titles, Type, Marks, StudentContact, NextOfKinContact
		// use PREPARED statements
	}
	
	public void joinTables() {
		// not needed if I create the tables in the correct order
	}
	
	public void createStudentTable() {
		
	}

}
