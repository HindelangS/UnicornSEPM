package control;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.MatteBorder;

import view1.Field;
import view1.Overworld;
import view1.UnderworldE;
import view1.UnderworldK;

public class PanelFeldMouseListener extends MouseAdapter {

	UnderworldE underw;
	Field pf;

	public PanelFeldMouseListener(Field field) {

		pf = field;

	}

	public void mouseClicked(MouseEvent arg0) {

		System.out.println("geklickt");
		System.out.println(pf.getKoordX() + " / " + pf.getKoordY() + " " + pf.getBild());
		if (Overworld.overworld == false) {

			pf.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
			if (UnderworldE.lastclicked != null) {
				UnderworldE.lastclicked.setBorder(null);
			}

			UnderworldE.lastclicked = pf;

			Controller c3 = new Controller();
			int index = c3.getField(UnderworldE.UnderworldFieldstest, pf.getKoordX(), pf.getKoordY());
			System.out.println(UnderworldE.UnderworldFieldstest.get(index).getGebäude());
		} else {
			if(pf.getKoordX() == 0 && pf.getKoordY() == 0) {
				//mein feld
				Overworld.overworldobj.frame.dispose();
				Overworld.overworld = false;
				
				String user = Overworld.owliste.get(0)[0];
				System.out.println("Username:" + user);
				
				try {
					UnderworldE window = new UnderworldE(8, 10);
					window.frame.setVisible(true);
					UnderworldE.underworlde = window;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else {
				//Gegner
			}
		}
	}

}
