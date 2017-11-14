package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatenbankUnicorn {
	
	// JDBC driver name and database URL 
		static final String DB_URL = "jdbc:mysql://Db4free.net:3306/cuteunicornfight?user=verenagurtner&password=Passwort17&useSSL=false";

		//  Database credentials
		static final String USER = "verenagurtner";
		static final String PASS = "Passwort17";

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
	
	public static void DatenbankErzeugung()
	{
		System.out.println("Creating database...");
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "CREATE TABLE if not exists Employee ("
					+ "id SERIAL primary key,"
					+ "first character varying not null," 
					+ "last character varying not null,"
					+ "age int)";
			stmt.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Tabelle wurde erstellt");

	}

}
