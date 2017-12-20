package control;

public abstract class Gebäude {
	private int leben;
	private int level;
	private int kosten;
	private int bonikosten;
	private int robustheit; 

	
	public Gebäude(int level,int kosten,int bonikosten, int robustheit) {
		this.setLevel(level);
		this.leben = berechneLeben(level);
		this.kosten = kosten;
		this.bonikosten = bonikosten;
		this.robustheit = robustheit; 
		// TODO Auto-generated constructor stub
	}
	

	public Gebäude(int level, int kosten, int robustheit) {
		this.setLevel(level);
		this.leben = berechneLeben(level);
		this.kosten = kosten;
		this.robustheit = robustheit; 
	}


	public int berechneKosten(){
		int kosten = this.level * this.kosten + this.bonikosten;
		return kosten;
	}

	private int berechneLeben(int level){
		int lebennerechnet = level * 3 + 30 * robustheit;
		
		return lebennerechnet;
	}
	
	public int getLeben() {
		return leben;
	}
	
	public void setLeben(int leben) {
		this.leben = leben;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
