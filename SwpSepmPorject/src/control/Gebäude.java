package control;

public class Geb�ude {
	int leben;
	int level;
	
	public Geb�ude(int level) {
		this.level = level;
		this.leben = berechneLeben(level);
		// TODO Auto-generated constructor stub
	}
	
	private int berechneLeben(int level){
		int lebennerechnet = level * 3 + 30;
		
		return lebennerechnet;
	}
}
