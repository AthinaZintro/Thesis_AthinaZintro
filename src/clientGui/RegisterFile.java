package clientGui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegisterFile extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFile frame = new RegisterFile();
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
	public RegisterFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 837, 430);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register File");
		lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(193, 71, 445, 96);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Choose File");
		btnNewButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DraftApplication register =new DraftApplication();
				register.draft();
				
			}
		});
		btnNewButton.setBounds(70, 255, 295, 71);
		panel.add(btnNewButton);
		
		JButton btnGiveInformation = new JButton("Give information");
		btnGiveInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GiveRegisterInfo information=new GiveRegisterInfo();
				information.setVisible(true);
				
				
			}
		});
		btnGiveInformation.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnGiveInformation.setBounds(502, 255, 308, 71);
		panel.add(btnGiveInformation);
	}

}
