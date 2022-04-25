import javax.swing.*;

public class OrderMenuView extends JFrame{
    
    public JButton MCCOrderButton = new JButton("Make/Change/Cancel Order");
    public JButton backButton = new JButton("Back");
    public JButton orderHistoryButton = new JButton("Order History");

    public OrderMenuView(){

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(MCCOrderButton);
        buttonPanel.add(orderHistoryButton);
        this.getContentPane().add(buttonPanel);
        
    }
}
