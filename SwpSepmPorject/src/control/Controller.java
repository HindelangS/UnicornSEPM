package control;

import java.util.ArrayList;

public class Controller {

	UnderworldField[][] Underworld = new UnderworldField[10][10];

	public  ArrayList <UnderworldField> create(){
		ArrayList <UnderworldField> Underworld2 = new ArrayList<>();

		for(int i = 0;i <= 9;i++){
			for(int a = 0;a <= 9;a++){
				Underworld[i][a] = new UnderworldField(false, a, i);
				//	System.out.println(i+"/"+a);
				Underworld2.add(new UnderworldField(false, a, i));
			}
		}
		
		return Underworld2;

	}

	public int getField(ArrayList <UnderworldField> Underworld,int x,int y){
		UnderworldField feld = new UnderworldField(false,x,y);
		UnderworldField feld2 = new UnderworldField(true,x,y);

		for(int i = 0;i<=Underworld.size();i++){
			if(Underworld.get(i).equals(feld)){
				return i;
			}
			if(Underworld.get(i).equals(feld2)){
				return i;
			}
		}

		return -1;

	}


	
	public ArrayList <UnderworldField> attack(UnderworldField attackerField, UnderworldField zielField, ArrayList <UnderworldField> Underworld2){
		System.out.println("Check ob Angreifer und Gebäude vorhanden");
		Gebäude geb = zielField.getGebäude();
		Einheit einheit = attackerField.getEinheit();
		if(einheit == null || geb == null){
			System.out.println("ERROR, Gebäude oder Einheit nicht vorhanden");
		}
		else{
			System.out.println("Angegebene Felder in Ordnung");
			geb.setLeben(geb.getLeben() - einheit.getSchaden());
			Underworld2.get( getField(Underworld2, zielField.getX(), zielField.getY()) ).getGebäude().setLeben(geb.getLeben());
			if(geb.getLeben() <= 0){
				System.out.println("Gebäude zerstört");
				Underworld2.get( getField(Underworld2, zielField.getX(), zielField.getY()) ).setGebäude(null);
				Underworld2.get( getField(Underworld2, zielField.getX(), zielField.getY()) ).setBelegt(false);
			}
			
		}
		
		
		
		return Underworld2;
		
		
	}

	public ArrayList <UnderworldField> movefor1(UnderworldField startField, UnderworldField zielField,ArrayList <UnderworldField> Underworld2) {
		Einheit einheit = startField.getEinheit();
		if(Math.abs(startField.getX() - zielField.getX()) <= 1 && Math.abs(startField.getY() - zielField.getY()) <= 1 ){
			if(zielField.getGebäude() == null){
				Underworld2.get(getField(Underworld2, startField.getX(), startField.getY())).setEinheit(null);;
				Underworld2.get(getField(Underworld2, zielField.getX(), zielField.getY())).setEinheit(einheit);;
				System.out.println("um 1 bewegt");
				
			}
			else{
				System.out.println("Gebäude davor");
				System.out.println("Gebäude wird angegriffen");
				
			}
			
		}


		return Underworld2;

	}

	



	public ArrayList <UnderworldField> move(UnderworldField startField, UnderworldField zielField,ArrayList <UnderworldField> Underworld2){
		if(startField.getEinheit() != null && (startField.getX() != zielField.getX() || startField.getY() != zielField.getY())){
			Einheit einheit = startField.getEinheit();
			int reichweite = einheit.getMovement();

			Underworld2.get(getField(Underworld2, startField.getX(), startField.getY()));
			Underworld2.get(getField(Underworld2, zielField.getX(), zielField.getY()));


			if(Math.abs(startField.getX() - zielField.getX()) <= reichweite && Math.abs(startField.getY() - zielField.getY()) <= reichweite ){
				System.out.println("Ziel in Reichweite");
					if(startField.getX() != zielField.getX()){
						System.out.println("geht ost oder west");
						if(startField.getX() - zielField.getX() > 0){
							System.out.println("geht west");
							for(int i = startField.getX();i >= zielField.getX();i--){
								System.out.println(i);
								UnderworldField aktuell = new UnderworldField(true,i,startField.getY());
								int nextF = getField(Underworld2, i+1, startField.getY());
								UnderworldField nextField = Underworld2.get(nextF);
								Underworld2 = movefor1(aktuell, nextField, Underworld2);
								
							}

						}
						else{
							System.out.println("geht ost");

						}

					}
					else{
						System.out.println("geht nord oder süd");

						if(startField.getY() - zielField.getY() > 0){
							System.out.println("geht süd");

						}
						else{
							System.out.println("geht nord");

						}
					}


				

			}
			else{
				System.out.println("Ziel nicht in Reichweite"+reichweite);
			}

		}
		else{
			System.out.println("keine einheit oder felder gleich");
		}
		return Underworld2;

	}
	public static void main(String[] args) {
		System.out.println("Start");
		Controller c1 = new Controller();
		ArrayList <UnderworldField> Underworld2 = c1.create();
		UnderworldField startField = new UnderworldField(false,6,2);
		Schildkämpfer einheit = new Schildkämpfer(3);
		startField.setEinheit(einheit);
		startField.setBelegt(true);
		UnderworldField zielField = new UnderworldField(false,5,2);
		
		c1.move(startField, zielField, Underworld2);
	}

}
