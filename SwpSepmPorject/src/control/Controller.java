package control;

import java.util.ArrayList;

public class Controller {

	//2D array Underworld bseitzt alle Underworldfielder, in echt dann 8x10??? 
	UnderworldField[][] Underworld = new UnderworldField[10][10];

	//Erstellt die ganzen (funktionellen) Felder einzeln und setzt sie defaultm‰ﬂig auf leer 
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
		
		UnderworldField feldFrei = new UnderworldField(false,x,y);
		UnderworldField feldbesetzt = new UnderworldField(true,x,y);

		for(int i = 0;i<=Underworld.size();i++){
			if(Underworld.get(i).equals(feldFrei)){
				return i;
			}
			if(Underworld.get(i).equals(feldbesetzt)){
				return i;
			}
		}

		return -1;
	}


	
	public ArrayList <UnderworldField> attack(UnderworldField attackerField, UnderworldField zielField, ArrayList <UnderworldField> Underworld2){
		System.out.println("Check ob Angreifer und Geb‰ude vorhanden");
		Geb‰ude geb = zielField.getGeb‰ude();
		Einheit einheit = attackerField.getEinheit();
		if(einheit == null || geb == null){
			System.out.println("ERROR, Geb‰ude oder Einheit nicht vorhanden");
		}
		else{
			System.out.println("Angegebene Felder in Ordnung");
			geb.setLeben(geb.getLeben() - einheit.getSchaden());
			Underworld2.get( getField(Underworld2, zielField.getX(), zielField.getY()) ).getGeb‰ude().setLeben(geb.getLeben());
			if(geb.getLeben() <= 0){
				System.out.println("Geb‰ude zerstˆrt");
				Underworld2.get( getField(Underworld2, zielField.getX(), zielField.getY()) ).setGeb‰ude(null);
				Underworld2.get( getField(Underworld2, zielField.getX(), zielField.getY()) ).setBelegt(false);
			}
			
		}
		
		return Underworld2;
		
		
	}

	public ArrayList <UnderworldField> movefor1(UnderworldField startField, UnderworldField zielField,ArrayList <UnderworldField> Underworld2) {
		Einheit einheit = startField.getEinheit();
		if(Math.abs(startField.getX() - zielField.getX()) <= 1 && Math.abs(startField.getY() - zielField.getY()) <= 1 ){
			if(zielField.getGeb‰ude() == null){
				Underworld2.get(getField(Underworld2, startField.getX(), startField.getY())).setEinheit(null);;
				Underworld2.get(getField(Underworld2, zielField.getX(), zielField.getY())).setEinheit(einheit);;
				System.out.println("um 1 bewegt "+startField.getX()+"/"+startField.getY()+" --> "+zielField.getX()+"/"+zielField.getY());
				
			}
			else{
				System.out.println("Geb‰ude davor");
				System.out.println("Geb‰ude wird angegriffen");
				Underworld2 = attack(startField, zielField, Underworld2);
				
			}
			
		}


		return Underworld2;

	}

	



	public ArrayList <UnderworldField> move(UnderworldField startField, UnderworldField zielField,ArrayList <UnderworldField> Underworld2){
	System.out.println("called");
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
							for(int i = startField.getX();i > zielField.getX();i--){
								System.out.println(i);
								UnderworldField aktuell = new UnderworldField(true,i,startField.getY());
								int nextF = getField(Underworld2, i-1, startField.getY());
								UnderworldField nextField = Underworld2.get(nextF);
								Underworld2 = movefor1(aktuell, nextField, Underworld2);
								
							}

						}
						else{
							System.out.println("geht ost");
							for(int i = startField.getX();i < zielField.getX();i++){
								System.out.println(i);
								UnderworldField aktuell = new UnderworldField(true,i,startField.getY());
								int nextF = getField(Underworld2, i+1, startField.getY());
								UnderworldField nextField = Underworld2.get(nextF);
								Underworld2 = movefor1(aktuell, nextField, Underworld2);
								
							}

						}

					}
					else{
						System.out.println("geht nord oder s¸d");

						if(startField.getY() - zielField.getY() > 0){
							System.out.println("geht s¸d");
							
							for(int i = startField.getY();i > zielField.getY();i--){
								System.out.println(i);
								UnderworldField aktuell = new UnderworldField(true,startField.getX(),i);
								int nextF = getField(Underworld2, startField.getX(), i-1);
								UnderworldField nextField = Underworld2.get(nextF);
								Underworld2 = movefor1(aktuell, nextField, Underworld2);
								
							}
							

						}
						else{
							System.out.println("geht nord");
							for(int i = startField.getY();i < zielField.getY();i++){
								System.out.println(i);
								UnderworldField aktuell = new UnderworldField(true,startField.getX(),i);
								int nextF = getField(Underworld2, startField.getX(), i+1);
								UnderworldField nextField = Underworld2.get(nextF);
								Underworld2 = movefor1(aktuell, nextField, Underworld2);
								
							}

						}
					}


				

			}
			else{
				System.out.println("Ziel nicht in Reichweite"+reichweite);
			}

		}
		else{
			System.out.println("keine Einheit oder Felder gleich");
		}
		return Underworld2;

	}
	public static void main(String[] args) {
		
//		System.out.println("Starten");
//		Controller c1 = new Controller();
//		ArrayList <UnderworldField> Underworld2 = c1.create();
//		UnderworldField startField = new UnderworldField(false,5,4);
//		Schildk‰mpfer einheit = new Schildk‰mpfer(3);
//		startField.setEinheit(einheit);
//		startField.setBelegt(true);
//		UnderworldField zielField = new UnderworldField(false,5,2);
//		
//		c1.move(startField, zielField, Underworld2);
		
		for(int i = 1;i<= 8;i++){
			System.out.println("Level: "+i);
			GebEnergie1 geb1 = new GebEnergie1(i);
			GebEnergie2 geb2 = new GebEnergie2(i);
			GebEnergie3 geb3 = new GebEnergie3(i);
			int kosten1 = geb1.berechneKosten();
			int kosten2 = geb2.berechneKosten(); 
			int kosten3 = geb3.berechneKosten();
			int leben = geb1.getLeben();
			int prodrate1 = geb1.getProduktion();
			int prodrate2 = geb2.getProduktion();
			int prodrate3 = geb3.getProduktion();
			
			System.out.println("Geb1: Kosten: "+kosten1+", Produktion: "+prodrate1+", Leben: "+leben);
			System.out.println("Geb2: Kosten: "+kosten2+", Produktion: "+prodrate2+", Leben: "+leben);
			System.out.println("Geb3: Kosten: "+kosten3+", Produktion: "+prodrate3+", Leben: "+leben);
			System.out.println("------------------------------------------------------");
			
			System.out.println("---------------------ZAUN-------------------------");
			
			ZaunEnergie1 z1 = new ZaunEnergie1(i);
			ZaunEnergie2 z2 = new ZaunEnergie2(i);
			ZaunEnergie3 z3 = new ZaunEnergie3(i);
			int zaunl1 = z1.getLeben();
			int zaunl2 = z2.berechneKosten(); 
			int zaunl3= z3.berechneKosten();
			int lebenZ = z1.getLeben();
			int robustheit1 = z1.getRobustheit();
			int robustheit2 = z2.getRobustheit();
			int robustheit3 = z3.getRobustheit();
			
			System.out.println("Zaun1: Kosten: "+zaunl1+", Robustheit: "+robustheit1+", Leben: "+lebenZ);
			System.out.println("Zaun2: Kosten: "+zaunl2+", Robustheit: "+robustheit2+", Leben: "+lebenZ);
			System.out.println("Zaun3: Kosten: "+zaunl3+", Robustheit: "+robustheit3+", Leben: "+lebenZ);
			System.out.println("------------------------------------------------------");
		}
		
		
		
	}

}
