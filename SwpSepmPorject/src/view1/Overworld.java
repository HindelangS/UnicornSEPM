package view1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.Controller;
import control.Gebäude;
import control.Haus;
import control.UnderworldField;
import control.Zaun;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Overworld extends JFrame{

	private JFrame frame;
	private JPanel panelStatus;
	private JButton btnWeiterZurberwelt;
	private JLabel lblWelt;
	private JLabel lblLevel;
	private JLabel lblGeld;

	private boolean angemeldet;

	Field panel;
	Field[][] felder;
	Overworld ow; 

	private static int spalten = 7; 
	private static int reihen = 5;

//	public static ArrayList <OverworldField> OverworldFieldtest;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Overworld window = new Overworld(reihen, spalten);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Overworld(int reihen, int spalten) {
		setAnzReihen(reihen);
		setAnzSpalten(spalten);
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		ImageIcon img = new ImageIcon(Overworld.class.getResource("/pictures/rosa.jpg"));
		Image im = img.getImage().getScaledInstance(1920, 1080, Image.SCALE_FAST);
		img = new ImageIcon(im);
		frame.setResizable(false);
		frame.setContentPane(new JLabel(img));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Overworld.class.getResource("/pictures/unicorn.PNG")));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[434px,grow]", "[37px][grow]"));

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 3);
		frame.setLocation(x, y);

		panelStatus = new JPanel();
		panelStatus.setOpaque(false);
		frame.getContentPane().add(panelStatus, "cell 0 0,growx,aligny top");
		panelStatus.setLayout(new MigLayout("", "[125px][60.00px][75][28px,grow][127px]", "[23px]"));

		lblWelt = new JLabel("SpielerXs Welt: ");
		lblWelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblWelt, "cell 0 0,alignx left,aligny center");

		lblLevel = new JLabel("Level: ");
		lblLevel.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblLevel, "cell 1 0,alignx left,aligny center");

		lblGeld = new JLabel("Geld: ");
		lblGeld.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblGeld, "cell 2 0,alignx left,aligny center");

		btnWeiterZurberwelt = new JButton("go to choosen world");
		btnWeiterZurberwelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(btnWeiterZurberwelt, "cell 4 0,alignx right,aligny top");

		panel = new Field();
		panel.setBild("pictures/rainbow.png");
		panel.setLayout(new GridLayout(reihen, spalten));
		frame.getContentPane().add(panel, "cell 0 1,grow");	

		ArrayList<ArrayList<Field>> liste = new ArrayList<ArrayList<Field>>();

		Controller tester = new Controller();
//		UnderworldFieldstest =  tester.create();

		for(int i = 0; i < reihen; i++) {

			liste.add(new ArrayList<Field>());
			for(int j = 0; j < spalten; j++) {

//				int index = tester.getField(UnderworldFieldstest, i, j);
//
//				Gebäude geb = UnderworldFieldstest.get(index).getGebäude();
//				Haus haus = UnderworldFieldstest.get(index).getHaus();
//				Zaun zz = UnderworldFieldstest.get(index).getZaun();
//
//				System.out.println("Derzeitig bestetzt durch: "+geb+","+ haus+" I: "+i+" J:"+j);
//
//				if(geb != null){
//					System.out.println("Gebaude vorhanden");
//					liste.get(i).add(drawFeld(geb, i, j));
//					panel.add(liste.get(i).get(j));
//				}
//				if(haus != null){
//					System.out.println("Haus vorhanden");
//					liste.get(i).add(drawFeld(haus, i, j));
//					panel.add(liste.get(i).get(j));
//				}
//				if(zz != null){
//					System.out.println("Zaun vorhanden");
//					liste.get(i).add(drawFeld(haus, i, j));
//					panel.add(liste.get(i).get(j));
//				}
//				else{
//					System.out.println("nix auf dem Feld");
					liste.get(i).add(new Field(i,j,"E", null));
					panel.add(liste.get(i).get(j));
//				}

				System.out.println("--------------------------");
				System.out.println("X X X X X X "+liste.size()+ " X X X X X X ");
				panel.repaint();
			}
		}



		anmelden(false);

	}

	public boolean isAngemeldet() {
		return angemeldet;
	}

	public void anmelden(boolean anmelden){ 

		//TODO hier label setzten und 

		lblWelt.setText("Achtung du bist "	);

	}

	private void setAnzReihen(int anz) {
		reihen = anz;
	}

	private void setAnzSpalten(int anz) {
		spalten = anz;
	}



}
