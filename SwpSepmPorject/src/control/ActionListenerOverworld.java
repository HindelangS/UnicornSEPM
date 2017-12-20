package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view1.Login;
import view1.Overworld;
import view1.Startingpage;
import view1.UnderworldE;
/**
 * ActionListener der ersten Seite, ruft das Login - Fenster auf 
 */
public class ActionListenerOverworld implements ActionListener {

	private static int spalten = 7; 
	private static int reihen = 5;
	UnderworldE uwe;
	public ActionListenerOverworld(UnderworldE uwE) {
		uwe = uwE;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Overworld frame = new Overworld(spalten, reihen);
		frame.setVisible(true);
//		uwe.dispose();
	}
}
