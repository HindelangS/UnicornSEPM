package control;

public class Geb�ude {
	private int leben;
	private int level;
	
	public Geb�ude(int level) {
		this.setLevel(level);
		this.leben = berechneLeben(level);
		// TODO Auto-generated constructor stub
	}
	
	private int berechneLeben(int level){
		int lebennerechnet = level * 3 + 30;
		
		return lebennerechnet;
	}
	public int getLeben() {
		return leben;
	}public void setLeben(int leben) {
		this.leben = leben;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
