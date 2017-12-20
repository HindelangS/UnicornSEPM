package control;

public class HausEinheiten extends Haus {

	private int robustheit;
	
	public HausEinheiten(int level,int kosten, int robustheit) {
		super(level, kosten	, robustheit);
		this.robustheit = robustheit;
		// TODO Auto-generated constructor stub
	}
	
	int getRobustheit(){
		return this.getLevel() * robustheit;
	}
}
