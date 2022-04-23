import javax.swing.*;
import java.awt.*;

public class NewCustomerView extends JFrame{
    
    public JTextField userNameText = new JTextField(30);
    public JPasswordField passwordText = new JPasswordField(30);
    public JTextField displayNameText = new JTextField(30);
    public JTextField customerNameText = new JTextField(30);
    public JTextField dateOfBirthText = new JTextField(30);
    public JTextField addressText = new JTextField(30);

    public JButton saveButton = new JButton("Save");
    public JButton backButton = new JButton("Back");
    public JButton clearButton = new JButton("Clear");

    public NewCustomerView(){

        this.setTitle("New Customer");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel();
        line1.add(new JLabel("User Name"));
        line1.add(userNameText);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Password"));
        passwordText.setEchoChar('*');
        line2.add(passwordText);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("Display Name"));
        line3.add(displayNameText);
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel();
        line4.add(new JLabel("First and Last Name"));
        line4.add(customerNameText);
        this.getContentPane().add(line4);

        JPanel line5 = new JPanel();
        line5.add(new JLabel("Date of Birth"));
        line5.add(dateOfBirthText);
        this.getContentPane().add(line5);

        JPanel line6 = new JPanel();
        line6.add(new JLabel("Address"));
        line6.add(addressText);
        this.getContentPane().add(line6);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);

    }

}
