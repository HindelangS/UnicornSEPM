package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Login;
import view.Overworld;
import view.Startingpage;
import view.UnderworldE;
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
		
//		TODO: in DB alles übertragen
		System.out.println("Goodbye, thanks for playing this superduper game! ");
		Overworld.overworldobj.frame.dispose();
	}
}
