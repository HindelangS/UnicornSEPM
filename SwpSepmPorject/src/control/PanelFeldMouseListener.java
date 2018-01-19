package control;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import view.Field;
import view.Login;
import view.Overworld;
import view.UnderworldE;
import view.UnderworldK;

public class PanelFeldMouseListener extends MouseAdapter {

	UnderworldE underw;
	Field pf;

	public PanelFeldMouseListener(Field field) {

		pf = field;

	}

	public void mouseClicked(MouseEvent arg0) {

		System.out.println("geklickt: "+ pf.getKoordX() + " / " + pf.getKoordY());
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
		System.out.println("user: "+	Login.getUser() +"  "+ pf.getUser());
			
			if(pf.getUser().equalsIgnoreCase(Login.getUser())){
				//mein feld
				Overworld.overworldobj.frame.dispose();
				Overworld.overworld = false;
				
				String user = Overworld.owliste.get(0)[0];
				System.out.println("Username: " + user);
				
				try {
					UnderworldE window = new UnderworldE(8, 10);
					window.frame.setVisible(true);
					UnderworldE.underworlde = window;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else {
				//gegner
				if (Overworld.overworld == true) {
					
//					if(pf.getBild().equals("")) {
					if(pf.getUser().equals("")) {
//						System.out.println("leeres Feld");
//						JOptionPane.showMessageDialog(null, "Empty field, please choose a different one"); TODO
						return;
					}
					
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
			}
		}
	}

}
