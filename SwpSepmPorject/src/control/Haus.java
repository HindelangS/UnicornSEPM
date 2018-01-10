package control;

public abstract class Haus extends Gebäude {

	private int level;
	private int kosten;
	private int robustheit; 
	

	public Haus(int level, int kosten, int robustheit) {
		super(level, kosten, robustheit);
		// TODO Auto-generated constructor stub
	}

	public int berechneKosten(){
		int kosten = this.level * this.kosten + this.robustheit;
		return kosten;
	}
}
