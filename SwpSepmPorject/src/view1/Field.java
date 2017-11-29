package view1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Einheit;
import control.Geb‰ude;
import control.PanelFeldMouseListener;

public class Field extends JPanel{

	private JLabel lblId;  //dient ‹bergangsm‰ﬂig als Hilfe zur Orientierung
	private final int x;
	private final int y;
	private boolean belegt;
	Underworld underworld;
	Overworld overworld;
	BufferedImage bild;
	URL bildURL;

	public Field(int x, int y, Underworld uw ){

		underworld= uw;
		this.x = x; 
		this.y = y; 

		setLayout(new BorderLayout());
		lblId = new JLabel(x+","+y);
		lblId.setFont(new Font("Century Schoolbook", Font.PLAIN, 24));
		setOpaque(false);
		setBackground( Color.black);
		addMouseListener(new PanelFeldMouseListener(this));

	}

	public Field(int i, int j, Overworld ow) {
		// TODO Auto-generated constructor stub
		overworld= ow;
		this.x = i; 
		this.y = j; 

		setLayout(new BorderLayout());
		lblId = new JLabel(x+","+y);
		lblId.setFont(new Font("Century Schoolbook", Font.PLAIN, 24));
		//		setOpaque(false);
		setBackground( Color.black);
		addMouseListener(new PanelFeldMouseListener(this));
	}
	
	public void isBelegt(){
		
	}

	public void setBild(BufferedImage _bild) {
		bild = _bild;
	}

	public void setBild(String bildPfad) {

		bildURL = this.getClass().getClassLoader().getResource(bildPfad);

		try {

			bild = ImageIO.read(bildURL);
		}
		catch (IOException e) {

			e.printStackTrace();
		}
		setBild(bild);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {

			g.drawImage(bild, 0, 0, null);

		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
	}


	public void setZahlenhilfe(boolean ein) {
		lblId.setVisible(ein);
	}

	public int getKoordY (){
		return y;
	}

	public int getKoordX (){
		return x;
	}


	public Underworld getUWorld() {
		return underworld;
	}

	public Overworld getOWorld() {
		return overworld;
	}

}
