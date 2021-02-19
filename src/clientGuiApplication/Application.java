package clientGuiApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.SwingConstants;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Application {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 865, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setToolTipText("");
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 851, 417);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Select the action you want to perform  ");
		lblNewLabel.setBackground(SystemColor.menu);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 30));
		lblNewLabel.setBounds(95, 56, 705, 108);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Register File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrationFile regFile=new RegistrationFile();
				regFile.setVisible(true);
				
			}
			
		});
		btnNewButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnNewButton.setBounds(80, 271, 293, 82);
		panel.add(btnNewButton);
		
		JButton btnExecuteQuery = new JButton("Query Execution");
		btnExecuteQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryWriter exquery=new QueryWriter();
				exquery.setVisible(true);
			}
		});
		btnExecuteQuery.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnExecuteQuery.setBounds(477, 271, 299, 82);
		panel.add(btnExecuteQuery);
	}


}