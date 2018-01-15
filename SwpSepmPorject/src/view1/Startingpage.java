package view1;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import control.ActionListenerStartingpageBtn;

public class Startingpage extends JFrame{

	JButton btnLogin;
	JLabel lblStartbild;

	/**
	 * Create the application.
	 */
	public Startingpage() {

	
		ImageIcon img = new ImageIcon(UnderworldE.class.getResource("/pictures/UnicornStart.png"));
		Image im = img.getImage().getScaledInstance(1920, 1080, Image.SCALE_FAST);
		img = new ImageIcon(im);
		
		setContentPane(new JLabel(img));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("pictures/unicorn.png"));
		setTitle("Cute Unicorn Fight To Death");
		setFont(new Font("Century Schoolbook", Font.ITALIC, 14));
		setBounds(100, 100, 700, 437);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		/**Platziert den Frame in der Mitte des Bildschirms*/
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 3);
		setLocation(x, y);

		btnLogin = new JButton("getting started");
		btnLogin.setBackground(new Color(255, 153, 204));
		btnLogin.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		btnLogin.addActionListener(new ActionListenerStartingpageBtn(this)); 
		btnLogin.setBounds(490, 322, 150, 40);
		getContentPane().add(btnLogin);

		lblStartbild = new JLabel("");
		lblStartbild.setIcon(new ImageIcon("C:\\Users\\Sara\\Documents\\SWP-SEPM 5 Kl\\UnicornLogo.png")); //TODO
		lblStartbild.setBounds(0, 0, 695, 400);
		getContentPane().add(lblStartbild);

		validate();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Startingpage frame = new Startingpage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
