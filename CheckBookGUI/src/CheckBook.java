import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/*
Joseph Bollish

Project Name: CSC372-CTA02

Project Purpose:

Algorithm Used: Using JFrame, JPanel and ActionListener to 
create a graphical user interface. The simple GUI keeps track 
of the balance of a checking account by using the BankAccount 
class and CheckingAccount class.

Program Inputs:

The program take in a single amount and the user has the option 
to deposit or withdraw the amount inputed.
	
Program Outputs:

The program shows the balance of the checking account after 
each amount is entered. If the balance is overdrawn the GUI 
will show that the balance has been overdrawn and that the 
user is charged 30 dollars. The new balance is then shown along 
with the overdraft message. The will also show a error window 
if a number is not entered.

Program Limitations:

Program will except negative amounts for deposits when it 
should not. The program will also reset to 0 after the program 
closes. 

Program Errors:

corrected
The program needed a exit button to exit the GUI.

===========================================
*/

public class CheckBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckBook frame = new CheckBook();
					frame.setVisible(true);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CheckBook() {
		super("Checking Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// labels, frames, and buttons
		CheckingAccount checking = new CheckingAccount();
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(90, 77, 56, 21);
		contentPane.add(lblBalance);
		
		textField = new JTextField();
		textField.setBounds(188, 12, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterAmount = new JLabel("Enter Amount: $");
		lblEnterAmount.setBounds(90, 13, 96, 13);
		contentPane.add(lblEnterAmount);
		
		JLabel label = new JLabel("$0.00");
		label.setBounds(156, 78, 96, 21);
		contentPane.add(label);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setBounds(90, 108, 328, 27);
		contentPane.add(lblMessage);
		
		/*
		 * Deposit listener
		 */
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				try {
				double Input = Double.parseDouble(textField.getText());
				checking.deposit(Input);
				
				NumberFormat formatter = NumberFormat.getCurrencyInstance();	//Thanks for showing how to format balance
				String moneyString = formatter.format(checking.getBalance());
				label.setText(moneyString);
				textField.setText("");
				lblMessage.setText("");
				}catch (Exception exception) { // used catch from your post on P10_11
                    JOptionPane.showMessageDialog(null, "Not a number!", "Invalid input", JOptionPane.ERROR_MESSAGE);
                    textField.setText("");
    				lblMessage.setText("");
                }
			}
		});
		btnDeposit.setBounds(90, 46, 117, 21);
		contentPane.add(btnDeposit);
	
		/*
		 * Withdraw listener
		 */
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				try {
				double Input = Double.parseDouble(textField.getText());
				checking.withdraw(Input);
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				String moneyString = formatter.format(checking.getBalance());
				label.setText(moneyString);
				lblMessage.setText("");
				textField.setText("");
				if(checking.getBalance() < 0 && Input > 0) {
					checking.processWithdrawal();
					NumberFormat fmatter = NumberFormat.getCurrencyInstance(); //Thanks for showing how to format balance
					String moneyString2 = fmatter.format(checking.getBalance());
					lblMessage.setText("Overdraft fee, 30 dollar charge. New balance: " + moneyString2);
							
				}
				}
				catch (Exception exception) { // used catch from your post on P10_11
                    JOptionPane.showMessageDialog(null, "Not a number!", "Invalid input", JOptionPane.ERROR_MESSAGE);
                    textField.setText("");
    				lblMessage.setText("");
                }
			}
		});
		btnWithdraw.setBounds(282, 46, 117, 21);
		contentPane.add(btnWithdraw);
	
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			System.exit(0);
			}
			
		});
		btnExit.setBounds(199, 145, 85, 21);
		contentPane.add(btnExit);
		
		

		

	}
}
