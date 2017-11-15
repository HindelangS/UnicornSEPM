package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view1.Login;
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
		Startingpage frame = new Startingpage();
		frame.setVisible(true);
		log.dispose();
	}
}