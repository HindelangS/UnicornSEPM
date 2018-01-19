package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import control.ActionListenerQuitBtn;
import control.Controller;
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

	/**
	 * Create the application.
	 */
	public Overworld(String username, int reihen, int spalten) { // user mitgeben? 
		user = username; 
		this.reihen = reihen; 
		this.spalten = spalten; 
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

		btnQuit = new JButton("quit game");
		btnQuit.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		btnQuit.addActionListener(new ActionListenerQuitBtn(ow));
		panelStatus.add(btnQuit, "cell 4 0,alignx right,aligny top");

		panel = new Field();
		panel.setBild("pictures/rainbow.png");
		panel.setLayout(new GridLayout(reihen, spalten));
		frame.getContentPane().add(panel, "cell 0 1,grow");

		ArrayList<ArrayList<Field>> liste = new ArrayList<ArrayList<Field>>(); //TODO is des nit sowas wie die owliste?

		Controller tester = new Controller();
	
		for (int i = 0; i < reihen; i++) {

			liste.add(new ArrayList<Field>());

			for (int j = 0; j < spalten; j++) {

				boolean check = false;

				for (String[] s : owliste) {
					
					try {
						if(Integer.parseInt(s[1]) == j && Integer.parseInt(s[2]) == i){
							System.out.println(s[0]+s[1]+s[2]);		
							if(s[0].equalsIgnoreCase(Login.getUser())) { //bitte auf useramen prüfen von login 
								
								
								Field buffer = new Field(i,j,"E",s[0]);
								buffer.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 150, 0)));
								liste.get(i).add(buffer);
								liste.get(i).get(j).setBild("pictures/village3.png");
								check = true;
								
							}else { 
								Controller test = new Controller();
								System.out.println(s[0]+" - Feld fremder User");
								
								Field buffer = new Field(i,j,"K",s[0]);
								buffer.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
								
								liste.get(i).add(buffer); // TODO: statt UnderworldK null bitte die underworld des gegner (anderer spieler einfügen)
								liste.get(i).get(j).setBild("pictures/village1.png");
								check = true;
							}


						}else { 
//							Field buffer = new Field(i,j);  //TODO statt underworld e garkein feld? 
//							buffer.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
//							liste.get(i).add(buffer);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}

				if(check == false) {
					
					Field buffer = new Field(i,j); //TODO statt underworld e garkein feld? 
					buffer.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
					liste.get(i).add(buffer);
					
				}
				panel.add(liste.get(i).get(j));

			}
		}


		panel.repaint();
		panel.validate();

	}

	public boolean isAngemeldet() {
		return angemeldet;
	}
}
