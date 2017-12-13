package control;

public abstract class GebEnergie extends Gebäude {

	private int prodrate;
	
	public GebEnergie(int level,int prodrate,int kosten,int bonikosten) {
		super(level, kosten	,bonikosten);
		this.prodrate = prodrate;
		// TODO Auto-generated constructor stub
	}
	
	int getProduktion(int level){
		return level * prodrate;
	}

}
