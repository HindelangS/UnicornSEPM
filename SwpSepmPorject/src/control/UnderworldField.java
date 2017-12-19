package control;

import javax.swing.JOptionPane;

public class UnderworldField{

	private final int x;
	private final int y;
	private boolean belegt;
	private Einheit einheit;
	private Gebäude gebäude;
	private Haus haus; 
	private Zaun zz; 

	public void setGebäude(Gebäude gebäude){
		if(this.belegt == false){
			this.gebäude = gebäude;
			this.belegt = true;

			if(this.gebäude == null){
				this.belegt = false;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Fehler, Feld bereits besetzt");
			System.out.println("Fehler, Feld bereits besetzt");
		}
	}
	
	public void setHaus(Haus haus){
		if(this.belegt == false){
			this.haus = haus;
			this.belegt = true;

			if(this.haus == null){
				this.belegt = false;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Fehler, Feld bereits besetzt");
			System.out.println("Fehler, Feld bereits besetzt");
		}

	}

	public void deleteField(){
		
		if(this.belegt == true){
			this.gebäude = null;
			this.haus = null; 
			this.zz = null; 
			this.belegt = false;

			if(this.gebäude == null || this.haus == null || this.zz == null){
				this.belegt = false;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Fehler, Feld bereits leer");
			System.out.println("Fehler, Feld bereits leer");
		}
	}


	public void setZaun(Zaun z){
		if(this.belegt == false){
			this.zz = z;
			this.belegt = true;

			if(this.zz == null){
				this.belegt = false;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Fehler, Feld bereits besetzt");
			System.out.println("Fehler, Feld bereits besetzt");
		}

	}
	
	public void setEinheit(Einheit einheit)  {
		if(this.belegt == false){
			this.einheit = einheit;
			this.belegt = true;

			if(this.einheit == null){
				this.belegt = false;
			}
		}
		else{
			System.out.println("ERROR FELD BELEGT");
		}

	}

	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
	public Einheit getEinheit() {
		return einheit;
	}
	public Gebäude getGebäude() {
		return gebäude;
	}
	public Zaun getZaun() {
		return zz;
	}
	public Haus getHaus() {
		return haus;
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

	//Was genau macht des? 
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		UnderworldField u =(UnderworldField)obj;
		return belegt == u.isBelegt() && x == u.getX() && y == u.getY() ;
	}

}
