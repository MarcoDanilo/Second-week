package Weekly;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2456443812055385444L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField pwdPass;
	public static String username;
	public static String current_amount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("Username :");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(41, 24, 70, 20);
		contentPane.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setBounds(41, 55, 162, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(41, 86, 70, 14);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUser.getText().length()==0)  // Checking for empty field
				      JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
				   else if(pwdPass.getPassword().length==0)  // Checking for empty field
				      JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
				  
				   else{
				       String user = txtUser.getText();   // Collecting the input
				       char[] pass = pwdPass.getPassword(); // Collecting the input
				       String pwd = String.copyValueOf(pass);  // converting from array to string
				       if(validate_login(user,pwd)){
				          JOptionPane.showMessageDialog(null, "You have been successfully logged in!");
				       setVisible(false);
				       new DepWith().setVisible(true);
				       username = user;
				       bal();
				       }
				       else
				          JOptionPane.showMessageDialog(null, "Incorrect Login Credentials");
				   }  
			}
			
			private boolean validate_login(String user_Name,String user_Password) {
				   try{           
				       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
				       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbsecondweek", "root", "");  
				       System.out.println("Konektado ka na!");
				       PreparedStatement pst = (PreparedStatement) conn.prepareStatement("Select * from user where user_Name=? and user_Password=?");
				       pst.setString(1, user_Name); 
				       pst.setString(2, user_Password);
				       ResultSet rs = pst.executeQuery();                        
				       if(rs.next())            
				           return true;    
				       else
				           return false;            
				   }
				   catch(Exception e){
				       e.printStackTrace();
				       return false;
				   }       
				}
		});
		btnNewButton.setBounds(41, 168, 162, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewAccount = new JLabel("New Account?");
		lblNewAccount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewAccount.setBounds(41, 202, 95, 14);
		contentPane.add(lblNewAccount);
		
		JButton btnNewButton_1 = new JButton("Sign up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			       new Signup().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(41, 227, 162, 23);
		contentPane.add(btnNewButton_1);
		
		pwdPass = new JPasswordField();
		pwdPass.setBounds(41, 111, 162, 20);
		contentPane.add(pwdPass);
		
		JCheckBox chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()) {
				     pwdPass.setEchoChar((char) 0);
				} else {
				     pwdPass.setEchoChar('*');
				}
			}
			
		
			
		});
		chckbxShowPassword.setBounds(41, 138, 140, 23);
		contentPane.add(chckbxShowPassword);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Warning", JOptionPane.YES_NO_OPTION);
				if(dialogResult==JOptionPane.YES_NO_OPTION){
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(41, 261, 162, 23);
		contentPane.add(btnExit);
		

		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	void bal (){
		try {
			 Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbsecondweek", "root", "");  
		       System.out.println("Konektado ka na!");
		       Statement stmt = (Statement) conn.createStatement ();
		       String acc = "SELECT user_Money from user WHERE user_Name = '" + MainMenu.username + "'" ;
		       ResultSet bal= stmt.executeQuery(acc);
		       if(bal.next()){
		    	   current_amount = bal.getString("user_Money");
		       }
			
		}catch (Exception s) {
			s.printStackTrace();
			}
		}
}