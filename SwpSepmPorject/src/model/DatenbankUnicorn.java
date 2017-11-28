package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatenbankUnicorn {

	// JDBC driver name and database URL 
	static final String DB_URL = "jdbc:mysql://localhost:3306/cuftd?user=root&password=&useSSL=false";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "";

	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;


	public static void main(String[] args) {
		VerbindungAufbauen();

	}

	public static void VerbindungAufbauen()
	{
		System.out.println("Connecting to database...");
		try {
			conn = DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Verbindung wurde aufgebaut");
	}


	

	// TODO
	public static void UnderworldinDB()
	{
		
		ArrayList<String[]> spielfeld = new ArrayList<String[]>();
		
		String sql_OworldinDB="Select odiif, xKoordinaten, yKoordinaten, username from OverworldField Join Spieler on username";
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql_OworldinDB);
			
			while(rs.next()){
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
