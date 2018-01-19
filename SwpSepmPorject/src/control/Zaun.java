package control;

public abstract class Zaun extends Gebäude {
	
	private int leben;
	private int level;
	private int kosten;
	private int robustheit; 


	public Zaun(int level, int kosten, int robustheit) {
		super(level, kosten, robustheit);
		this.level = level;
		this.kosten = kosten;
		this.robustheit = robustheit;
	}

	public int berechneKosten(){
		int kosten = this.level * this.kosten + this.robustheit;
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
