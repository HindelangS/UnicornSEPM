package control;

import view1.UnderworldE;
import view1.UnderworldK;

public class OverworldField{

//	private final int x;
//	private final int y;
	private boolean belegt;
	
	private UnderworldE uwE; 
	private UnderworldK uwK; 
	
	public void setEinheit(Einheit einheit)  {
		if(this.belegt == false){

//			this.einheit = einheit;
//			this.belegt = true;
//
//			if(this.einheit == null){
//				this.belegt = false;
//			}
		}
		else{
			System.out.println(this);
			System.out.println("ERROR FELD BELEGT");
		}
	}

}
