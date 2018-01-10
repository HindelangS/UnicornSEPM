package model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import control.UnderworldField;
import view1.UnderworldK;


public class DatenbankUnicorn {

	// JDBC driver name and database URL 
	static final String DB_URL = "jdbc:mysql://localhost:3306/cuftd?user=root&password=&useSSL=false";

	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	static boolean wert=true;

	//zum Testen
	static String username="Kerber";
	static String password="34678215";
	static int uwid=4;
	static int level=35;
	static int geld=924;
	static int erfahrung=237;

	public static ArrayList <UnderworldField> UnderworldField;
	public static String[] test=new String[4];


	public static void main(String[] args) {
		SpielstandspeichernSpieler(username,level,geld,erfahrung);

//				try {
//					DatenbankUnicorn DBU = new DatenbankUnicorn();
//					Connection conn=DBU.getConnection();
//					List<Spieler> elemente = DBU.readSpieler(conn);;
//					for(Spieler e: elemente)
//					{
//						System.out.println(e);
//					}
//					DBU.releaseConnection(conn);
//				} catch (InstantiationException e) {
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				} catch(SQLException e){
//					e.printStackTrace();
//				}

		//spielerRegistrieren(username, password);

//		test[0]="1";
//		test[0]="1";
//		test[0]="1";
//		test[0]="";
//		UnderworldinDB();
//		writeFeldUW(test);
		
		UWerstellen("Verena");
		

	}


	public DatenbankUnicorn() throws InstantiationException, IllegalAccessException{
		super();
		// DB Driver init
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException
	{
		Connection conn = null;
		//neuen Connection holen
		try {
			conn=DriverManager.getConnection(DB_URL);


		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return conn;
	}

	public void releaseConnection(Connection conn)
	{
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Fehler beim schlieﬂen der Verbindung:");
			System.out.println("Meldung: "+e.getMessage());
			e.printStackTrace();
		}
	}


	public static ArrayList<String[]> OverworldinDBUpdate(int spieleranzahl)
	{

		int owid=1;
		ArrayList<String[]> spielfeldOW = new ArrayList<String[]>();

		String sql_OworldinDB="UPDATE overworld set anzahlSpieler =? WHERE owid = ?;";
		try {
			conn=DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(sql_OworldinDB);
			pstmt.setInt(1, spieleranzahl);
			pstmt.setInt(2, owid);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return spielfeldOW;
	}

	// TODO Fehlermeldung: Column 'username' in field list is ambiguous
	public static ArrayList<String[]> UnderworldinDB()
	{
		System.out.println("Test");
		ArrayList<String[]> spielfeldUW = new ArrayList<String[]>();

		String sql_UworldinDB="Select xKoordinaten, yKoordinaten, gebaeudeid, uwid, username from underworld Join underworldfield using(uwid)";
		try{

			conn=DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(sql_UworldinDB);
			rs=pstmt.executeQuery();

			System.out.println("Underworld ausgeben:");
			while(rs.next()){

				String daten[]=new String[5];
				System.out.print("Gelesen wurde: ");
				for (int i = 0; i < 5; i++) {
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
		String INSERT_DATA_SQL = "INSERT INTO underworldfield ( xKoordinaten, yKoordinaten, uwid, gebaeudeid) VALUES (?, ?, ?, ?)";

		//connection Aufbau
		try {
			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(INSERT_DATA_SQL);

			//System.out.println("HIIIIIII"); //zur Kontrolle

			//TODO ersten drei werte in int verwandeln
			pstmt.setString(1, testzeile2[0]);
			pstmt.setString(1, testzeile2[1]);
			pstmt.setString(1, testzeile2[2]);
			pstmt.setString(1, testzeile2[3]);
			//System.out.println(" '" + testzeile2[i-1] + "'");
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
		String INSERT_DATA_SQL = "INSERT INTO overworldfield ( xKoordinaten, yKoordinaten, owid, username) VALUES (?, ?, ?, ?)";

		//connection Aufbau
		try {
			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(INSERT_DATA_SQL);

			//TODO ersten drei werte in int verwandeln
			pstmt.setString(1, testzeile2[0]);
			pstmt.setString(1, testzeile2[1]);
			pstmt.setString(1, testzeile2[2]);
			pstmt.setString(1, testzeile2[3]);
			//System.out.println(" '" + testzeile2[i-1] + "'");
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			erfolg = false;
		}
		return erfolg;

	}

	public List<Spieler> readSpieler (Connection conn) throws SQLException
	{
		ArrayList<Spieler> spieler = new ArrayList<>();
		String SQL="select * from spieler";

		try {
			System.out.println("HIIIIII");
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String username=rs.getString(1);
				int password=rs.getInt(2);	
				int uwid=rs.getInt(3);
				int erfahrungspunkte=rs.getInt(4);
				int level=rs.getInt(5);
				int geldeinheiten=rs.getInt(6);
				

				Spieler zeile = new Spieler(erfahrungspunkte,geldeinheiten,level,password,username,uwid);
				spieler.add(zeile);
			}
			rs.close(); rs=null;
			pstmt.close(); pstmt=null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spieler;
	}

	public static int spielerEinloggen(String nickname, String passwort) throws InstantiationException, IllegalAccessException
	{
		String sql = "SELECT * FROM spieler WHERE username LIKE ?";

		try {
			pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, nickname);
			ResultSet rs = pstmt.executeQuery();

			// Wenn Feld fuer Benutzername nicht leer ist (wenn es einen Spieler mit diesem Nickname gibt)
			if(rs.next()) {

				if(rs.getString(2).equals(passwort)) {
					// Passwort und Nickname korrekt => einloggen
					// Testausgabe
					System.out.printf("Spieler %s wurde eingeloggt\n", rs.getString(1));
					// TODO auf Overworld kommen
				}
				else {
					// Passwort zu diesem Nickname ist falsch
					return 5;
				}
			}
			else {
				// Kein Spieler mit diesem Nickname vorhanden
				return 4;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			// Nullpointer tritt bei getconnection() auf, wenn keine Verbindung zur Datenbank moeglich
			return 1;
		}

		return 6;
	}

	public static boolean SpielstandspeichernSpieler(String username, int erfahrungspunkte, int geldeinheiten, int level)
	{

		String SQL="UPDATE spieler set erfahrungspunkte =?, geldeinheiten= ?, level= ? WHERE username = ?;";

		try {

			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, erfahrungspunkte);
			pstmt.setInt(2, geldeinheiten);
			pstmt.setInt(3, level);
			pstmt.setString(4, username);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			wert=false;
		}

		return wert;

	}
	// TODO
	public static boolean SpielstandspeichernOW(ArrayList<String[]> daten)
	{
		String SQL="UPDATE overworldfield set xKoordinaten =?, yKoordinaten= ?, username= ? WHERE owid = ?;";

		try {

			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQL);

			for(int i=1;i<=4;i++)
			{
				for(int j=1;j<=4;j++)
				{
					pstmt.setString(i, daten.get(i)[j]);	
				}
			}

			pstmt.executeUpdate();

			System.out.println(wert);
		} catch (SQLException e) {
			e.printStackTrace();
			wert=false;
		}

		return wert;

	}
	// TODO
	public static boolean SpielstandspeichernUW(ArrayList<String[]> daten)
	{
		String SQL="UPDATE underworldfield set xKoordinaten =?, yKoordinaten= ?, gebaeudeid= ? WHERE uwid = ?;";

		try {

			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQL);

			for(int i=1;i<=4;i++)
			{
				for(int j=1;j<=4;j++)
				{
					pstmt.setString(i, daten.get(i)[j]);	
				}
			}

			pstmt.executeUpdate();

			System.out.println(wert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			wert=false;
		}

		return wert;

	}

	public static int spielerRegistrieren(String username, String password2)
	{
		String sql = "INSERT INTO spieler (username, password, uwid ,erfahrungspunkte, level, geldeinheiten) values (?,?,?,?,?,?);";

		try {
			PreparedStatement stm = getConnection().prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, password2);
			stm.setInt(3, uwid);
			stm.setInt(4,0);
			stm.setInt(5,1);
			stm.setInt(6,0);
			stm.execute();

			// TODO Hier das objektorientierte Spieler-Objekt veraendern
			System.out.println("Ein neuer Spieler (" + username + ") wurde hinzugefuegt");
			// Erfolgreich
			return 0;

		} catch (SQLException  e) {

			e.printStackTrace();
			if(e.getClass() == MySQLIntegrityConstraintViolationException.class) {
				// Nickname bereits vergeben
				return 3;
			}
		}
		catch(NullPointerException e) {

			e.printStackTrace();
			// Nullpointer tritt bei getconnection() auf, wenn keine Verbindung zur Datenbank moeglich
			return 1;
		}
		// Unbekannter Fehler
		return 6;
	}

	public static String fehlercodeAufloesen(int fehlercode) {

		switch(fehlercode) {
		case 0: return "Kein Fehler aufgetreten";
		case 1: return "Es konnte keine Verbindung zur Datenbank aufgebaut werden";
		case 2: return "Es liegen keine Zugriffsrechte fuer die Datenbank vor";
		case 3: return "Dieser Nickname ist bereits vergeben!";
		case 4: return "Dieser Nickname ist nicht vorhanden. Moechten Sie sich registrieren?";
		case 5: return "Das Passwort fuer diesen Nickname ist falsch";
		default: return "Ein unbekannter Fehler ist aufgetreten";
		}
	}

	//TODO
	public static boolean UWerstellen(String username)
	{
		String SQL="Insert into underworld (gebaeudeanzahl, username) VALUES (gebaeudeanzahl='0',username= ?";

		try {
			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQL);

			//System.out.println("HIIIIIII"); //zur Kontrolle

			pstmt.setString(1, username);
			pstmt.executeUpdate();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return wert;
	}

}
