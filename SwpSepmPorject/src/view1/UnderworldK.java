package view1;
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
public class UnderworldK{

	private JFrame frame;
	private JPanel panelStatus;
	private JButton btnWeiterZurberwelt;
	private JLabel lblWelt;
	private JLabel lblLevel;
	private JLabel lblGeld;
	private JPanel panelFelder;
	private JPanel panelEast;
	private JButton btnKaempfer1;
	private JButton btnKaempfer2;
	private JButton btnKaempfer3;
	private JCheckBox cbBearbeiten;
	private JPanel phPanel;
	private JButton btnWeapon1;
	private JButton btnWeapon2;
	private JButton btnWeapon3;
	private JButton btnEnergy1;
	private JButton btnEnergy2;
	private JButton btnEnergy3;
	private JButton btnWaffe1;
	private JButton btnWaffe2;
	private JButton btnWaffe3;
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
					UnderworldK window = new UnderworldK(reihen, spalten);
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
	public UnderworldK(int reihen, int spalten) {
		setAnzReihen(reihen);
		setAnzSpalten(spalten);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		ImageIcon img = new ImageIcon(UnderworldK.class.getResource("/pictures/rosa.jpg"));
		Image im = img.getImage().getScaledInstance(1920, 1080, Image.SCALE_FAST);
		img = new ImageIcon(im);
		frame.setContentPane(new JLabel(img));
		frame.setTitle("Cute Unicorn Fight to Death");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(UnderworldK.class.getResource("/pictures/unicorn.PNG")));
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
					liste.get(i).add(setWarrior(geb, i, j));
					panelFelder.add(liste.get(i).get(j));
				}
				if(einheit != null){

					System.out.println("Einheit vorhanden");
					liste.get(i).add(setWarrior(einheit, i, j));
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

		btnKaempfer1 = new JButton("Warrior who is strong but very vulnerable");
		btnKaempfer1.addActionListener(new btnH1ActionListener());
		btnKaempfer1.setToolTipText("a normal house can be built ");
		btnKaempfer1.setBackground(Color.darkGray);
		panelEast.add(btnKaempfer1, "cell 0 0,growx,aligny top");

		btnKaempfer2 = new JButton("warrior who is neraly indestructibel but not very strong");
		btnKaempfer2.addActionListener(new btnH2ActionListener());
		btnKaempfer2.setToolTipText("a super house can be built ");
		btnKaempfer2.setEnabled(false);
		btnKaempfer2.setBackground(Color.darkGray);
		panelEast.add(btnKaempfer2, "cell 0 1,growx");

		btnKaempfer3 = new JButton("strong and neraly indestructible warrior");
		btnKaempfer3.setEnabled(false);
		btnKaempfer3.addActionListener(new btnH3ActionListener());
		btnKaempfer3.setToolTipText("a fantastic house can be built");
		btnKaempfer3.setBackground(Color.darkGray);
		panelEast.add(btnKaempfer3, "cell 0 2,growx");

		btnWeapon1 = new JButton("fence");
		btnWeapon1.addActionListener(new btnZ1ActionListener());
		btnWeapon1.setToolTipText("set up a small bomb");
		btnWeapon1.setBackground(Color.darkGray);
		panelEast.add(btnWeapon1, "cell 0 3,growx");

		btnWeapon2 = new JButton("super strong fence");
		btnWeapon2.addActionListener(new btnZ2ActionListener());
		btnWeapon2.setToolTipText("cause a big mess ");
		btnWeapon2.setEnabled(false);
		btnWeapon2.setBackground(Color.darkGray);
		panelEast.add(btnWeapon2, "cell 0 4,growx");

		btnWeapon3 = new JButton("wall");
		btnWeapon3.addActionListener(new btnZ3ActionListener());
		btnWeapon3.setToolTipText("destroy this! ");
		btnWeapon3.setEnabled(false);
		btnWeapon3.setBackground(Color.darkGray);
		panelEast.add(btnWeapon3, "cell 0 5,growx");

		btnDelete = new JButton("delete");
		btnDelete.addActionListener(new btnW3ActionListener());
		btnDelete.setToolTipText("select a item you want to delete");
		btnDelete.setBackground(Color.darkGray);
		panelEast.add(btnDelete, "cell 0 6,growx");

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

	private class btnZ2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {



		}
	}

	private class btnZ3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {



		}
	}

	private class btnW3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {



		}
	}

	private void setAnzReihen(int anz) {
		reihen = anz;
	}

	private void setAnzSpalten(int anz) {
		spalten = anz;
	}

	public Field setWarrior(Gebäude geb,int x,int y){

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

	public Field setWarrior(Einheit einheit,int x, int y){

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
