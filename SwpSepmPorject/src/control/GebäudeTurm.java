package control;

public class Geb�udeTurm extends Geb�ude {
	int Reichweite = calcReichweite(getLevel());

	public Geb�udeTurm(int level) {
		super(level);
		// TODO Auto-generated constructor stub
	}
	
	private int calcReichweite(int level){
		int rw = 3;
		if(level <=5){
			return rw; 
		}
		if(level <= 10){
			return rw+1;
		}
		if(level <= 15){
			return rw+2;
		}
		if(level <= 20){
			return rw+3;
		}
		return 7;
	}

}
