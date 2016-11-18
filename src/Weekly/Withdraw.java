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
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Withdraw extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Withdraw;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Withdraw frame = new Withdraw();
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
	public Withdraw() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterAmountYou = new JLabel("Enter amount you want to withdraw:");
		lblEnterAmountYou.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterAmountYou.setBounds(40, 115, 234, 14);
		contentPane.add(lblEnterAmountYou);
		
		textField_Withdraw = new JTextField();
		textField_Withdraw.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent evt) {
				
				char c =evt.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
				getToolkit().beep();
				evt.consume();
				}
			}
		});
		textField_Withdraw.setBounds(40, 140, 190, 20);
		contentPane.add(textField_Withdraw);
		textField_Withdraw.setColumns(10);
		
		JButton btnWithdraw = new JButton("Confirm");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
			       if(textField_Withdraw.getText().isEmpty()){  // Checking for empty field
					      JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");}
			       else if (Double.parseDouble(textField_Withdraw.getText())<100){
			    	   JOptionPane.showMessageDialog(null, "Minimum cash withdrawal amount is 100" , "minimum", JOptionPane.INFORMATION_MESSAGE);
			    	   
			       }else if (Double.parseDouble(MainMenu.current_amount)==0)
			    	   JOptionPane.showMessageDialog(null, "You have insufficient balance" , "minimum", JOptionPane.INFORMATION_MESSAGE);
			       
			       else if(dialogResult==JOptionPane.YES_NO_OPTION){
						setVisible(false);
					       new DepWith().setVisible(true);
					       
					      withdraw();
				}
			}
		});
		btnWithdraw.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnWithdraw.setBounds(40, 171, 89, 23);
		contentPane.add(btnWithdraw);
		
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
				if(dialogResult==JOptionPane.YES_NO_OPTION){
					setVisible(false);
				       new DepWith().setVisible(true);
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(139, 171, 89, 23);
		contentPane.add(button);

		setVisible(true);
		setLocationRelativeTo(null);
	}
void withdraw(){
		double depositAmount=Double.parseDouble(textField_Withdraw.getText());
	 double current=Double.parseDouble(MainMenu.current_amount);
	 double sumatotal= current - depositAmount;
	 MainMenu.current_amount=Double.toString(sumatotal);
	try{
		 Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
	       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbsecondweek", "root", "");  
	       System.out.println("Konektado ka na!");
	       Statement stmt = (Statement) conn.createStatement ();
	       String dep = "UPDATE user SET user_Money = " + sumatotal + " WHERE user_Name =  '" +MainMenu.username +"'";
	       stmt.executeUpdate(dep);
		
	}catch (Exception d) {
		d.printStackTrace();
		}
}
}
