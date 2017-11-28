package view1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.ActionListernerContinueBtn;

import java.awt.Font;

public class Login extends JFrame{
	
	JLabel lblName;
	JLabel lblPwd;
	JButton btnContinue;
	JTextField txtName;
	JTextField txtPwd;
	
	public Login(){
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Startingpage.class.getResource("/pictures/Unicorn.png")));
		getContentPane().setEnabled(false);
		setTitle("Cute Unicorn Figth To Death");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setContentPane(new JLabel(new ImageIcon(Startingpage.class.getResource("/pictures/Login.png"))));
		getContentPane().setLayout(null);
		
		/** Platziert den Frame in der Mitte des Bildschirms; genau dort wo zuerst die Startseite war*/
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 3);
		setLocation(x, y);
		
		lblName = new JLabel("Username: ");
		lblName.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		lblName.setBounds(106, 126, 117, 28);
		getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(268, 133, 117, 21);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		txtName.setOpaque(false);
		
		txtPwd = new JTextField();
		txtPwd.setHorizontalAlignment(SwingConstants.CENTER);
		txtPwd.setText("*****");
		txtPwd.setBounds(268, 262, 117, 21);
		getContentPane().add(txtPwd);
		txtPwd.setColumns(10);
		txtPwd.setOpaque(false);
		
		lblPwd = new JLabel("Password: ");
		lblPwd.setFont(new Font("Century Schoolbook", Font.ITALIC, 18));
		lblPwd.setBounds(106, 251, 117, 37);
		getContentPane().add(lblPwd);
	
		btnContinue = new JButton("Login");
		btnContinue.setBackground(new Color(32, 178, 170));
		btnContinue.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		btnContinue.setBounds(455, 313, 102, 37);
		btnContinue.setForeground(Color.WHITE);
//		btnContinue.addActionListener(new ActionListenerContinueBtn(this));
		getContentPane().add(btnContinue);
		
		validate();

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtPwd() {
		return txtPwd;
	}


}
