import javax.swing.*;
import java.awt.*;

public class OrderMenuView extends JFrame{
    
    public JButton MCCOrderButton = new JButton("Make/Change/Cancel Order");
    public JButton backButton = new JButton("Back");
    public JButton orderHistoryButton = new JButton("Order History");

    public OrderMenuView(){
        this.setTitle("Order Menu");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(MCCOrderButton);
        buttonPanel.add(orderHistoryButton);
        this.getContentPane().add(buttonPanel);
        
    }
}
