package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view1.Login;
import view1.Overworld;
import view1.Register;

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
