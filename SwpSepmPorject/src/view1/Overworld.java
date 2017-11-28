package view1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.UnderworldField;

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
	private JPanel panel;
	
	Field[][] felder;
	Overworld uw; 

	private static int spalten = 7; 
	private static int reihen = 5;

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

		btnWeiterZurberwelt = new JButton("weiter zur Overworld");
		btnWeiterZurberwelt.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		panelStatus.add(btnWeiterZurberwelt, "cell 4 0,alignx right,aligny top");

		panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(reihen, spalten));
		frame.getContentPane().add(panel, "cell 0 1,grow");
		
		ArrayList<ArrayList<Field>> liste = new ArrayList<ArrayList<Field>>();
		
		for(int i = 0; i < reihen; i++) {
			liste.add(new ArrayList<Field>());
			for(int j = 0; j < spalten; j++) {
				
				liste.get(i).add(new Field(i,j,uw));
				liste.get(i).get(j).setBackground(new Color((int) (Math.random()*255), (int)(Math.random()*255),(int)(Math.random()*255)));
				liste.get(i).get(j).setVisible(true);
				panel.add(liste.get(i).get(j));

				
			}
		}

	}

	private void setAnzReihen(int anz) {
		reihen = anz;
	}

	private void setAnzSpalten(int anz) {
		spalten = anz;
	}



}
