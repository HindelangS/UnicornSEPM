package control;

public class Einheit {
	int leben;
	int schaden;
	boolean isMoved;
	int level;
	
	private int calculateSchaden(int schaden, int leben){
		int schadenberechnet;
		schadenberechnet = level * 2 + schaden;
		
		return schadenberechnet;
	}
	
	private int calculateLeben(int leben, int level){
		int lebenberechnet;
		lebenberechnet = level*2+leben;
		return lebenberechnet;
	}

}
