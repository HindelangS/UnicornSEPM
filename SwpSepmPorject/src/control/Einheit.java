package control;

public class Einheit {
	private int leben;
	private int schaden;
	private boolean isMoved;
	private int level = 1;
	private int movement = 3*level;
	public int getLeben() {
		return leben;
	}public int getMovement() {
		return movement;
	}public int getSchaden() {
		return schaden;
	}public int getLevel() {
		return level;
	}
	private int calculateSchaden(int schaden, int leben){
		int schadenberechnet;
		schadenberechnet = getLevel() * 2 + schaden;
		
		return schadenberechnet;
	}
	
	private int calculateLeben(int leben, int level){
		int lebenberechnet;
		lebenberechnet = level*2+leben;
		return lebenberechnet;
	}
	
	public void setSchaden(int schaden) {
		this.schaden = schaden;
	}
	
	public void setLeben(int leben) {
		this.leben = leben;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
