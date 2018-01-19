package control;

public abstract class Zaun extends Gebäude {

	private int level;
	private int kosten;
	private int robustheit; 

	public Zaun(int level, int kosten, int robustheit) {
		super(level, kosten, robustheit);
		this.level = level;
		this.kosten = kosten;
		this.robustheit = robustheit;
		// TODO Auto-generated constructor stub
	}
	
	public int berechneKosten(){
		int kosten = this.level * this.kosten + this.robustheit;
		return kosten;
	}
}
