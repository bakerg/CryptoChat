package io.github.bakerg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsPane {

	private JFrame frmSettingsCryptochat;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void showSettings() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsPane window = new SettingsPane();
					window.frmSettingsCryptochat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SettingsPane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSettingsCryptochat = new JFrame();
		frmSettingsCryptochat.setTitle("Settings - CryptoChat");
		frmSettingsCryptochat.setResizable(false);
		frmSettingsCryptochat.setBounds(100, 100, 450, 120);
		frmSettingsCryptochat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSettingsCryptochat.getContentPane().setLayout(null);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setBounds(194, 6, 62, 16);
		frmSettingsCryptochat.getContentPane().add(lblSettings);
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setBounds(40, 40, 78, 16);
		frmSettingsCryptochat.getContentPane().add(lblServer);
		
		textField = new JTextField();
		textField.setBounds(118, 34, 292, 28);
		frmSettingsCryptochat.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(Network.getAddress());
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Network.setAddress(textField.getText());
			}
		});
		btnSave.setBounds(166, 63, 118, 29);
		frmSettingsCryptochat.getContentPane().add(btnSave);
	}
}
