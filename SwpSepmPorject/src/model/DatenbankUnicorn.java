package model;

import java.sql.Array;
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
	static ResultSet rs = null;


	public static void main(String[] args) {
		VerbindungAufbauen();
		UnderworldinDB();
		OverworldinDB();

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
	public static ArrayList<String[]> OverworldinDB()
	{

		ArrayList<String[]> spielfeldOW = new ArrayList<String[]>();

		String sql_OworldinDB="Select owfid, xKoordinaten, yKoordinaten, username, owid from overworldField Full Join overworld USING (owid)";
		try {
			conn=DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(sql_OworldinDB);
			rs=pstmt.executeQuery(sql_OworldinDB);

			System.out.println("Overworld ausgeben:");
			while(rs.next()){

				String daten[]=new String[5];
				System.out.print("Gelesen wurde: ");
				for (int i = 0; i < 10; i++) {
					daten[i] = rs.getString(i+1);
					System.out.print(" '" + daten[i] + "'");	//zur Kontrolle
				}
				spielfeldOW.add(daten);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return null;

		return spielfeldOW;
	}

	public static ArrayList<String[]> UnderworldinDB()
	{

		ArrayList<String[]> spielfeldUW = new ArrayList<String[]>();

		String sql_UworldinDB="Select udfid, xKoordinaten, yKoordinaten, gebaeudeid, uwid, username from underworld Join underworldfield using(uwid)";
		try{

			conn=DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(sql_UworldinDB);
			rs=pstmt.executeQuery();

			System.out.println("Underworld ausgeben:");
			while(rs.next()){

				String daten[]=new String[5];
				System.out.print("Gelesen wurde: ");
				for (int i = 0; i < 10; i++) {
					daten[i] = rs.getString(i+1);
					System.out.print(" '" + daten[i] + "'");	//zur Kontrolle
				}
				spielfeldUW.add(daten);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return null;

		return spielfeldUW;
	}
	
	public static boolean writeFeldUW(String[] testzeile2) {

		boolean erfolg = true;
		//SQL-Abfrag zum hineinschreiben neuer Daten
		String INSERT_DATA_SQL = "INSERT INTO underworldfield ( xKoordinaten, yKoordinaten, uwid, udfid, gebaeudeid) VALUES (?, ?, ?, ?, ?)";

		//connection Aufbau
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			pstmt = conn.prepareStatement(INSERT_DATA_SQL);

			//System.out.println("HIIIIIII"); //zur Kontrolle

			for (int i = 1; i <= 6; i++) {
				pstmt.setString(i, testzeile2[i-1]);
				System.out.println(" '" + testzeile2[i-1] + "'");
			}
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			erfolg = false;
		}
		return erfolg;

	}
	
	public static boolean writeFeldOW(String[] testzeile2) {

		boolean erfolg = true;
		//SQL-Abfrag zum hineinschreiben neuer Daten
		String INSERT_DATA_SQL = "INSERT INTO overworldfield ( xKoordinaten, yKoordinaten, owid, owfid, username) VALUES (?, ?, ?, ?, ?)";

		//connection Aufbau
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			pstmt = conn.prepareStatement(INSERT_DATA_SQL);

			//System.out.println("HIIIIIII"); //zur Kontrolle

			for (int i = 1; i <= 6; i++) {
				pstmt.setString(i, testzeile2[i-1]);
				System.out.println(" '" + testzeile2[i-1] + "'");
			}
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			erfolg = false;
		}
		return erfolg;

	}
}
