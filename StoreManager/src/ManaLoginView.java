import javax.swing.*;
import java.awt.*;

public class ManaLoginView extends JFrame{
    public JTextField manaUserNameText = new JTextField(30);
    public JPasswordField manaPassText = new JPasswordField(30);

    public JButton backButton = new JButton("Back");
    public JButton loginButton = new JButton("Login");

    public ManaLoginView(){
        this.setTitle("Manager Login");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel();
        line1.add(new JLabel("User Name"));
        line1.add(manaUserNameText);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Password"));
        manaPassText.setEchoChar('*');
        line2.add(manaPassText);
        this.getContentPane().add(line2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(loginButton);
        this.getContentPane().add(buttonPanel);
    }
    
}
