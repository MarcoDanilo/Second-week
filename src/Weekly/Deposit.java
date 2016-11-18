package Weekly;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Deposit extends JFrame {

	private JPanel contentPane;
	private JTextField textField_amount;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Deposit frame = new Deposit();
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
	public Deposit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_amtdep = new JLabel("Enter amount you want to deposit:");
		lbl_amtdep.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_amtdep.setBounds(40, 132, 234, 14);
		contentPane.add(lbl_amtdep);
		
		textField_amount = new JTextField();
		textField_amount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c =e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
				getToolkit().beep();
				e.consume();
				}
			}
		});
		textField_amount.setBounds(40, 157, 190, 20);
		contentPane.add(textField_amount);
		textField_amount.setColumns(10);
		
		JButton btnDeposit = new JButton("Confirm");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
				       if(textField_amount.getText().isEmpty()){  // Checking for empty field
						      JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");}
				       else if (Double.parseDouble(textField_amount.getText())<100){
				    	   JOptionPane.showMessageDialog(null, "You can only deposit cash amount of 100" , "minimum", JOptionPane.INFORMATION_MESSAGE); 
				       }
				       else if(dialogResult==JOptionPane.YES_NO_OPTION){
							setVisible(false);
						       new DepWith().setVisible(true);
						       deposit();
				       }
			}
		});
		btnDeposit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeposit.setBounds(40, 188, 89, 23);
		contentPane.add(btnDeposit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
				if(dialogResult==JOptionPane.YES_NO_OPTION){
					setVisible(false);
				       new DepWith().setVisible(true);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(141, 188, 89, 23);
		contentPane.add(btnExit);

		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	void deposit (){
		
		 double depositAmount=Double.parseDouble(textField_amount.getText());
		 double current=Double.parseDouble(MainMenu.current_amount);
		 double sumatotal= current + depositAmount;
		 
		 MainMenu.current_amount=Double.toString(sumatotal);
//		 if(!depositAmount.matches("[0-9]+")){
//			 System.out.println("Invalid number");
//		 }else
		try{
			
			 Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbsecondweek", "root", "");  
		       System.out.println("Konektado ka na!");
		       Statement stmt = (Statement) conn.createStatement ();
		       String dep = "UPDATE user SET user_Money = " + sumatotal + " WHERE user_Name =  '" +MainMenu.username +"'";
		       stmt.executeUpdate(dep);
		}catch (Exception s) {
			s.printStackTrace();
			}
	}
}
