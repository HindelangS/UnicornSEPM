package control;

import java.util.ArrayList;

public class Controller {
	
	UnderworldField[][] Underworld = new UnderworldField[10][10];
	ArrayList <UnderworldField> Underworld2 = new ArrayList<>();
	
	public void create(){
		
		for(int i = 0;i <= 9;i++){
			for(int a = 0;a <= 9;a++){
				Underworld[i][a] = new UnderworldField(false, a, i);
			//	System.out.println(i+"/"+a);
				Underworld2.add(new UnderworldField(false, a, i));
			}
		}
		
	}
	
	public void move(){
		boolean test = Underworld2.contains(new UnderworldField(false,1,1));
	System.out.println(	Underworld2.get(11).getX() +"/"+ Underworld2.get(11).getY());

		System.out.println(test);
	}
	public static void main(String[] args) {
		Controller c1 = new Controller();
		c1.create();
		c1.move();
	}

}
