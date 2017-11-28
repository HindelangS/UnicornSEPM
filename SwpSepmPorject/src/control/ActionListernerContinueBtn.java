package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view1.Login;
import view1.Overworld;
import view1.Startingpage;

/**
 * ActionListener der ersten Seite (Button) ruft das Hauptmenue auf 
 */
public class ActionListernerContinueBtn implements ActionListener {

	Login log;
	public ActionListernerContinueBtn(Login l) {
		log = l;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {

		//		int fehlercode;
		//		if((fehlercode = Datenbank.spielerEinloggen(Namefield.getText(), String.valueOf(Passfield.getPassword()))) == 0) {
		// erfolreich
		Overworld frame = new Overworld(10, 15);
		frame.setVisible(true);
		log.dispose();

		//		}
		//		else {
		// Fehlernachricht anzeigen
		//			JOptionPane(setText(Datenbank.fehlercodeAufloesen(fehlercode)));
		//		}		


	}
}