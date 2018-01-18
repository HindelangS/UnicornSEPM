package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Login;
import view.Overworld;
import view.Register;

public class ActionListernerRegisterBtn implements ActionListener {
	Login log;
	public ActionListernerRegisterBtn(Login l) {
		log = l;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Register frame = new Register();
		frame.setVisible(true);
		log.dispose();
	}

}
