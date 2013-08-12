package io.github.bakerg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPane {

	private JFrame frmLoginCryptochat;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private static final String serverAddress = "localhost"; //Set the server address here

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPane window = new LoginPane();
					window.frmLoginCryptochat.setResizable(false);
					window.frmLoginCryptochat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginCryptochat = new JFrame();
		frmLoginCryptochat.setTitle("Login - CryptoChat");
		frmLoginCryptochat.setBounds(100, 100, 600, 172);
		frmLoginCryptochat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginCryptochat.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(40, 40, 78, 16);
		frmLoginCryptochat.getContentPane().add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(118, 34, 442, 28);
		frmLoginCryptochat.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(40, 78, 78, 16);
		frmLoginCryptochat.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(118, 72, 442, 28);
		frmLoginCryptochat.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(passwordField.getPassword() != null && usernameField.getText() != null){
					Network.connect(serverAddress);
					Network.createAccount(usernameField.getText(), Crypto.md5(new String(passwordField.getPassword())));
				}
			}
		});
		btnCreate.setBounds(118, 106, 117, 29);
		frmLoginCryptochat.getContentPane().add(btnCreate);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(passwordField.getPassword() != null && usernameField.getText() != null){
					Network.connect(serverAddress);
					Network.login(usernameField.getText(), Crypto.md5(new String(passwordField.getPassword())));
				}
			}
		});
		btnLogin.setBounds(443, 106, 117, 29);
		frmLoginCryptochat.getContentPane().add(btnLogin);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBounds(247, 106, 117, 29);
		frmLoginCryptochat.getContentPane().add(btnSettings);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(270, 6, 60, 16);
		frmLoginCryptochat.getContentPane().add(lblLogin);
	}
}
