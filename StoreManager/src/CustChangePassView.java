import javax.swing.*;
import java.awt.*;

public class CustChangePassView extends JFrame{

    public JPasswordField oldPasswordText = new JPasswordField();
    public JPasswordField newPasswordText = new JPasswordField();

    public JButton backButton = new JButton("Back");
    public JButton saveButton = new JButton("Save New Password");

    public CustChangePassView(){
        
        this.setTitle("Change Password");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Old Password"));
        oldPasswordText.setEchoChar('*');
        line1.add(oldPasswordText);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("New Password"));
        newPasswordText.setEchoChar('*');
        line2.add(newPasswordText);
        this.getContentPane().add(line2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(saveButton);
        this.getContentPane().add(buttonPanel);


    }
    
}
