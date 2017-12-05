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

	BufferedImage bild;
	URL bildURL;
	
	public Field( int y, int x){
		
		this.x = x; 
		this.y = y; 
		System.out.println("Was dazu: "+x+" / "+y);
		setLayout(new BorderLayout());
		lblId = new JLabel(x+" / "+y);
		lblId.setFont(new Font("Century Schoolbook", Font.PLAIN, 24));
		add(lblId);
		setOpaque(false);
		
		setBackground(new Color((int) (Math.random()*255), (int)(Math.random()*255),(int)(Math.random()*255)));
		addMouseListener(new PanelFeldMouseListener(this));

	}

	public Field(int y, int x, Overworld uw) {
		// TODO Auto-generated constructor stub
		super();
		
		this.x = x; 
		this.y = y;
	}

	public Field(int y, int x, Underworld uw) {
		// TODO Auto-generated constructor stub
		super();
		
		this.x = x; 
		this.y = y;
	}

	public void setBild(BufferedImage _bild) {
		bild = _bild;
	}
	
	public URL getBild()
	{
		return this.bildURL;
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

	public int getKoordY (){
		return y;
	}

	public int getKoordX (){
		return x;
	}
	
	public Underworld getUWorld() {
		// TODO Auto-generated method stub
		return null;
	}
}
