package control;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.MatteBorder;

import view.Field;
import view.Overworld;
import view.UnderworldK;

public class PanelFeldMouseListenerK extends MouseAdapter {

	UnderworldK under;
	Field pf;

	public PanelFeldMouseListenerK(Field field) {

		pf = field;
//		under = underw;

	}

	public void mouseClicked(MouseEvent arg0) {

		if (Overworld.overworld == true) {
			
			int x = pf.getKoordX();
			int y = pf.getKoordY();
			
			for(String[] s : Overworld.owliste) {
				if(Integer.parseInt(s[1]) == x && Integer.parseInt(s[2]) == y) {
					String user = s[0];
					System.out.println("Attacker : " + user + " (clicked on   X: " + x + "  Y: " + y +" )");
				}
			}
			
			Overworld.overworldobj.frame.dispose();
			Overworld.overworld = false;

			try {
				
				UnderworldK window = new UnderworldK(8, 10, pf.getKoordX(), pf.getKoordY());
				window.frame.setVisible(true);
				System.out.println("launch underworld k");
				UnderworldK.underworldobj = window;
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return;
		}

		System.out.println("geklickt");
		// System.out.println(pf.getKoordX()+" / "+pf.getKoordY()+" "+pf.getBild());
		Color farbe = new Color(120, 0, 0);
		pf.setBorder(new MatteBorder(5, 5, 5, 5, farbe));
		if (UnderworldK.lastclicked != null) {
			UnderworldK.lastclicked.setBorder(null);
		}
		System.out.println("klick1: " + UnderworldK.lastclicked);
		System.out.println("klick2: " + pf);

		if (UnderworldK.lastclicked != null && UnderworldK.lastclicked != pf) {
			System.out.println("ANGRIFF");

			Controller k = new Controller();
			int ziel = k.getField(UnderworldK.UnderworldFieldstest, pf.getKoordX(), pf.getKoordY());
			int start = k.getField(UnderworldK.UnderworldFieldstest, UnderworldK.lastclicked.getKoordX(),
					UnderworldK.lastclicked.getKoordY());
			UnderworldK.UnderworldFieldstest = k.move(UnderworldK.UnderworldFieldstest.get(start),
					UnderworldK.UnderworldFieldstest.get(ziel), UnderworldK.UnderworldFieldstest);

		}
		if (UnderworldK.lastclicked == pf) {
			Controller c3 = new Controller();
			int index = c3.getField(UnderworldK.UnderworldFieldstest, pf.getKoordX(), pf.getKoordY());
			System.out.println(UnderworldK.UnderworldFieldstest.get(index).getEinheit());
		}
		
		UnderworldK.lastclicked = pf;
		under.redraw();

	}

}
