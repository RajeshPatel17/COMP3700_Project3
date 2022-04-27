import javax.swing.*;
import java.awt.*;

public class OrderHistoryView extends JFrame{
    public JTextArea orderHistoryArea = new JTextArea(30,30);

    public JButton backButton = new JButton("Back");
    public JButton loadButton = new JButton("Load History");

    public OrderHistoryView(){
        this.setTitle("Your Order History");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Order History"));
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        orderHistoryArea.setEditable(false);
        line2.add(orderHistoryArea);
        this.getContentPane().add(line2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(loadButton);
        this.getContentPane().add(buttonPanel);
    }
}
