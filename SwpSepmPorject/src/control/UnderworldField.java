package control;

public class UnderworldField {
	
	private final int x;
	private final int y;
	private boolean belegt;
	private Einheit einheit;
	private Gebäude gebäude;

	public void setGebäude(Gebäude gebäude) {
		this.gebäude = gebäude;
		this.belegt = true;
	}
	public int getY() {
		return y;
	}public int getX() {
		return x;
	}public Einheit getEinheit() {
		return einheit;
	}public Gebäude getGebäude() {
		return gebäude;
	}
	public void setEinheit(Einheit einheit) {
		this.einheit = einheit;
		this.belegt = true;
	}
	
	public UnderworldField(boolean belegt,int x,int y){
		this.belegt = belegt;
		this.x = x;
		this.y = y;
	}
	


	public boolean isBelegt() {
		return belegt;
	}
	public void setBelegt(boolean belegt) {
		this.belegt = belegt;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		UnderworldField u =(UnderworldField)obj;
		return belegt == u.isBelegt() && x == u.getX() && y == u.getY() ;
	}

}
