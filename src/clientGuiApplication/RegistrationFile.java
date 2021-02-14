package clientGuiApplication;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegistrationFile extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationFile frame = new RegistrationFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistrationFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 991, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 32, 967, 430);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose the way you want to register the file");
		lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 937, 96);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Choose File");
		btnNewButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				FileRegisterChoice register =new FileRegisterChoice();
				//dispose();
			}
		});
		btnNewButton.setBounds(86, 220, 308, 71);
		panel.add(btnNewButton);
		
		JButton btnGiveInformation = new JButton("Give information");
		btnGiveInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrationInfo information=new RegistrationInfo();
				information.setVisible(true);
				
				
			}
		});
		btnGiveInformation.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnGiveInformation.setBounds(560, 220, 308, 71);
		panel.add(btnGiveInformation);
		
		JButton btnNewButton_1 = new JButton("<< Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnNewButton_1.setBounds(10, 374, 146, 45);
		panel.add(btnNewButton_1);
	}

}
