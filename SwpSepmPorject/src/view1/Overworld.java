package view1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ActionListenerQuitBtn;
import control.Controller;
import control.Geb�ude;
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

	// public static ArrayList <OverworldField> OverworldFieldtest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Overworld window = new Overworld("sara", reihen, spalten);
					window.frame.setVisible(true);
					overworldobj = window;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Overworld(String username, int reihen, int spalten) { // user mitgeben? 
		setAnzReihen(reihen);
		setAnzSpalten(spalten);
		owliste = DatenbankUnicorn.OverworldinDB();
		uwliste = DatenbankUnicorn.UnderworldinDB();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {


		String usern = "sara";  //TODO change it nowwwwwwwwwwwww + uebr DB spieler

		int count = 0;
		for (String[] s : owliste) {
			if(s[0].equalsIgnoreCase(usern)) {
				String[] ss = owliste.get(0);
				String buf = s[0];
				s[0] = ss[0]; //namen tauschen (user auf 0/0)
				ss[0] = buf;

				owliste.set(0, ss);
				owliste.set(count, s);
				break;
			}
			count ++;
		}

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

		lblWelt = new JLabel("You are logged in as: "+ usern+" \t");
		lblWelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblWelt, "cell 0 0,alignx left,aligny center");

		lblLevel = new JLabel("\t Level: ");
		lblLevel.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblLevel, "cell 1 0,alignx left,aligny center");

		lblGeld = new JLabel("\t Money: ");
		lblGeld.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblGeld, "cell 2 0,alignx left,aligny center");

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
		// UnderworldFieldstest = tester.create();

		for (int i = 0; i < reihen; i++) {

			liste.add(new ArrayList<Field>());

			for (int j = 0; j < spalten; j++) {
				//
				// int index = tester.getField(UnderworldFieldstest, i, j);
				//
				// Geb�ude geb = UnderworldFieldstest.get(index).getGeb�ude();
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
					//System.out.println(s[1] + "   "  + s[2]);
					try {
						if(Integer.parseInt(s[1]) == j && Integer.parseInt(s[2]) == i){
							System.out.println("add");
							if(s[0].equalsIgnoreCase(usern)) { //bitte auf usernamen pr�fen von login 

								liste.get(i).add(new Field(i, j, "E", (UnderworldE) null)); // bitte statt null undrworldE der angemeldeten spielers ein�gen 
								liste.get(i).get(j).setBild("pictures/village3.png");
							
								check = true;
							}else {
								liste.get(i).add(new Field(i, j, "K", (UnderworldK) null)); //statt UnderworldK null bitte die underworld des gegner (anderer spieler einf�gen)
								liste.get(i).get(j).setBild("pictures/village2.png");
								check = true;
							}


						}else { 
							//liste.get(i).add(new Field(i, j, "E",(UnderworldK)  null));
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}

				if(check == false) {
					liste.get(i).add(new Field(i, j, "E",(UnderworldK)  null));
				}
				panel.add(liste.get(i).get(j));
				panel.repaint();
				panel.validate();
			}
		}


		panel.repaint();
		anmelden(false);

	}

	public boolean isAngemeldet() {
		return angemeldet;
	}

	public void anmelden(boolean anmelden) {

		// TODO hier label setzten und

		//		lblWelt.setText("Achtung du bist "+ username);

	}

	private void setAnzReihen(int anz) {
		reihen = anz;
	}

	private void setAnzSpalten(int anz) {
		spalten = anz;
	}

}
