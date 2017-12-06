package view1;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;

/**
 * Damit kann zwischen UI-Components mittels der TAB-Taste gewechselt werden
 * Tab-Reihenfolge wird angegeben durch Liste mit den Components (zwischen denen gewechselt werden soll)
 * die in der Liste geordnet sind (0. Element = 1. Element zu dem der Focus gelegt wird)
 * 
 * Implementierung in Container:
 * >>container<<.setFocusTraversalPolicy(new FocusReihenfolge(>>ComponentListe<<));
 * 
 * @version 1.0
 */
public class FocusReihenfolge extends FocusTraversalPolicy {

	/**
	 * Liste mit den Components, zwischen denen gewechselt werden soll
	 * Components muessen in richtiger Reihenfolge in der ArrayList stehen
	 */
	private ArrayList<Component> compOrder;
	
	/**
	 * Eine neue Policy fuer den Focus Traversal
	 * Damit kann zwischen UI-Components mittels der TAB-Taste gewechselt werden
	 * @param compList Liste mit den Components, zwischen denen gewechselt wird (muss geordnet sein)
	 */
	public FocusReihenfolge(ArrayList<Component> compList) {
		compOrder = compList;
	}
	
	@Override
	public Component getComponentAfter(Container arg0, Component comp) {
		
		int indx = compOrder.indexOf(comp)+1;
		if(indx >= compOrder.size()) indx = 0;
		return compOrder.get(indx);
	}

	@Override
	public Component getComponentBefore(Container arg0, Component comp) {
		
		int indx = compOrder.indexOf(comp)-1;
		if(indx < 0) indx = compOrder.size()-1;
		return compOrder.get(indx);
	}

	@Override
	public Component getDefaultComponent(Container arg0) {
		
		return compOrder.get(0);
	}

	@Override
	public Component getFirstComponent(Container arg0) {
		
		return compOrder.get(0);
	}

	@Override
	public Component getLastComponent(Container arg0) {
		
		return compOrder.get(compOrder.size()-1);
	}

}
