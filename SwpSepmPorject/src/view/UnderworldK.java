package view;

/***
 * K Wie KÄMPFEN 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import control.*;
import model.DatenbankUnicorn;

public class UnderworldK {

	public JFrame frame;
	private JPanel panelStatus;
	private JButton btnWeiterZurberwelt;
	private JLabel lblWelt;
	private JLabel lblLevel;
	private JLabel lblGeld;
	// private JPanel panelFelder;
	private JPanel panelEast;
	private JButton btnKaempfer1;
	private JButton btnKaempfer2;
	private JButton btnKaempfer3;
	private JCheckBox cbBearbeiten;
	private JPanel phPanel;
	private JButton btnDelete;

	String[] daten=DatenbankUnicorn.SpielerSachen(username);

	// ArrayList vom Typ UnderworldField um Funktion zu Feld hinzuzufügen mit getX
	public static ArrayList<UnderworldField> UnderworldFieldstest;
	public static ArrayList<String[]> FelderUW = new ArrayList<String[]>();

	public static int x;

	Field panelFelder;
	Field[][] felder;
	public static Field lastclicked;

	public static UnderworldK underworldobj;

	private static int spalten = 10;
	private static int reihen = 8;
	private static String username = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnderworldK window = new UnderworldK(reihen, spalten, 0, 1);
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
	public UnderworldK(int reihen, int spalten, int callonX, int callonY) {
		setAnzReihen(reihen);
		setAnzSpalten(spalten);
		initialize(callonX, callonY);
		drawUnderworld();
	}

	private void drawUnderworld() {

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int callonX, int callonY) {

		for (String[] s : Overworld.owliste) {
			if (s[1].equalsIgnoreCase(callonX + "") && s[2].equalsIgnoreCase(callonY + "")) {
				username = s[0];
			}
		}

		frame = new JFrame();
		ImageIcon img = new ImageIcon(UnderworldK.class.getResource("/pictures/rosa.jpg"));
		Image im = img.getImage().getScaledInstance(1920, 1080, Image.SCALE_FAST);
		img = new ImageIcon(im);
		frame.setContentPane(new JLabel(img));
		frame.setTitle("Cute Unicorn Fight to Death");
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(UnderworldK.class.getResource("/pictures/unicorn.PNG")));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new MigLayout("", "[434px,grow]", "[37px][grow]"));

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 3);
		frame.setLocation(x, y);

		panelStatus = new JPanel();
		panelStatus.setOpaque(false);
		frame.getContentPane().add(panelStatus, BorderLayout.NORTH);
		panelStatus.setLayout(new MigLayout("", "[225][125][125][grow][][20]", "[25px,grow]"));

		lblWelt = new JLabel("World from: " + username); // nicht eigener name, besitzer welt
		lblWelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblWelt, "cell 0 0,alignx left,aligny top");

		lblLevel = new JLabel("Level: "+ daten[2]);
		lblLevel.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblLevel, "cell 1 0,alignx left,aligny top");

		lblGeld = new JLabel("Money: "+ daten[0]);
		lblGeld.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblGeld, "cell 2 0,alignx left,aligny top");

		phPanel = new JPanel();
		phPanel.setOpaque(false);
		panelStatus.add(phPanel, "cell 3 0,growx");

		cbBearbeiten = new JCheckBox("figth");
		cbBearbeiten.setToolTipText("show opitons to start the battle");
		panelStatus.add(cbBearbeiten, "cell 5 0,alignx right,aligny center");
		cbBearbeiten.setSelected(false);
		cbBearbeiten.setOpaque(false);
		cbBearbeiten.addActionListener(new CbBearbeitenActionListener());

		btnWeiterZurberwelt = new JButton("change to overworld");
		btnWeiterZurberwelt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UnderworldK.underworldobj.frame.dispose();
				Overworld.overworld = true;
				try {
					Overworld window = new Overworld(username, 5, 7);
					window.frame.setVisible(true);
					Overworld.overworldobj = window;
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnWeiterZurberwelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(btnWeiterZurberwelt, "cell 4 0,alignx center,aligny center");

		panelFelder = new Field();
		panelFelder.setLayout(new GridLayout(reihen, spalten));
		panelFelder.setBild("pictures/cloud.png");
		frame.getContentPane().add(panelFelder, "cell 0 1,grow");

		ArrayList<ArrayList<Field>> liste = new ArrayList<ArrayList<Field>>();

		Controller tester = new Controller();
		UnderworldFieldstest = tester.create();

		String username = "";
		for (String[] s : Overworld.owliste) {
			if (s[1].equalsIgnoreCase(callonX + "") && s[2].equalsIgnoreCase(callonY + "")) {
				username = s[0];
			}
		}

		ArrayList<String[]> underworldliste = new ArrayList<String[]>();

		for (String[] s : Overworld.uwliste) {
			if (s[4].equalsIgnoreCase(username)) {
				underworldliste.add(s);
			}
		}

		for (String[] s : underworldliste) {
			System.out.println(
					"User: " + s[4] + "  X: " + s[0] + "  Y: " + s[1] + "   uwid: " + s[3] + "   geid: " + s[2]);
		}

		for (int i = 0; i < reihen; i++) {

			liste.add(new ArrayList<Field>());
			for (int j = 0; j < spalten; j++) {

				int index = tester.getField(UnderworldFieldstest, i, j);

				// draw your stuff according to the Overworld.uwliste <- liste is created at TODO
				// DatenbankUnicorn

				Einheit einheit = null;
				einheit = UnderworldFieldstest.get(index).getEinheit();
				Gebäude geb = UnderworldFieldstest.get(index).getGebäude();
				Zaun zz = UnderworldFieldstest.get(index).getZaun();
				Haus haus = UnderworldFieldstest.get(index).getHaus();

				//	System.out.println(
				//		"Derzeitige Objekte auf Feld: " + geb + "," + zaun + "," + haus + " I: " + i + " J:" + j);


				for (String[] s : underworldliste) {
					if(Integer.parseInt(s[0]) == j && Integer.parseInt(s[1]) == i) {
						switch(Integer.parseInt(s[2])) {
						case 1:
							haus = new HausEinheiten1(1);
							break;
						case 2:
							haus = new HausEinheiten2(1);	
							break;
						case 3:
							haus = new HausEinheiten3(1);
							break;
						case 4:
							zz = new ZaunEnergie1(1);	
							break;

						case 5:
							zz = new ZaunEnergie2(1);	
							break;

						case 6:
							zz = new ZaunEnergie3(1);
							break;

						case 7:
							geb = new GebEnergie1(1);	
							break;

						case 8:
							geb = new GebEnergie2(1);
							break;

						case 9:
							geb = new GebEnergie3(1);	
							break;
						}
					}
				}



				if (geb != null) {
					System.out.println("Quelle vorhanden");
					liste.get(i).add(UnderworldE.drawFeld(geb, i, j));
					panelFelder.add(liste.get(i).get(j));
				}

				if (zz != null) {
					System.out.println("Zaun vorhanden");
					liste.get(i).add(UnderworldE.drawFeld(zz, i, j));
					panelFelder.add(liste.get(i).get(j));
				}
				if (haus != null) {
					System.out.println("Haus vorhanden");
					liste.get(i).add(UnderworldE.drawFeld(haus, i, j));
					panelFelder.add(liste.get(i).get(j));
				}
				if (einheit != null) {

					System.out.println("Einheit vorhanden");
					liste.get(i).add(setWarrior(einheit, i, j));
					panelFelder.add(liste.get(i).get(j));

				} else {
//					System.out.println("Feld leer");
					liste.get(i).add(new Field(i, j, "K", this));
					panelFelder.add(liste.get(i).get(j));
				}

//				System.out.println("--------------------------");
			}
		}

		panelFelder.repaint();

		panelEast = new JPanel();
		panelEast.setLayout(new MigLayout("", "[89px]", "[23px][][][][][][][][][][][]"));
		panelEast.setOpaque(false);

		btnKaempfer1 = new JButton("Simple Unicorn");
		btnKaempfer1.addActionListener(new btnU1ActionListener());
		btnKaempfer1.setToolTipText("Warrior who is strong but very vulnerable");
		btnKaempfer1.setBackground(new Color(147, 112, 219));
		btnKaempfer1.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		panelEast.add(btnKaempfer1, "cell 0 4,growx,aligny top");

		btnKaempfer2 = new JButton("Unicorn Warrior");
		btnKaempfer2.addActionListener(new btnU2ActionListener());
		btnKaempfer2.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnKaempfer2.setToolTipText("warrior who is neraly indestructibel but not very strong");
		btnKaempfer2.setBackground(new Color(147, 112, 219));
		panelEast.add(btnKaempfer2, "cell 0 5,growx");

		btnKaempfer3 = new JButton("Fight Unicorn");
		btnKaempfer3.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnKaempfer3.addActionListener(new btnU3ActionListener());
		btnKaempfer3.setToolTipText("strong and neraly indestructible warrior");
		btnKaempfer3.setBackground(new Color(147, 112, 219));
		panelEast.add(btnKaempfer3, "cell 0 6,growx");

		btnDelete = new JButton("delete");
		btnDelete.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnDelete.addActionListener(new btnDeleteActionListener());
		btnDelete.setToolTipText("select a item you want to delete");
		btnDelete.setBackground(new Color(147, 112, 219));
		panelEast.add(btnDelete, "cell 0 9,growx");

	}

	private class CbBearbeitenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			if (cbBearbeiten.isSelected()) {

				JOptionPane.showMessageDialog(null, " Klick on a field and then choose a warror to start the fight");
				frame.getContentPane().add(panelEast, BorderLayout.EAST);
				panelEast.validate();
				frame.getContentPane().validate();
			}

			if (cbBearbeiten.isSelected() == false) {

				frame.getContentPane().remove(panelEast);
				frame.getContentPane().validate();
			}
		}
	}

	public void redraw() {

		// panelFelder = new JPanel();
		panelFelder.removeAll();
		System.out.println("REDRAW");
		ArrayList<ArrayList<Field>> liste = new ArrayList<ArrayList<Field>>();

		Controller tester = new Controller();

		for (int i = 0; i < reihen; i++) {

			liste.add(new ArrayList<Field>());

			for (int j = 0; j < spalten; j++) {

				int index = tester.getField(UnderworldFieldstest, j, i);
				Einheit einheit = null;
				einheit = UnderworldFieldstest.get(index).getEinheit();
				Gebäude geb = UnderworldFieldstest.get(index).getGebäude();
				Zaun zaun = UnderworldFieldstest.get(index).getZaun();
				Haus haus = UnderworldFieldstest.get(index).getHaus();

				// System.out.println("Derzeitige Einheit: "+einheit+ "I: "+i+" J:"+j);

				if (geb != null) {
					System.out.println("Gebaude vorhanden");
					liste.get(i).add(UnderworldE.drawFeld(geb, i, j));
					panelFelder.add(liste.get(i).get(j));
				}
				if (einheit != null) {

					System.out.println("Einheit vorhanden");
					liste.get(i).add(setWarrior(einheit, i, j));
					panelFelder.add(liste.get(i).get(j));

				}
				if (geb == null && zaun == null && haus == null && einheit == null) {
					System.out.println("Feld leer");
					liste.get(i).add(new Field(i, j, "K", this));
					panelFelder.add(liste.get(i).get(j));
				}
			}
		}

		frame.getContentPane().repaint();
		frame.getContentPane().validate();
	}

	private class btnU1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			Einheit einheit = new SimpleUnicorn(1);
			UnderworldFieldstest.get(index).setEinheit(einheit);
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnU2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			Einheit einheit = new FightUnicorn(1);
			UnderworldFieldstest.get(index).setEinheit(einheit);
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnU3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			Einheit einheit = new UnicornWarrior(1);
			UnderworldFieldstest.get(index).setEinheit(einheit);
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnDeleteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			UnderworldFieldstest.get(index).deleteField();
			UnderworldFieldstest.get(index).setBelegt(false);
			redraw();

		}
	}

	private void setAnzReihen(int anz) {
		reihen = anz;
	}

	private void setAnzSpalten(int anz) {
		spalten = anz;
	}

	public Field setWarrior(Einheit einheit, int x, int y) {

		Field buffer = new Field(x, y, "K", this);
		String einheitstyp = einheit.getClass().getName();

		switch (einheitstyp) {
		case "control.SimpleUnicorn": {
			System.out.println("Unicorn wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/u1_klein.png");
			break;
		}
		case "control.FightUnicorn": {
			System.out.println("Unicorn wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/u3_klein.png");
			break;
		}
		case "control.UnicornWarrior": {
			System.out.println("Unicorn wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/u2_klein.png");
			break;
		}
		default: {
			System.out.println("UNBEKANNTE KLASSE KÄMPFER; ERROR");
		}
		}

		return buffer;

	}

	public void setArrayList() {
		String[] zeile = new String[3];
		for (int i = 0; i <= 3; i++) {

		}
		// FelderUW.add
		// UnderworldField=UnderworldK.UnderworldFieldstest;
		// System.out.println(UnderworldField.get(index).);
	}

}