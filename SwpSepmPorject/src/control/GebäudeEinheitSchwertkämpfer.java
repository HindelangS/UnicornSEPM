package control;

public class GebäudeEinheitSchwertkämpfer extends Gebäude {
	
	
	public GebäudeEinheitSchwertkämpfer(int level) {
		super(level);
		// TODO Auto-generated constructor stub
	}

	private Schwertkämpfer produziereEinheit(){
		Schwertkämpfer kämpfer = new Schwertkämpfer(this.getLevel());
		
		
		return kämpfer;
		
	}
	
}
