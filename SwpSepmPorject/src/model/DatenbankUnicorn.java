package model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import control.UnderworldField;
import view.UnderworldK;


public class DatenbankUnicorn {

	// JDBC driver name and database URL 
	static final String DB_URL = "jdbc:mysql://localhost:3306/cuftd?user=root&password=&useSSL=false";

	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	static boolean wert=true;
	static private int anzahl;

	//zum Testen
	static String username="Sara";
	static String password="";
	//static int uwid=4;
	static int level=7;
	static int geld=600;
	static int erfahrung=68;

	public static ArrayList <UnderworldField> UnderworldField;
	public static String[] test=new String[4];


	public static void main(String[] args) {
		Geldgenerieren("sara");
		
		//SpielstandspeichernSpieler(username,erfahrung,geld,level);
//		String[]liste=new String[4];
//		liste[0]="0";
//		liste[1]="0";
//		liste[2]="4";
//		liste[3]="0";


		//writeFeldUW();

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

		//UWerstellen("Verena");


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


	public static int AnzahlEintr‰ge(Connection conn)
	{
		String SQL="select count(username) from spieler";

		try {
			conn=DriverManager.getConnection(DB_URL);
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				anzahl=rs.getInt(1);
				System.out.println(anzahl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return anzahl;
	}

	
	public static ArrayList<String[]> OverworldinDBUpdate(int spieleranzahl)
	{
		int owid=1;
		ArrayList<String[]> spielfeldOW = new ArrayList<String[]>();

		String sql_OworldinDB="UPDATE overworld set anzahlSpieler ='"+spieleranzahl+"' WHERE owid = '1';";
		try {
			conn=DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(sql_OworldinDB);
			pstmt.executeUpdate();

	

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return spielfeldOW;
	}

	public static boolean UWSPielerzuteilen(String username)
	{
		
		int uwidDB=0;
		int xKoordinate=0;
		int yKoordinate=0;

		//SQL-Abfrag zum hineinschreiben neuer Daten
		int gebeaudeanzahl=0;
		String INSERT_DATA_SQL = "INSERT INTO underworld (gebeaudeanzahl) VALUES (?)";

		//connection Aufbau
		try {
			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(INSERT_DATA_SQL);
			pstmt.setInt(1, gebeaudeanzahl);
			pstmt.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		String SQL ="select max(uwid) from underworld;";
		
		try {
			conn=DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQL);
			rs=pstmt.executeQuery(SQL);

			while(rs.next())
			{
				uwidDB = rs.getInt(1);
			}
			
			//pstmt.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String SQL1="UPDATE spieler set uwid= ? WHERE username = ?;";

		try {

			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQL1);
			pstmt.setInt(1, uwidDB);
			pstmt.setString(2, username);
			pstmt.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		String SQL3 ="select max(xKoordinaten) from overworldfield;";
		
		try {
			conn=DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQL3);
			rs=pstmt.executeQuery(SQL3);

			while(rs.next())
			{
				xKoordinate = rs.getInt(1);
			}
			
			//pstmt.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String SQL4="INSERT INTO overworldfield (xKoordinaten, yKoordinaten,username) VALUES (?,?,?)";

		try {

			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQL4);
			pstmt.setInt(1, xKoordinate+1);
			pstmt.setInt(2, yKoordinate);
			pstmt.setString(3, username);
			pstmt.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			wert=false;
		}

		return wert;
		

	}

	public static ArrayList<String[]> OverworldausDB()
	{

		ArrayList<String[]> spielfeldOW = new ArrayList<String[]>();

		String sql_OworldinDB="Select owfid, xKoordinaten, yKoordinaten, username, owid from overworldField Full Join overworld USING (owid)";
		try {
			conn=DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(sql_OworldinDB);
			rs=pstmt.executeQuery(sql_OworldinDB);

			System.out.println("Overworld ausgeben aus DB:");
			while(rs.next()){

				String daten[]=new String[3];

				String username = rs.getString("username"); 
				System.out.println("Username: "+ username);
				int xKoord = rs.getInt("xKoordinaten");
				int yKoord = rs.getInt("yKoordinaten");
				System.out.println("X:"+ xKoord +" Y: "+ yKoord);

				daten[0] = username;
				daten[1] = xKoord+""; 
				daten[2] = yKoord+"";

				spielfeldOW.add(daten);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return null;

		return spielfeldOW;
	}


	public static ArrayList<String[]> UnderworldausDB()
	{

		ArrayList<String[]> spielfeldUW = new ArrayList<String[]>();

		String sql_UworldinDB="Select xKoordinaten, yKoordinaten, gebaeudeid, uwid, username from underworld Join underworldfield using(uwid) JOIN spieler USING(uwid)";
		try{

			conn=DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(sql_UworldinDB);
			rs=pstmt.executeQuery();

			System.out.println("Underworld ausgeben:");
			while(rs.next()){

				int xKoor = rs.getInt("xKoordinaten");
				int yKoor = rs.getInt("yKoordinaten");
				int gebid = rs.getInt("gebaeudeid");
				int uwid = rs.getInt("uwid");
				String user = rs.getString("username");

				String daten[] = new String[5];


				//uwlist!!!!
				daten[0] = xKoor + "";
				daten[1] = yKoor + "";
				daten[2] = gebid + "";//welches haus	
				daten[3] = uwid + ""; //welche unterwelt !!! deine fremde1 fremde2 usw.. .......
				daten[4] = user;
				
				spielfeldUW.add(daten);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return null;

		return spielfeldUW;
	}
	
//	public static ArrayList<String[]> UnderworldausDBSpieler()
//	{
//		System.out.println("Test");
//		ArrayList<String[]> spielfeldUW = new ArrayList<String[]>();
//
//		String sql_UworldinDB="Select xKoordinaten, yKoordinaten, gebaeudeid, uwid, username from underworld Join underworldfield using(uwid) JOIN spieler USING(uwid) WHERE username='"+username+"';";
//		try{
//
//			conn=DriverManager.getConnection(DB_URL);
//			pstmt = conn.prepareStatement(sql_UworldinDB);
//			rs=pstmt.executeQuery();
//
//			System.out.println("Underworld ausgeben:");
//			while(rs.next()){
//
//				int xKoor = rs.getInt("xKoordinaten");
//				int yKoor = rs.getInt("yKoordinaten");
//				int gebid = rs.getInt("gebaeudeid");
//				int uwid = rs.getInt("uwid");
//				String user = rs.getString("username");
//
//				String daten[] = new String[5];
//
//
//				//uwlist!!!!
//				daten[0] = xKoor + "";
//				daten[1] = yKoor + "";
//				daten[2] = gebid + "";//welches haus	
//				daten[3] = uwid + ""; //welche unterwelt !!! deine fremde1 fremde2 usw.. .......
//				daten[4] = user;
//				
//				spielfeldUW.add(daten);
//
//			}
//
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		//return null;
//
//		return spielfeldUW;
//	}
	
	/**
	 * 
	 * @param username
	 * @return geld, erfahrung, level
	 */
	public static String[] SpielerSachen(String username) {

		String[] SpielStand = new String[3];
		
		String SQLinsert="select geldeinheiten,erfahrungspunkte, level from spieler WHERE username= ?";
		try {
			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQLinsert);
			pstmt.setString(1, username);

			rs=pstmt.executeQuery();

			while(rs.next())
			{
				System.out.print("Gelesen wurde: ");
				for (int i = 0; i < 3; i++) {
					SpielStand[i] = rs.getString(i+1);
					System.out.print(" '" + SpielStand[i] + "'");	//zur Kontrolle
				}
				System.out.println();
				geld = rs.getInt(1);
				erfahrung = rs.getInt(2);
				level = rs.getInt(3);
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SpielStand;
	}
	public static void writeFeldUW(String username,ArrayList<UnderworldField> liste) {

		int uwid=0;
		
		String SQLDelete="Delete uw From underworldField uw JOIN Spieler s USING(uwid) WHERE username='"+username+"';";
		
		try{
			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQLDelete);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		String SQLinsert="select uwid from underworld JOIN Spieler USING(uwid) WHERE username = '"+username+"';";
		try {
			conn = DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQLinsert);
			//pstmt.setString(1, username);
			System.out.println(SQLinsert);
			rs=pstmt.executeQuery();

			while(rs.next())
			{
				uwid = rs.getInt(1);
			}
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//SQL-Abfrag zum hineinschreiben neuer Daten
		String INSERT_DATA_SQL = "INSERT INTO underworldfield ( xKoordinaten, yKoordinaten, uwid, gebaeudeid) VALUES (?, ?, ?, ?)";

		for(int i=0;i<=liste.size()-1;i++)
		{
				if(liste.get(i).isBelegt())
				{
					
					
					String switcher = null;
					
					if(liste.get(i).getGeb‰ude() != null){
						switcher = liste.get(i).getGeb‰ude().getClass().getName();
					}
					if(liste.get(i).getHaus()!= null){
						switcher = liste.get(i).getHaus().getClass().getName();

										}
					if(liste.get(i).getZaun() != null){
						switcher = liste.get(i).getZaun().getClass().getName();

					}
		


					int gid=0;
					switch(switcher)
					{
					case "control.GebEnergie1":{
						gid=4;
						break;
					}case "control.GebEnergie2":{
						gid=5;
						break;
					
					}
					case "control.GebEnergie3":{
						gid=6;
						break;
					
					}
					case "control.HausEinheiten1":{
						gid=1;
						break;
					}case "control.HausEinheiten2":{
						gid=2;
						break;
					}case "control.HausEinheiten3":{
						gid=3;
						break;
					}case "control.ZaunEnergie1":{
						gid=7;
						break;
					}case "control.ZaunEnergie2":{
						gid=8;
						break;
					}case "control.ZaunEnergie3":{
						gid=9;
						break;
					}
					}
					
					
					
					try {
						conn = DriverManager.getConnection(DB_URL);
						pstmt = conn.prepareStatement(INSERT_DATA_SQL);

						//System.out.println("HIIIIIII"); //zur Kontrolle
					 INSERT_DATA_SQL = "INSERT INTO underworldfield ( xKoordinaten, yKoordinaten, uwid, gebaeudeid) VALUES ('"+liste.get(i).getX()+"', '"+liste.get(i).getY()+"','"+uwid+"', '"+gid+"')";

//						pstmt.setInt(1, liste.get(i).getX());
//						pstmt.setInt(2, liste.get(i).getY());
//						pstmt.setInt(3, uwid);
//						pstmt.setInt(4, gid);
						
						System.out.println(INSERT_DATA_SQL);
						//System.out.println(" '" + testzeile2[i-1] + "'");
						pstmt.executeUpdate(INSERT_DATA_SQL);
						
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
		}
		
		

		//connection Aufbau
		


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

		return 0;
	}

	/**
	 * 
	 * @param username
	 * @param erfahrungspunkte
	 * @param geldeinheiten
	 * @param level
	 * @return
	 */
	public static boolean SpielstandspeichernSpieler(String username, int erfahrungspunkte, int geldeinheiten, int level)
	{

		String SQL="UPDATE spieler set erfahrungspunkte =?, geldeinheiten= ?, level= ?, datum= CURDATE() WHERE username = ?;";

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

	public static int spielerRegistrieren(String username, String password2)
	{
		String sql = "INSERT INTO spieler (username, password ,erfahrungspunkte, level, geldeinheiten) values (?,?,?,?,?);";

		try {
			PreparedStatement stm = getConnection().prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, password2);
			//stm.setInt(3,uwid );
			stm.setInt(3,0);
			stm.setInt(4,1);
			stm.setInt(5,500);
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
		case 0: return "Anmeldung OK, Kein Fehler aufgetreten";
		case 1: 
			JOptionPane.showMessageDialog(null, "Fehler: Es konnte keine Verbindung zur Datenbank aufgebaut werden");
			return "Es konnte keine Verbindung zur Datenbank aufgebaut werden";
		case 2:
			JOptionPane.showMessageDialog(null, "Es liegen keine Zugriffsrechte fuer die Datenbank vor");
			return "Es liegen keine Zugriffsrechte fuer die Datenbank vor";
		case 3: 
			JOptionPane.showMessageDialog(null, "Dieser Nickname ist bereits vergeben!");
			return "Dieser Nickname ist bereits vergeben!";
		case 4:
			JOptionPane.showMessageDialog(null, "Dieser Nickname ist nicht vorhanden. Moechten Sie sich registrieren?");
			return "Dieser Nickname ist nicht vorhanden. Moechten Sie sich registrieren?";
		case 5: 
			JOptionPane.showMessageDialog(null, "Das Passwort fuer diesen Nickname ist falsch");
			return "Das Passwort fuer diesen Nickname ist falsch";
		default:
			JOptionPane.showMessageDialog(null, "Ein unbekannter Fehler ist aufgetreten");
			return "Ein unbekannter Fehler ist aufgetreten";
		}
	}


	public static void Geldgenerieren(String username)
	{
		String datumDB=null;
		//Date datumDB = null;
		Time zeit=null;
		Date datum=new Date();
		
		//Internet Query:
		String SQL ="INSERT INTO spieler (id, thomas, datum, timestamp) VALUES ('', '$thomas', '$current_date', UNIX_TIMESTAMP());";
		String SQL2="DELETE FROM spieler WHERE datum < UNIX_TIMESTAMP(DATE_SUB(NOW(), INTERVAL 1 hour));";
		
		
		String SQLtime = "select timestamp from spieler where username='"+username+"';";
		try {
			conn=DriverManager.getConnection(DB_URL);
			pstmt=conn.prepareStatement(SQLtime);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				datumDB=rs.getString(1);
				//datumDB=rs.getDate(1);
				//rs.getTime(1);
				System.out.println(anzahl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Datum aus DB:");
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:sss");
//   	String d2 = formatter.format(datumDB);
		
		System.out.println(datumDB);
		
		System.out.println("Datum aus Java mit Format:");
		String d = formatter.format(datum);
		System.out.println(d);
		
		int geldfaktor=0;
		
		
		//TODO Geld durch Differenz von timestamp und aktueller Zeit ausrechnen
		String SQL3="update spieler set geldeinheiten='"+geld+"' where username='"+username+"'";
		
		try {
			conn=DriverManager.getConnection(DB_URL);
			pstmt = conn.prepareStatement(SQL3);
			pstmt.executeUpdate();

	

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	

}
