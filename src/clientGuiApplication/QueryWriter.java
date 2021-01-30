package clientGuiApplication;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clientQueringApplication.ClientQuering;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.JTextPane;
import java.awt.Dimension;


@SuppressWarnings("serial")
public class QueryWriter extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public QueryWriter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 759, 532);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Query");
		lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(184, 11, 359, 51);
		panel.add(lblNewLabel);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setAutoscrolls(true);
		tabbedPane.setBounds(10, 83, 739, 358);
		panel.add(tabbedPane);
		
		final JTextPane textPane = new JTextPane();
		textPane.setSize(new Dimension(5, 5));
		textPane.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		tabbedPane.addTab("Query", null, textPane, null);
		tabbedPane.setEnabledAt(0, true);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("List", null, horizontalBox, null);
		tabbedPane.setBackgroundAt(1, Color.LIGHT_GRAY);
		
		
		JButton btnNewButton = new JButton("Execute Query");
		btnNewButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] queryString =textPane.getText().split("//s+");
				int result=ClientQuering.executeFromArgs(queryString);
				if(result==-1) {
					JOptionPane.showMessageDialog(contentPane,"The column information that you gave is wrong!");	
				}else if(result==-2) {
					JOptionPane.showMessageDialog(contentPane,"The file is not registered!");
				}else if(result==-3) {
					JOptionPane.showMessageDialog(contentPane,"The The query that you have typed is wrong");
				}else {
					JOptionPane.showMessageDialog(contentPane,"Query Execution succesfully done!!");
				}
				textPane.setText("");
				 
				
			}
		});
		btnNewButton.setBounds(247, 452, 265, 38);
		panel.add(btnNewButton);
	
		
	}
}