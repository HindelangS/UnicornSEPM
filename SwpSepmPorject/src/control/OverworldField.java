package control;

import view.UnderworldE;
import view.UnderworldK;

public class OverworldField{

//	private final int x;
//	private final int y;
	private boolean belegt;
	
	private UnderworldE uwE; 
	private UnderworldK uwK; 
	
	public void setVillageM(Einheit einheit)  {
		
	
			System.out.println("Meine Welt wird links oben angezeigt");
	
	
	}
	
	public void setVillageF(Einheit einheit)  {
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
