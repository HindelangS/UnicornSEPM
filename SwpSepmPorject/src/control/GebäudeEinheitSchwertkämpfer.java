package control;

public class Geb�udeEinheitSchwertk�mpfer extends Geb�ude {
	
	
	public Geb�udeEinheitSchwertk�mpfer(int level) {
		super(level);
		// TODO Auto-generated constructor stub
	}

	private Schwertk�mpfer produziereEinheit(){
		Schwertk�mpfer k�mpfer = new Schwertk�mpfer(this.getLevel());
		
		
		return k�mpfer;
		
	}
	
}
