package clientGuiApplication;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		setBounds(100, 100, 946, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 912, 408);
		contentPane.add(panel);
		panel.setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Choose the way you want to register the file");
		lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(-20, 11, 937, 96);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Choose File");
		btnNewButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileRegisterChoice register =new FileRegisterChoice();
				if(register.RegisterChoice()==-1) {
					JOptionPane.showMessageDialog(contentPane,"You did not make any choice");
					
				}else {
					JOptionPane.showMessageDialog(contentPane,"Registered file suceed");
					
				}
				
			}
		});
		btnNewButton.setBounds(86, 207, 308, 71);
		panel.add(btnNewButton);
		
		JButton btnGiveInformation = new JButton("Give information");
		btnGiveInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrationInfo information=new RegistrationInfo();
				information.setVisible(true);
				
				
			}
		});
		btnGiveInformation.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnGiveInformation.setBounds(537, 207, 308, 71);
		panel.add(btnGiveInformation);
		
		JButton btnNewButton_1 = new JButton("<< Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnNewButton_1.setBounds(10, 332, 146, 45);
		panel.add(btnNewButton_1);
	}

}
