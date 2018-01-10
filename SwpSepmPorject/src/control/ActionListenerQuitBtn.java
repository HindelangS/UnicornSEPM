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
public class ActionListenerQuitBtn implements ActionListener {

	Overworld ow;
	public ActionListenerQuitBtn(Overworld oworld) {
		ow = oworld;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Goodbye, thanks for playing this superduper game! ");
		Overworld.overworldobj.frame.dispose();
	}
}
