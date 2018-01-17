package view1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import control.ActionListenerQuitBtn;
import control.Controller;
import control.Gebäude;
import control.Haus;
import control.HausEinheiten1;
import control.UnderworldField;
import control.Zaun;
import model.DatenbankUnicorn;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

//Sie bekommen noch eine Schnitzelsemmel von der Sara.
public class Overworld extends JFrame {

	public JFrame frame;
	private JPanel panelStatus;
	private JButton btnQuit;
	private JLabel lblWelt;
	private JLabel lblLevel;
	private JLabel lblGeld;

	private boolean angemeldet;

	public static boolean overworld = true;
	public static Overworld overworldobj = null;


	public static ArrayList<String[]> owliste;
	public static ArrayList<String[]> uwliste;

	Field panel;
	Field[][] felder;
	Overworld ow;

	private static int spalten = 7;
	private static int reihen = 5;
	private static String user= "";

	// public static ArrayList <OverworldField> OverworldFieldtest;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Overworld window = new Overworld(user, reihen, spalten);
//					window.frame.setVisible(true);
//					overworldobj = window;
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Overworld(String username, int reihen, int spalten) { // user mitgeben? 
		username = user; 
		reihen = this.reihen; 
		spalten = this.spalten; 
		owliste = DatenbankUnicorn.OverworldausDB();
		uwliste = DatenbankUnicorn.UnderworldausDB();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
//		int count = 0;
//		for (String[] s : owliste) {
//			if(s[0].equalsIgnoreCase(user)) {
//				String[] ss = owliste.get(0);
//				String buf = s[0];
//				s[0] = ss[0]; //namen tauschen (user auf 0/0)
//				ss[0] = buf;
//
//				owliste.set(0, ss);
//				owliste.set(count, s);
//				break;
//			}
//			count ++;
//			System.out.println("Count in Overworld: "+ count);
//		}
		
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

		lblWelt = new JLabel("You are logged in as: "+ Login.getUser() +" \t");
		lblWelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblWelt, "cell 0 0,alignx left,aligny center");

//		lblLevel = new JLabel("\t Level: "+ Login.getLevel()+" \t" );
//		lblLevel.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
//		panelStatus.add(lblLevel, "cell 1 0,alignx left,aligny center");
//
//		lblGeld = new JLabel("\t Money: "+ Login.getMoney()+" \t" );
//		lblGeld.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
//		panelStatus.add(lblGeld, "cell 2 0,alignx left,aligny center");

		btnQuit = new JButton("quit game");
		btnQuit.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		btnQuit.addActionListener(new ActionListenerQuitBtn(ow));
		panelStatus.add(btnQuit, "cell 4 0,alignx right,aligny top");

		panel = new Field();
		panel.setBild("pictures/rainbow.png");
		panel.setLayout(new GridLayout(reihen, spalten));
		frame.getContentPane().add(panel, "cell 0 1,grow");

		ArrayList<ArrayList<Field>> liste = new ArrayList<ArrayList<Field>>();

		Controller tester = new Controller();
	
		for (int i = 0; i < reihen; i++) {

			liste.add(new ArrayList<Field>());

			for (int j = 0; j < spalten; j++) {

				// int index = tester.getField(UnderworldFieldstest, i, j);

				// Gebäude geb = UnderworldFieldstest.get(index).getGebäude();
				// Haus haus = UnderworldFieldstest.get(index).getHaus();
				// Zaun zz = UnderworldFieldstest.get(index).getZaun();
				//
				// System.out.println("Feld +" I: "+i+" J:"+j" derzeitig bestetzt durch:
				// "+geb+","+ haus");
				//
				// if(geb != null){
				// System.out.println("Gebaude vorhanden");
				// liste.get(i).add(drawFeld(geb, i, j));
				// panel.add(liste.get(i).get(j));
				// }
				// if(haus != null){
				// System.out.println("Haus vorhanden");
				// liste.get(i).add(drawFeld(haus, i, j));
				// panel.add(liste.get(i).get(j));
				// }
				// if(zz != null){
				// System.out.println("Zaun vorhanden");
				// liste.get(i).add(drawFeld(haus, i, j));
				
				// panel.add(liste.get(i).get(j));
				// }
				// else{
				// System.out.println("nix auf dem Feld");

				
				//Feld ist besetzt
				boolean check = false;

				for (String[] s : owliste) {
					
					try {
						if(Integer.parseInt(s[1]) == j && Integer.parseInt(s[2]) == i){
							
						
							
							if(s[0].equalsIgnoreCase(Login.getUser())) { //bitte auf useramen prüfen von login 
								
								
								Field buffer = new Field(i,j,"E",Login.getUser());
								buffer.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));

								liste.get(i).add(buffer); // TODO bitte statt null undrworldE der angemeldeten spielers einfuegen 
								liste.get(i).get(j).setBild("pictures/village3.png");
								check = true;
								
							}else {
								Controller test = new Controller();
								System.out.println(s[0]+"sdfdsfd");
								
								Field buffer = new Field(i,j,"K",s[0]);
								buffer.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
								
								liste.get(i).add(buffer); // TODO: statt UnderworldK null bitte die underworld des gegner (anderer spieler einfügen)
								liste.get(i).get(j).setBild("pictures/village2.png");
								check = true;
							}


						}else { 
							Field buffer = new Field(i,j,"E", (UnderworldE)null);
							buffer.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
							liste.get(i).add(buffer);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}

				if(check == false) {
					Field buffer = new Field(i,j,"E",( UnderworldE) null);
					buffer.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));

					liste.get(i).add(buffer);
				}
				panel.add(liste.get(i).get(j));
				panel.repaint();
				panel.validate();
			}
		}


		panel.repaint();
//		anmelden(false);

	}

	public boolean isAngemeldet() {
		return angemeldet;
	}
}
