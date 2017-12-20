package control;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.MatteBorder;

import view1.Field;
import view1.UnderworldE;

public class PanelFeldMouseListener extends MouseAdapter{

	UnderworldE underw; 
	Field pf;

	public PanelFeldMouseListener(Field field) {

		pf = field;

	}

	public void mouseClicked(MouseEvent arg0) {

		System.out.println("geklickt");
		System.out.println(pf.getKoordX()+" / "+pf.getKoordY()+" "+pf.getBild());
		pf.setBorder(new MatteBorder(1, 1, 1,1,  new Color(0,0,0)));
		if(UnderworldE.lastclicked != null){
			UnderworldE.lastclicked.setBorder(null);
		}
		
		UnderworldE.lastclicked = pf;
		
		Controller c3 = new Controller();
		int index = c3.getField(UnderworldE.UnderworldFieldstest, pf.getKoordX(), pf.getKoordY());
		System.out.println(UnderworldE.UnderworldFieldstest.get(index).getGebäude());

	}




}
