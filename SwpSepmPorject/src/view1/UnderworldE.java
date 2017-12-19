package view1;
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

public class UnderworldE{

	private JFrame frame;
	private JPanel panelStatus;
	private JButton btnWeiterZurberwelt;
	private JLabel lblWelt;
	private JLabel lblLevel;
	private JLabel lblGeld;
	private JPanel panelFelder;
	private JPanel panelEast;
	private JButton btnHaus1;
	private JButton btnHaus2;
	private JButton btnHaus3;
	private JCheckBox cbBearbeiten;
	private JPanel phPanel;
	private JButton btnZaun1;
	private JButton btnEnergy1;
	private JButton btnEnergy2;
	private JButton btnEnergy3;
	private JButton btnDelete;

	//ArrayList vom Typ UnderworldField um Funktion zu Feld hinzuzufügen mit getX
	public static ArrayList <UnderworldField> UnderworldFieldstest;

	public static int x;

	Field[][] felder;
	public static Field lastclicked;

	private static int spalten = 10; 
	private static int reihen = 8;

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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(UnderworldE.class.getResource("/pictures/unicorn.PNG")));
		frame.setBounds(100, 100, 800, 600);
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

		lblWelt = new JLabel("SpielerXs Welt: ");
		lblWelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblWelt, "cell 0 0,alignx left,aligny top");

		lblLevel = new JLabel("Level: ");
		lblLevel.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(lblLevel, "cell 1 0,alignx left,aligny top");

		lblGeld = new JLabel("Geld: ");
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

		btnWeiterZurberwelt = new JButton("weiter zur Ueberwelt");
		btnWeiterZurberwelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(btnWeiterZurberwelt, "cell 4 0,alignx center,aligny center");

		panelFelder = new JPanel();
		panelFelder.setBackground(Color.PINK);
		panelFelder.setOpaque(false);
		panelFelder.setLayout(new GridLayout(reihen, spalten));
		//		panelFelder.setBild("pictures/cloud.jpg");
		frame.getContentPane().add(panelFelder, "cell 0 1,grow");	

		
		//2D ArrayListe die Spalten mit ihren jeweiligen Feldern speichert. 
		
		ArrayList<ArrayList<Field>> liste = new ArrayList<ArrayList<Field>>();

		Controller tester = new Controller();
		UnderworldFieldstest =  tester.create();

		//				UnderworldFieldstest.get(16).setEinheit(new Schildkämpfer(3));
		//				UnderworldFieldstest.get(16).setBelegt(true);

		for(int i = 0; i < reihen; i++) {
			
			//ArrayListe 
			liste.add(new ArrayList<Field>());
				for(int j = 0; j < spalten; j++) {

				int index = tester.getField(UnderworldFieldstest, i, j);
				Einheit einheit = null;
				einheit = UnderworldFieldstest.get(index).getEinheit();
				Gebäude geb = UnderworldFieldstest.get(index).getGebäude();

				System.out.println("Derzeitige Einheit: "+einheit+ "I: "+i+" J:"+j);

				if(geb != null){
					System.out.println("Gebaude vorhanden");
					liste.get(i).add(drawFeld(geb, i, j));
					panelFelder.add(liste.get(i).get(j));
				}
				if(einheit != null){

					System.out.println("Einheit vorhanden");
					liste.get(i).add(drawFeld(einheit, i, j));
					panelFelder.add(liste.get(i).get(j));

				}else{
					System.out.println("nix auf dem Feld");
					liste.get(i).add(new Field(i,j));
					panelFelder.add(liste.get(i).get(j));
				}

				System.out.println("--------------------------");
			}
		}
		
		System.out.println("XXXXXX"+liste.size());
		panelFelder.repaint();

		panelEast = new JPanel();
		panelEast.setLayout(new MigLayout("", "[89px]", "[23px][][][][][][][][][][][]"));
		panelEast.setOpaque(false);

		btnHaus1 = new JButton("normal house");
		btnHaus1.addActionListener(new btnH1ActionListener());
		btnHaus1.setToolTipText("a normal house can be built ");
		btnHaus1.setBackground(Color.darkGray);
		panelEast.add(btnHaus1, "cell 0 0,growx,aligny top");

		btnHaus2 = new JButton("super house");
		btnHaus2.addActionListener(new btnH2ActionListener());
		btnHaus2.setToolTipText("a super house can be built ");
		btnHaus2.setEnabled(false);
		btnHaus2.setBackground(Color.darkGray);
		panelEast.add(btnHaus2, "cell 0 1,growx");

		btnHaus3 = new JButton("fantastic house");
		btnHaus3.setEnabled(false);
		btnHaus3.addActionListener(new btnH3ActionListener());
		btnHaus3.setToolTipText("a fantastic house can be built");
		btnHaus3.setBackground(Color.darkGray);
		panelEast.add(btnHaus3, "cell 0 2,growx");

		btnZaun1 = new JButton("fence");
		btnZaun1.addActionListener(new btnZ1ActionListener());
		btnZaun1.setToolTipText("built a fence");
		btnZaun1.setBackground(Color.darkGray);
		panelEast.add(btnZaun1, "cell 0 3,growx");

		btnEnergy1 = new JButton("small energy source");
		btnEnergy1.addActionListener(new btnE1ActionListener());
		btnEnergy1.setToolTipText("a small energy source can be set up");
		btnEnergy1.setBackground(Color.darkGray);
		panelEast.add(btnEnergy1, "cell 0 4,growx");

		btnEnergy2 = new JButton("energy source");
		btnEnergy2.addActionListener(new btnE2ActionListener());
		btnEnergy2.setToolTipText("a energy source can be set up");
		btnEnergy2.setEnabled(false);
		btnEnergy2.setBackground(Color.darkGray);
		panelEast.add(btnEnergy2, "cell 0 5,growx");

		btnEnergy3 = new JButton("super energy source");
		btnEnergy3.addActionListener(new btnE3ActionListener());
		btnEnergy3.setToolTipText("set up a big enery source");
		btnEnergy3.setEnabled(false);
		btnEnergy3.setBackground(Color.darkGray);
		panelEast.add(btnEnergy3, "cell 0 6");

		btnDelete = new JButton("delete");
		btnDelete.addActionListener(new btnDActionListener());
		btnDelete.setToolTipText("select a item you want to delete");
		btnDelete.setBackground(Color.darkGray);
		panelEast.add(btnDelete, "cell 0 12,growx");

	}

	private class CbBearbeitenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			if(cbBearbeiten.isSelected()){

				JOptionPane.showMessageDialog(null, "Um Gegenstand auf Feld zu setzen bitte zuerst Panel auswählen");
				System.out.println("ok cool");
				frame.getContentPane().add(panelEast, BorderLayout.EAST);
				panelEast.validate();
				frame.getContentPane().validate();
			}

			if(cbBearbeiten.isSelected()==false){

				frame.getContentPane().remove(panelEast);
				frame.getContentPane().validate();
			}
		}
	}

	private void redraw(){

		//neu malen
		//		if(einheit.getClass()== Schildkämpfer.class){
		//					Field buffer = new Field(i,j);
		//					System.out.println("afdasfe");
		//					buffer.setBild("pictures/cloud.jpg");
		//					liste.get(i).add(buffer);
		//					panelFelder.add(liste.get(i).get(j));
		//					System.out.println(liste.get(i).get(j).getBild());
		//				}

	}

	private class btnH1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Controller c1 = new Controller();
			int index = c1.getField(UnderworldFieldstest, lastclicked.getKoordX(), lastclicked.getKoordY());
			
			Field buffer = new Field(lastclicked.getKoordX(), lastclicked.getKoordY());
			buffer.setBild("pictures/haus1_klein.png");
//			liste.get(i).add(buffer);
//			panelFelder.add(buffer.get(i).get(j));


			UnderworldFieldstest.get(index).setGebäude(new GebäudeTurm(1));
			UnderworldFieldstest.get(index).setBelegt(true);
			redraw();

		}
	}

	private class btnH2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {


		}
	}

	private class btnH3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {



		}
	}

	private class btnZ1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {



		}
	}

	private class btnE1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {


		}
	}

	private class btnE2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {


		}
	}

	private class btnE3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {



		}
	}


	private class btnDActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {



		}
	}

	private void setAnzReihen(int anz) {
		reihen = anz;
	}

	private void setAnzSpalten(int anz) {
		spalten = anz;
	}

	public Field drawFeld(Gebäude geb,int x,int y){

		Field buffer = new Field(x,y);
		String gebtyp = geb.getClass().getName();

		switch(gebtyp){
		case "control.GebäudeTurm":{
			System.out.println("Turm wird auf"+x+"/"+y+" gezeichnet.");
			buffer.setBild("pictures/house1..jpg");
			break;
		}

		default:{
			System.out.println("UNBEKANNTE KLASSE GEBÄUDE ERROR");
		}
		}

		return buffer;
	}

	public Field drawFeld(Einheit einheit,int x, int y){

		Field buffer = new Field(x,y);
		String einheitstyp = einheit.getClass().getName();

		switch(einheitstyp){
		case "control.Schildkämpfer":{
			System.out.println("Schildkämpfer wird auf"+x+"/"+y+" gezeichnet.");
			buffer.setBild("pictures/cloud.jpg");
			break;
		}
		case "control.Schwertkämpfer":{
			System.out.println("Schildkämpfer wird auf"+x+"/"+y+" gezeichnet.");
			buffer.setBild("pictures/house1..jpg");
			break;
		}
		default:{
			System.out.println("UNBEKANNTE KLASSE KÄMPFER; ERROR");
		}
		}

		return buffer;

	}



}