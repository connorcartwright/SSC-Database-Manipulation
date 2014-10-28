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
//		createTables.clearDatabase();
	}
	
	public void populateTitlesTable() {
		// bla bla bla
	}

}
