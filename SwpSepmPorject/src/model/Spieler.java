package model;

public class Spieler {
	int erfahrungspunkte;
	int geldeinheiten;
	int level;
	int password;
	String username;
	int uwid;
	
	public int getErfahrungspunkte() {
		return erfahrungspunkte;
	}
	public void setErfahrungspunkte(int erfahrungspunkte) {
		this.erfahrungspunkte = erfahrungspunkte;
	}
	public int getGeldeinheiten() {
		return geldeinheiten;
	}
	public void setGeldeinheiten(int geldeinheiten) {
		this.geldeinheiten = geldeinheiten;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUwid() {
		return uwid;
	}
	public void setUwid(int uwid) {
		this.uwid = uwid;
	}
	
	public Spieler(int erfahrungspunkte, int geldeinheiten, int level, int password, String username, int uwid) {
		super();
		this.erfahrungspunkte = erfahrungspunkte;
		this.geldeinheiten = geldeinheiten;
		this.level = level;
		this.password = password;
		this.username = username;
		this.uwid = uwid;
	}
	
	public Spieler() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "Spieler [erfahrungspunkte=" + erfahrungspunkte + ", geldeinheiten=" + geldeinheiten + ", level=" + level
				+ ", password=" + password + ", username=" + username + ", uwid=" + uwid + "]";
	}
	
	
	
}
