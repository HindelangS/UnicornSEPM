package view;

import java.awt.EventQueue;
import java.awt.FocusTraversalPolicy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DatenbankUnicorn;

import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPasswordField;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextPane txtpnRegistrieren;
	private JTextPane txtpnBenutzername;
	private JTextPane txtpnPasswort;
	private JTextPane pww;
	private JTextField textField;
	private JButton btnRegistrieren;
	private JLabel lblError;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	private Login login;
	private Register reg;
	private ArrayList<Component> focusComps;

	static final String DB_URL = "jdbc:mysql://localhost:3306/cuftd?user=root&password=&useSSL=false";

	static private String username;
	static private int uwid;

	/**
	 * Wie lange das Passwort mindestens sein muss
	 */
	private final int pwMinLaenge = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Falls Registrierungs-Fenster durch LogIn aufgerufen wird
	 * LogIn Fenster deaktivieren
	 * @param login LogIn Fenster, von dem aus das Registrierungsfenster aufgerufen wird (optional)
	 */
	public Register(Login login) {
		// Anderen Konstruktor aufrufen
		this();
		this.login = login;
		this.login.setEnabled(false);
	}
	/**
	 * Create the frame.
	 */
	public Register() {
		initialize();
	}
	private void initialize() {
		setResizable(false);
		setVisible(true);
		setTitle("Register");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Overworld.class.getResource("/pictures/unicorn.PNG")));

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 3);
		setLocation(x, y);

		txtpnRegistrieren = new JTextPane();
		txtpnRegistrieren.setEditable(false);
		txtpnRegistrieren.setFont(new Font("Century Schoolbook", Font.ITALIC, 20));
		txtpnRegistrieren.setText("Register: ");
		txtpnRegistrieren.setBounds(10, 11, 113, 31);
		contentPane.add(txtpnRegistrieren);

		txtpnBenutzername = new JTextPane();
		txtpnBenutzername.setEditable(false);
		txtpnBenutzername.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		txtpnBenutzername.setText("Nickname: ");
		txtpnBenutzername.setBounds(10, 84, 113, 25);
		contentPane.add(txtpnBenutzername);

		txtpnPasswort = new JTextPane();
		txtpnPasswort.setEditable(false);
		txtpnPasswort.setText("Password: ");
		txtpnPasswort.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		txtpnPasswort.setBounds(10, 131, 113, 25);
		contentPane.add(txtpnPasswort);

		pww = new JTextPane();
		pww.setEditable(false);
		pww.setText("repeat pw: ");
		pww.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		pww.setBounds(10, 167, 113, 25);
		contentPane.add(pww);

		textField = new JTextField();
		textField.setBounds(133, 89, 194, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		btnRegistrieren = new JButton("GO !");
		btnRegistrieren.addActionListener(new btnRegistrierenActionListener());
		btnRegistrieren.setBounds(140, 220, 150, 30);
		btnRegistrieren.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		contentPane.add(btnRegistrieren);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Century Schoolbook", Font.PLAIN, 10));
		lblError.setBounds(10, 257, 424, 14);
		contentPane.add(lblError);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(133, 136, 194, 20);
		contentPane.add(passwordField_1);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(133, 172, 194, 20);
		contentPane.add(passwordField_2);


		focusComps = new ArrayList<Component>();
		focusComps.add(textField);
		focusComps.add(passwordField_1);
		focusComps.add(passwordField_2);
		focusComps.add(btnRegistrieren);

		this.setFocusTraversalPolicy(new FocusReihenfolge(focusComps));
	}

	@Override
	public void dispose() {

		if(reg != null) {
			reg.setEnabled(true);
			reg = null;
		}
		super.dispose();
	}

	public void anmelden() {
		System.out.println("Overworld wird gebaut..... ");
		username = textField.getText();
		Overworld.overworld = true;
		try {
			JOptionPane.showMessageDialog(null, "Choose either your own world to rebuild it, or fight  with your troops");
			Overworld window = new Overworld(username ,5, 7);
			window.frame.setVisible(true);
			Overworld.overworldobj = window;

		} catch (Exception ee) {
			ee.printStackTrace();
		}
		dispose();
	}

	private class btnRegistrierenActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			username=textField.getText();
			
			lblError.setText("");
			if(passwordField_1.getPassword().length >= pwMinLaenge) {
	
				if(String.valueOf(passwordField_1.getPassword()).equals(String.valueOf(passwordField_2.getPassword()))) {
					int fehlercode;
					if((fehlercode = DatenbankUnicorn.spielerRegistrieren(textField.getText(), String.valueOf(passwordField_1.getPassword()))) == 0) {
	
						DatenbankUnicorn.UWSPielerzuteilen(username);

						Login frame = new Login();
						frame.setVisible(true);
						
						dispose();
					}
					else {					 
						lblError.setText(DatenbankUnicorn.fehlercodeAufloesen(fehlercode));
					}
				}
				else {lblError.setText("The passwords do not match!");				}
			}
			else lblError.setText("The passwords must have at least 8 characters!");
		}
	}

}
