import javax.swing.*;
import java.awt.*;

public class CustMenuView extends JFrame{
   
    public JButton editInfoButton = new JButton("Edit My Info");
    public JButton changePasswordButton = new JButton("Change Password");
    public JButton orderButton = new JButton("Order Information");

    public CustMenuView(){
        this.setTitle("Customer Menu");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editInfoButton);
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(orderButton);
        this.getContentPane().add(buttonPanel);

    }
}
