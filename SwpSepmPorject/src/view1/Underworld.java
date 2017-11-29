package view1;

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
import java.util.ArrayList;

public class Underworld{

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
	private JButton btnZaun2;
	private JButton btnWall;
	private JButton btnEnergy1;
	private JButton btnEnergy2;
	private JButton btnEnergy3;
	private JButton btnWaffe1;
	private JButton btnWaffe2;
	private JButton btnWaffe3;

	Field[][] felder;
	Underworld uw; 

	private static int spalten = 10; 
	private static int reihen = 8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Underworld window = new Underworld(reihen, spalten);
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
	public Underworld(int reihen, int spalten) {
		setAnzReihen(reihen);
		setAnzSpalten(spalten);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		ImageIcon img = new ImageIcon(Underworld.class.getResource("/pictures/rosa.jpg"));
		Image im = img.getImage().getScaledInstance(1920, 1080, Image.SCALE_FAST);
		img = new ImageIcon(im);
		frame.setContentPane(new JLabel(img));
		frame.setTitle("Cute Unicorn Fight to Death");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Underworld.class.getResource("/pictures/unicorn.PNG")));
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
		frame.getContentPane().add(panelFelder, "cell 0 1,grow");

		ArrayList<ArrayList<Field>> liste = new ArrayList<ArrayList<Field>>();

		for(int i = 0; i < reihen; i++) {
			liste.add(new ArrayList<Field>());
			for(int j = 0; j < spalten; j++) {

				liste.get(i).add(new Field(i,j,uw));
				liste.get(i).get(j).setBackground(new Color((int) (Math.random()*255), (int)(Math.random()*255),(int)(Math.random()*255)));
				liste.get(i).get(j).setVisible(true);
				panelFelder.add(liste.get(i).get(j));

			}
		}

		panelEast = new JPanel();
		panelEast.setLayout(new MigLayout("", "[89px]", "[23px][][][][][][][][][][][]"));
		panelEast.setOpaque(false);

		btnHaus1 = new JButton("normal house");
		btnHaus1.addActionListener(new btnH1ActionListener());
		btnHaus1.setToolTipText("a normal house can be built ");
		panelEast.add(btnHaus1, "cell 0 0,growx,aligny top");

		btnHaus2 = new JButton("super house");
		btnHaus2.addActionListener(new btnH2ActionListener());
		btnHaus2.setToolTipText("a super house can be built ");
		btnHaus2.setEnabled(false);
		panelEast.add(btnHaus2, "cell 0 1,growx");

		btnHaus3 = new JButton("fantastic house");
		btnHaus3.setEnabled(false);
		btnHaus3.addActionListener(new btnH3ActionListener());
		btnHaus3.setToolTipText("a fantastic house can be built");
		panelEast.add(btnHaus3, "cell 0 2,growx");

		btnZaun1 = new JButton("fence");
		btnZaun1.addActionListener(new btnZ1ActionListener());
		btnZaun1.setToolTipText("built a fence");
		panelEast.add(btnZaun1, "cell 0 3,growx");

		btnZaun2 = new JButton("super strong fence");
		btnZaun2.addActionListener(new btnZ2ActionListener());
		btnZaun2.setToolTipText("a super strong fence can be built ");
		btnZaun2.setEnabled(false);
		panelEast.add(btnZaun2, "cell 0 4,growx");

		btnWall = new JButton("wall");
		btnWall.addActionListener(new btnZ3ActionListener());
		btnWall.setToolTipText("a super strong wall can be built ");
		btnWall.setEnabled(false);
		panelEast.add(btnWall, "cell 0 5,growx");

		btnEnergy1 = new JButton("small energy source");
		btnEnergy1.addActionListener(new btnE1ActionListener());
		btnEnergy1.setToolTipText("a small energy source can be set up");
		panelEast.add(btnEnergy1, "cell 0 6,growx");

		btnEnergy2 = new JButton("energy source");
		btnEnergy2.addActionListener(new btnE2ActionListener());
		btnEnergy2.setToolTipText("a small energy source can be set up");
		btnEnergy2.setEnabled(false);
		panelEast.add(btnEnergy2, "cell 0 7,growx");

		btnEnergy3 = new JButton("super energy source");
		btnEnergy3.addActionListener(new btnE3ActionListener());
		btnEnergy3.setToolTipText("set up a big enery source");
		btnEnergy3.setEnabled(false);
		panelEast.add(btnEnergy3, "cell 0 8");

		btnWaffe1 = new JButton("weapon");
		btnWaffe1.addActionListener(new btnW1ActionListener());
		btnWaffe1.setToolTipText("place a weapon");
		panelEast.add(btnWaffe1, "cell 0 9,growx");

		btnWaffe2 = new JButton("weapon3");
		btnWaffe2.addActionListener(new btnW2ActionListener());
		btnWaffe2.setToolTipText("place a weapon");
		panelEast.add(btnWaffe2, "cell 0 10,growx");

		btnWaffe3 = new JButton("weapon2");
		btnWaffe3.addActionListener(new btnW3ActionListener());
		btnWaffe3.setToolTipText("place a weapon");
		panelEast.add(btnWaffe3, "cell 0 11,growx");

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
	
	private class btnH1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

		
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
	
	
	private class btnW1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

		
		}
	}

	private class btnW2ActionListener implements ActionListener {
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



}
