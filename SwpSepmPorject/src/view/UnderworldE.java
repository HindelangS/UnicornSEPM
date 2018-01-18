package view;

/***
 * E Wie ERSTELLEN 
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
import javax.swing.border.MatteBorder;
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

public class UnderworldE {

	public JFrame frame;
	private JPanel panelStatus;
	private JButton btnWeiterZurberwelt;
	private JLabel lblWelt;
	private JLabel lblLevel;
	private JLabel lblGeld;
	// private JPanel panelFelder;
	private JPanel panelEast;
	private JButton btnHaus1;
	private JButton btnHaus2;
	private JButton btnHaus3;
	private JCheckBox cbBearbeiten;
	private JPanel phPanel;
	private JButton btnZaun1;
	private JButton btnZaun2;
	private JButton btnZaun3;
	private JButton btnEnergy1;
	private JButton btnEnergy2;
	private JButton btnEnergy3;
	private JButton btnDelete;

	// ArrayList vom Typ UnderworldField um Funktion zu Feld hinzuzufügen mit getX
	public static ArrayList<UnderworldField> UnderworldFieldstest;

	public static int x;

	Field[][] felder;
	Field panelFelder;
	public static Field lastclicked;

	public static UnderworldE underworlde;
	private static int spalten = 10;
	private static int reihen = 8;
	private static String username = Login.getUser();
	
	
	String[] daten=DatenbankUnicorn.SpielerSachen(username);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnderworldE window = new UnderworldE(reihen, spalten);
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
	public UnderworldE(int reihen, int spalten) {
		setAnzReihen(reihen);
		setAnzSpalten(spalten);
		initialize();
		drawUnderworld();
	}

	private void drawUnderworld() {

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		ImageIcon img = new ImageIcon(UnderworldE.class.getResource("/pictures/rosa.jpg"));
		Image im = img.getImage().getScaledInstance(1920, 1080, Image.SCALE_FAST);
		img = new ImageIcon(im);

		frame.setContentPane(new JLabel(img));
		frame.setTitle("Cute Unicorn Fight to Death");
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(UnderworldE.class.getResource("/pictures/unicorn.PNG")));
		frame.setBounds(100, 100, 800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[434px,grow]", "[37px][grow]"));

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 3);
		frame.setLocation(x, y);

		panelStatus = new JPanel();
		panelStatus.setOpaque(false);
		frame.getContentPane().add(panelStatus, BorderLayout.NORTH);
		panelStatus.setLayout(new MigLayout("", "[225][125][125][grow][][20]", "[25px,grow]"));

		lblWelt = new JLabel("World from: " + username);
		lblWelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblWelt, "cell 0 0,alignx left,aligny top");

		lblLevel = new JLabel("Level: "+ daten[2]);
		lblLevel.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblLevel, "cell 1 0,alignx left,aligny top");

		lblGeld = new JLabel("Money: "+daten[0]);
		lblGeld.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblGeld, "cell 2 0,alignx left,aligny top");

		phPanel = new JPanel();
		phPanel.setOpaque(false);
		panelStatus.add(phPanel, "cell 3 0,growx");

		cbBearbeiten = new JCheckBox("edit");
		cbBearbeiten.setToolTipText("show opitons to edit your world");
		panelStatus.add(cbBearbeiten, "cell 5 0,alignx right,aligny center");
		cbBearbeiten.setSelected(false);
		cbBearbeiten.setOpaque(false);
		cbBearbeiten.addActionListener(new CbBearbeitenActionListener());

		btnWeiterZurberwelt = new JButton("change to overworld");
		btnWeiterZurberwelt.setBackground(new Color(173, 216, 230));
		btnWeiterZurberwelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		btnWeiterZurberwelt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UnderworldE.underworlde.frame.dispose();
				Overworld.overworld = true;
				try {
					//TODO in DB speichern
					DatenbankUnicorn.writeFeldUW(username,UnderworldFieldstest);
					
					Overworld window = new Overworld(username, 5, 7);
					window.frame.setVisible(true);
					Overworld.overworldobj = window;
					
				} catch (Exception ee) {
					ee.printStackTrace();
				}

			}
		});
		panelStatus.add(btnWeiterZurberwelt, "cell 4 0,alignx center,aligny center");

		panelFelder = new Field();
		panelFelder.setLayout(new GridLayout(reihen, spalten));
		panelFelder.setBild("pictures/cloud.png");
		frame.getContentPane().add(panelFelder, "cell 0 1,grow");

		// 2D ArrayListe die Spalten mit ihren jeweiligen Feldern speichert.

		ArrayList<ArrayList<Field>> liste = new ArrayList<ArrayList<Field>>();


		ArrayList<String[]> underworldliste = new ArrayList<String[]>();
		

		for (String[] s : Overworld.uwliste) {
			
			System.out.println("Bitch"+s[4]+" "+Login.getUser());
			if (s[4].equalsIgnoreCase(Login.getUser())) {
				underworldliste.add(s);
				System.out.print("nices");
			}
		}

		for (String[] s : underworldliste) {
			System.out.println(
					"User: " + s[4] + "  X: " + s[0] + "  Y: " + s[1] + "   uwid: " + s[3] + "   geid: " + s[2]);
		}


		Controller tester = new Controller();
		UnderworldFieldstest = tester.create();

		for (int i = 0; i < reihen; i++) {

			liste.add(new ArrayList<Field>());
			for (int j = 0; j < spalten; j++) {

				int index = tester.getField(UnderworldFieldstest, i, j);

				Gebäude geb = UnderworldFieldstest.get(index).getGebäude();
				Haus haus = UnderworldFieldstest.get(index).getHaus();
				Zaun zz = UnderworldFieldstest.get(index).getZaun();


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
						case 7:
							zz = new ZaunEnergie1(1);	
							break;
							
						case 8:
							zz = new ZaunEnergie2(1);	
							break;
							
						case 9:
							zz = new ZaunEnergie3(1);
							break;
							
						case 4:
							geb = new GebEnergie1(1);	
							break;
							
						case 5:
							geb = new GebEnergie2(1);
							break;
							
						case 6:
							geb = new GebEnergie3(1);	
							break;
						}
						
					}
				}

				if (geb != null) {
					System.out.println("Gebaude vorhanden" + i +","+j);
					liste.get(i).add(drawFeld(geb, i, j));
					panelFelder.add(liste.get(i).get(j));
				}
				if (haus != null) {
					System.out.println("Haus vorhanden"  + i +","+j);
					liste.get(i).add(drawFeld(haus, i, j));
					panelFelder.add(liste.get(i).get(j));
				}
				if (zz != null) {
					System.out.println("Zaun vorhanden" + i +","+j);
					liste.get(i).add(drawFeld(zz, i, j));
					panelFelder.add(liste.get(i).get(j));
				} else {
					System.out.println("nix auf dem Feld"  + i +","+j);
					liste.get(i).add(new Field(i, j, "E", (UnderworldE) null));
					panelFelder.add(liste.get(i).get(j));
				}

				//				System.out.println("--------------------------");
			}
		}

		//		System.out.println("X X X X X X " + liste.size() + " X X X X X X ");
		panelFelder.repaint();

		panelEast = new JPanel();
		panelEast.setLayout(new MigLayout("", "[89px]", "[23px][][][][][][][][][][][]"));
		panelEast.setOpaque(false);

		btnHaus1 = new JButton("normal house");
		btnHaus1.setBackground(new Color(216, 191, 216));
		btnHaus1.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnHaus1.addActionListener(new btnH1ActionListener());
		btnHaus1.setToolTipText("a normal house can be built ");
		panelEast.add(btnHaus1, "cell 0 0,growx,aligny top");

		btnHaus2 = new JButton("super house");
		btnHaus2.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnHaus2.addActionListener(new btnH2ActionListener());
		btnHaus2.setToolTipText("a super house can be built ");
		btnHaus2.setBackground(new Color(216, 191, 216));
		panelEast.add(btnHaus2, "cell 0 1,growx");

		btnHaus3 = new JButton("fantastic house");
		btnHaus3.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnHaus3.addActionListener(new btnH3ActionListener());
		btnHaus3.setToolTipText("a fantastic house can be built");
		btnHaus3.setBackground(new Color(216, 191, 216));
		panelEast.add(btnHaus3, "cell 0 2,growx");

		btnZaun1 = new JButton("fence");
		btnZaun1.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnZaun1.addActionListener(new btnZ1ActionListener());
		btnZaun1.setToolTipText("build a fence");
		btnZaun1.setBackground(new Color(216, 191, 216));
		panelEast.add(btnZaun1, "cell 0 3,growx");

		btnZaun2 = new JButton("wall");
		btnZaun2.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnZaun2.addActionListener(new btnZ2ActionListener());
		btnZaun2.setToolTipText("build a mega fence");
		btnZaun2.setBackground(new Color(216, 191, 216));
		panelEast.add(btnZaun2, "cell 0 4,growx");

		btnZaun3 = new JButton("super strong wall");
		btnZaun3.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnZaun3.addActionListener(new btnZ3ActionListener());
		btnZaun3.setToolTipText("build a super strong wall");
		btnZaun3.setBackground(new Color(216, 191, 216));
		panelEast.add(btnZaun3, "cell 0 5, growx");

		btnEnergy1 = new JButton("small energy source");
		btnEnergy1.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnEnergy1.addActionListener(new btnE1ActionListener());
		btnEnergy1.setToolTipText("a small energy source can be set up");
		btnEnergy1.setBackground(new Color(216, 191, 216));
		panelEast.add(btnEnergy1, "cell 0 6,growx");

		btnEnergy2 = new JButton("energy source");
		btnEnergy2.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnEnergy2.addActionListener(new btnE2ActionListener());
		btnEnergy2.setToolTipText("a energy source can be set up");
		btnEnergy2.setBackground(new Color(216, 191, 216));
		panelEast.add(btnEnergy2, "cell 0 7,growx");

		btnEnergy3 = new JButton("super energy source");
		btnEnergy3.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnEnergy3.addActionListener(new btnE3ActionListener());
		btnEnergy3.setToolTipText("set up a big enery source");
		btnEnergy3.setBackground(new Color(216, 191, 216));
		panelEast.add(btnEnergy3, "cell 0 8, growx");

		btnDelete = new JButton("delete");
		btnDelete.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		btnDelete.addActionListener(new btnDActionListener());
		btnDelete.setToolTipText("select a item you want to delete");
		btnDelete.setBackground(new Color(216, 191, 216));
		panelEast.add(btnDelete, "cell 0 12,growx");

	}

	private class CbBearbeitenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			if (cbBearbeiten.isSelected()) {

				JOptionPane.showMessageDialog(null,
						"To add an object to a field, choose first the exact field and afterwards the item");
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

	private void redraw() {

		// panelFelder = new JPanel();
		panelFelder.removeAll();
		System.out.println("REDRAW");
		ArrayList<ArrayList<Field>> liste = new ArrayList<ArrayList<Field>>();

		Controller tester = new Controller();


		for (int i = 0; i < reihen; i++) {

			liste.add(new ArrayList<Field>());

			for (int j = 0; j < spalten; j++) {

				int index = tester.getField(UnderworldFieldstest, j, i);

				Gebäude geb = UnderworldFieldstest.get(index).getGebäude();
				Zaun zaun = UnderworldFieldstest.get(index).getZaun();
				Haus haus = UnderworldFieldstest.get(index).getHaus();
				
				//System.out.println(
				//	"Derzeitige Objekte auf Feld: " + geb + "," + zaun + "," + haus + " I: " + i + " J:" + j);

				if (geb != null) {
					System.out.println("Quelle vorhanden");
					liste.get(i).add(drawFeld(geb, i, j));
					panelFelder.add(liste.get(i).get(j));
				}

				if (zaun != null) {

					System.out.println("Zaun vorhanden");
					liste.get(i).add(drawFeld(zaun, i, j));
					panelFelder.add(liste.get(i).get(j));

				}
				if (haus != null) {

					System.out.println("Haus vorhanden");
					liste.get(i).add(drawFeld(haus, i, j));
					panelFelder.add(liste.get(i).get(j));

				}
				if (geb == null && zaun == null && haus == null) {
					System.out.println("Feld leer");
					liste.get(i).add(new Field(i, j, "E", (UnderworldE) null));
					panelFelder.add(liste.get(i).get(j));
				}
			}
		}

		frame.getContentPane().repaint();
		frame.getContentPane().validate();

	}

	private class btnH1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			UnderworldFieldstest.get(index).setHaus(new HausEinheiten1(1));
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnH2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			UnderworldFieldstest.get(index).setHaus(new HausEinheiten2(1));
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnH3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			UnderworldFieldstest.get(index).setHaus(new HausEinheiten3(1));
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnZ1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			UnderworldFieldstest.get(index).setZaun(new ZaunEnergie1(1));
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnZ2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			UnderworldFieldstest.get(index).setZaun(new ZaunEnergie2(1));
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnZ3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			UnderworldFieldstest.get(index).setZaun(new ZaunEnergie3(1));
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnE1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			UnderworldFieldstest.get(index).setGebäude(new GebEnergie1(1));
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnE2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			UnderworldFieldstest.get(index).setGebäude(new GebEnergie2(1));
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnE3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());

			UnderworldFieldstest.get(index).setGebäude(new GebEnergie3(1));
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnDActionListener implements ActionListener {
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

	public static Field drawFeld(Gebäude geb, int x, int y) {

		Field buffer = new Field(x, y, "E", (UnderworldE) null);
		String gebtyp = geb.getClass().getName();

		switch (gebtyp) {

		case "control.GebEnergie1": {
			System.out.println("GebEnergie1 wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/e3.png");
			break;
		}
		case "control.GebEnergie2": {
			System.out.println("GebEnergie2 wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/e2.png");
			break;
		}
		case "control.GebEnergie3": {
			System.out.println("GebEnergie3 wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/e1.png");
			break;
		}

		default: {
			System.out.println("UNBEKANNTE KLASSE GEBÄUDE ERROR");
		}
		}

		return buffer;
	}

	public static Field drawFeld(Haus haus, int x, int y) {

		Field buffer = new Field(x, y, "E", (UnderworldE) null);
		String haustyp = haus.getClass().getName();

		switch (haustyp) {

		case "control.HausEinheiten1": {
			System.out.println("Haus1 wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/haus1_klein.png");
			break;
		}
		case "control.HausEinheiten2": {
			System.out.println("Haus2 wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/haus2_klein.png");
			break;
		}
		case "control.HausEinheiten3": {
			System.out.println("Haus3 wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/haus3_klein.png");
			break;
		}

		default: {
			System.out.println("UNBEKANNTE KLASSE Haus ERROR");
		}
		}

		return buffer;
	}

	public static Field drawFeld(Zaun z, int x, int y) {
		System.out.println("DRAW Zaun");
		Field buffer = new Field(x, y, "E", (UnderworldE) null);
		String zauntyp = z.getClass().getName();

		switch (zauntyp) {
		case "control.ZaunEnergie1": {
			System.out.println("Zaun wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/zaun3_klein.png");
			break;
		}
		case "control.ZaunEnergie2": {
			System.out.println("Zaun wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/zaun1_klein.png");
			break;
		}
		case "control.ZaunEnergie3": {
			System.out.println("Zaun wird auf" + x + "/" + y + " gezeichnet.");
			buffer.setBild("pictures/zaun2_klein.png");
			break;
		}
		default: {
			System.out.println("UNBEKANNTE KLASSE Zaun; ERROR");
		}
		}

		return buffer;

	}

}