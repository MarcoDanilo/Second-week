package Weekly;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTextField textField_name;
	private JTextField textField_address;
	private JTextField textField_money;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Signup frame = new Signup();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(10, 27, 99, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(10, 52, 99, 14);
		contentPane.add(lblPassword);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(10, 77, 99, 14);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(10, 102, 99, 14);
		contentPane.add(lblAddress);
		
		JLabel lblInitialDeposit = new JLabel("Initial Deposit :");
		lblInitialDeposit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInitialDeposit.setBounds(10, 127, 99, 14);
		contentPane.add(lblInitialDeposit);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_username.getText().length()==0){
					JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");}
					else if(textField_password.getText().length()==0)  // Checking for empty field
					      JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
					else if(textField_name.getText().length()==0)  // Checking for empty field
					      JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
					else if(textField_address.getText().length()==0)  // Checking for empty field
					      JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
					else if(textField_money.getText().length()==0)  // Checking for empty field
					      JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
					else if(Double.parseDouble(textField_money.getText())<100){
					JOptionPane.showMessageDialog(null, "Please enter a value greater than 99" , "number", JOptionPane.INFORMATION_MESSAGE);}
				else {			
			       try {
			    	   
			    	   Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
				       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbsecondweek", "root", "");  
				       System.out.println("Konektado ka na!");
				       
				       Statement st = (Statement) conn.createStatement(); 
				       String Stmt = "INSERT INTO user VALUES (\"" + textField_username.getText() + "\", \"" 
				       												                        + textField_password.getText() + "\", \""
				       												                        + textField_name.getText() + "\", \""
				       												                        + textField_address.getText() + "\", \""
				       												                        + textField_money.getText() + "\")";
				       												                        
				       			st.executeUpdate(Stmt);
				       			MainMenu.username=textField_username.getText();
				       			setVisible(false);
				       			MainMenu.username=textField_username.getText();
				       			MainMenu.current_amount=textField_money.getText();
							    new DepWith().setVisible(true);
			            
			       }catch (Exception a){
				       a.printStackTrace();
				   }     
				}        
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSignUp.setBounds(24, 163, 89, 23);
		contentPane.add(btnSignUp);
		
		textField_username = new JTextField();
		textField_username.setBounds(83, 26, 141, 20);
		contentPane.add(textField_username);
		textField_username.setColumns(10);
		
		textField_password = new JTextField();
		textField_password.setBounds(83, 51, 141, 20);
		contentPane.add(textField_password);
		textField_password.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setBounds(68, 76, 156, 20);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_address = new JTextField();
		textField_address.setBounds(68, 101, 156, 20);
		contentPane.add(textField_address);
		textField_address.setColumns(10);
		
		textField_money = new JTextField();
		textField_money.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c =e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
				getToolkit().beep();
				e.consume();
				}
			}
		});
		textField_money.setBounds(101, 126, 123, 20);
		contentPane.add(textField_money);
		textField_money.setColumns(10);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			       new MainMenu().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(124, 163, 89, 23);
		contentPane.add(btnNewButton);

		setVisible(true);
		setLocationRelativeTo(null);
	}
}
