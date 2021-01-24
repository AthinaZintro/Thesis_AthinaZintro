package clientGui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clientRegister.ClientRegisterFile;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GiveRegisterInfo extends JFrame {

	private JPanel contentPane;
	private JTextField txtFilename;
	private JTextField txtNumberOfLabels;
	private JTextField txtDelimiter;
	private JTextField [] textField;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiveRegisterInfo frame = new GiveRegisterInfo();
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
	public GiveRegisterInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1167, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBounds(0, 11, 1143, 532);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Give informations to register a file");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 30));
		lblNewLabel.setBounds(204, 26, 704, 64);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filename :");
		lblNewLabel_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(30, 124, 164, 39);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Delimiter of labels :");
		lblNewLabel_1_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(30, 173, 349, 39);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Number of labels :");
		lblNewLabel_1_2.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		lblNewLabel_1_2.setBounds(30, 220, 310, 39);
		panel.add(lblNewLabel_1_2);
		txtFilename = new JTextField();
		txtFilename.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		txtFilename.setBounds(183, 120, 891, 35);
		panel.add(txtFilename);
		txtFilename.setColumns(10);
		
		txtNumberOfLabels = new JTextField();
		txtNumberOfLabels.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		txtNumberOfLabels.setColumns(10);
		txtNumberOfLabels.setBounds(304, 225, 770, 35);
		panel.add(txtNumberOfLabels);
		
		txtDelimiter = new JTextField();
		txtDelimiter.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		txtDelimiter.setColumns(10);
		txtDelimiter.setBounds(355, 171, 719, 35);
		panel.add(txtDelimiter);
		JButton btnNewButton = new JButton(" Add Labels ");
		btnNewButton.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				int numberOfLabels=Integer.parseInt(txtNumberOfLabels.getText().toString());
				int j=0;
				 textField=new JTextField[numberOfLabels];
				for(int i=0;i<numberOfLabels;i++) {
					textField[i]=new JTextField("Label");
					textField[i].setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
					textField[i].setColumns(10);
					textField[i].setBounds(30, 345+j, 703, 35);
					panel.add(textField[i]);
					j+=50;
					
				}
				
			
				

						
			}
		});

		
		
				
		
		

		btnNewButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnNewButton.setBounds(438, 295, 236, 39);
		panel.add(btnNewButton);
		
		JButton btnRegisterFile = new JButton("Register File");
		btnRegisterFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numberOfLabels=Integer.parseInt(txtNumberOfLabels.getText().toString());

				String [] register=new String[3+numberOfLabels];
				register[0]=txtFilename.getText();
				register[1]=txtDelimiter.getText();
				register[2]=txtNumberOfLabels.getText();

				for(int i=0;i<numberOfLabels;i++) {
					register[i+3]=textField[i].getText()+" ";
					
				}
				ClientRegisterFile.registerInfo(register);
				
			}
		});
		btnRegisterFile.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnRegisterFile.setBounds(884, 465, 236, 39);
		panel.add(btnRegisterFile);
		

		

	
	}
}