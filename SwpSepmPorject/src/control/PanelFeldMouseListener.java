package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view1.Field;
import view1.Underworld;

public class PanelFeldMouseListener extends MouseAdapter{

	Underworld underw; 
	Field pf;
	
	public PanelFeldMouseListener(Field field) {

		pf = field;
		underw = pf.getUWorld();
	}
	
	public void mouseClicked(MouseEvent arg0) {
		
	}

	


}
