package control;

public class Gebäude {
	int leben;
	int level;
	
	public Gebäude(int level) {
		this.level = level;
		this.leben = berechneLeben(level);
		// TODO Auto-generated constructor stub
	}
	
	private int berechneLeben(int level){
		int lebennerechnet = level * 3 + 30;
		
		return lebennerechnet;
	}
}
