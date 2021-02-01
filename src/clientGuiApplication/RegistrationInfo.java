package clientGuiApplication;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clientRegisterApplication.ClientRegisterFile;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class RegistrationInfo extends JFrame {

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
					RegistrationInfo frame = new RegistrationInfo();
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
	public RegistrationInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1167, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setPreferredSize(new Dimension(900,900));
		JScrollPane scroller = new JScrollPane(contentPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.getContentPane().add(scroller, BorderLayout.CENTER);	
		contentPane.setLayout(null);
		setResizable(false);
		
		
		JLabel lblNewLabel = new JLabel("Give informations to register a file");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 30));
		lblNewLabel.setBounds(204, 26, 704, 64);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filename :");
		lblNewLabel_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(30, 124, 164, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Delimiter of labels :");
		lblNewLabel_1_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(30, 173, 349, 39);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Number of labels :");
		lblNewLabel_1_2.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		lblNewLabel_1_2.setBounds(30, 220, 310, 39);
		contentPane.add(lblNewLabel_1_2);
		txtFilename = new JTextField();
		txtFilename.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		txtFilename.setBounds(183, 120, 891, 35);
		contentPane.add(txtFilename);
		txtFilename.setColumns(10);
		
		txtNumberOfLabels = new JTextField();
		txtNumberOfLabels.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		txtNumberOfLabels.setColumns(10);
		txtNumberOfLabels.setBounds(304, 225, 770, 35);
		contentPane.add(txtNumberOfLabels);
		
		txtDelimiter = new JTextField();
		txtDelimiter.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		txtDelimiter.setColumns(10);
		txtDelimiter.setBounds(355, 171, 719, 35);
		contentPane.add(txtDelimiter);
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
					contentPane.add(textField[i]);
					j+=50;
					
				}
				
			
				

						
			}
		});

		
		
				
		
		

		btnNewButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnNewButton.setBounds(438, 295, 236, 39);
		contentPane.add(btnNewButton);
		
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
				dispose();
				
			}
		});
		btnRegisterFile.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnRegisterFile.setBounds(884, 465, 236, 39);
		contentPane.add(btnRegisterFile);
		

		

	
	}
}
