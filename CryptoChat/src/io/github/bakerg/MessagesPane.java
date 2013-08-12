package io.github.bakerg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;

public class MessagesPane {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void showMessagesPane(String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessagesPane window = new MessagesPane();
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
	public MessagesPane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "name_1376315966915757000");
	}

}
