import javax.swing.*;
import java.awt.*;

public class CustEditView extends JFrame{
    
    public JTextField customerNameText = new JTextField(30);
    public JTextField displayNameText = new JTextField(30);
    public JTextField dateOfBirthText = new JTextField(30);
    public JTextField addressText = new JTextField(30);

    public JButton backButton = new JButton("Back");
    public JButton clearButton = new JButton("Clear Fields");
    public JButton saveButton = new JButton("Save");

    public CustEditView() {
        this.setTitle("Edit Your Profile");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Fill in the fields you would like to change."));
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Customer Name"));
        line2.add(customerNameText);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("Display Name"));
        line3.add(displayNameText);
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel();
        line4.add(new JLabel("Date of Birth"));
        line4.add(dateOfBirthText);
        this.getContentPane().add(line4);

        JPanel line5 = new JPanel();
        line5.add(new JLabel("Address"));
        line5.add(addressText);
        this.getContentPane().add(line5);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);
        this.getContentPane().add(buttonPanel);
    }
}
