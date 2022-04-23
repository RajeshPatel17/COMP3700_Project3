import javax.swing.*;
import java.awt.*;

public class CustLoginView extends JFrame{
    
    public JTextField CustUserNameText = new JTextField(30);
    public JPasswordField CustomerPassText = new JPasswordField(30);

    public JButton logInButton = new JButton("Log In");
    public JButton newCustomerButton = new JButton("New Customer");
    public JButton backButton = new JButton("Back");

    public CustLoginView() {

        this.setTitle("Customer Login");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel();
        line1.add(new JLabel("User Name"));
        line1.add(CustUserNameText);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Password"));
        CustomerPassText.setEchoChar('*');
        line2.add(CustomerPassText);
        this.getContentPane().add(line2);

        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.add(logInButton);
        this.getContentPane().add(buttonPanel1);

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.add(backButton);
        buttonPanel2.add(newCustomerButton);
        this.getContentPane().add(buttonPanel2);
    }
}
