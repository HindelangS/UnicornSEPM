package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Login;
import view.Startingpage;
/**
 * ActionListener der ersten Seite, ruft das Login - Fenster auf 
 */
public class ActionListenerStartingpageBtn implements ActionListener {

	Startingpage ss;
	public ActionListenerStartingpageBtn(Startingpage s) {
		ss = s;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Login frame = new Login();
		frame.setVisible(true);
		ss.dispose();
	}
}
