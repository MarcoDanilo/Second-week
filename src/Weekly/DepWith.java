package Weekly;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DepWith extends JFrame {

	private JPanel contentPane;
	public static String current_amount;
	DecimalFormat df = new DecimalFormat("#.00");
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DepWith frame = new DepWith();
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DepWith() {
		df.setGroupingUsed(true);
		df.setGroupingSize(3);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			       new Deposit().setVisible(true);
			}
		});
		btnDeposit.setBounds(39, 82, 150, 23);
		contentPane.add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			       new Withdraw().setVisible(true);
			}
		});
		btnWithdraw.setBounds(39, 116, 150, 23);
		contentPane.add(btnWithdraw);
		
		JButton btnAccount = new JButton("Account");
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//			       new Account().setVisible(true);
				
				double md = Double.parseDouble(MainMenu.current_amount);
				JOptionPane.showMessageDialog(null, "Your current balance is:" +" " +"PHP" +" " +df.format(md),
						"Balance", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		btnAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAccount.setBounds(39, 150, 150, 23);
		contentPane.add(btnAccount);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
				if(dialogResult==JOptionPane.YES_NO_OPTION){
					setVisible(false);
				       new MainMenu().setVisible(true);
				}
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOut.setBounds(39, 184, 150, 23);
		contentPane.add(btnLogOut);
		
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
