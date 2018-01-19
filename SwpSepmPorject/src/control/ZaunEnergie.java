package control;

public abstract class ZaunEnergie extends Zaun {

	private int robustheit;
	
	public ZaunEnergie(int level,int kosten, int robustheit) {
		super(level, kosten	, robustheit);
		this.robustheit = robustheit;
	}
	
	int getRobustheit(){
		return this.getLevel() * robustheit;
	}
}
