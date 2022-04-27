import javax.swing.*;
import java.awt.*;

public class MCCOrderView extends JFrame{
    public JTextArea productArea = new JTextArea(30,30);
    public JTextField orderIDText = new JTextField(30);

    public JButton backButton = new JButton("Back");
    public JButton loadButton = new JButton("Load Order");
    public JButton saveButton = new JButton("Save Order");
    public JButton cancelButton = new JButton("Cancel Order");
    public JButton addProductButton = new JButton("Add Product");
    public JButton removeProductButton = new JButton("Remove Product");

    public MCCOrderView(){
        this.setTitle("Edit Your Profile");
        this.setSize(new Dimension(700, 500));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Order ID (Blank For New Order"));
        line1.add(orderIDText);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Order Information"));
        line2.add(productArea);
        this.getContentPane().add(line2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(addProductButton);
        buttonPanel.add(removeProductButton);
        this.getContentPane().add(buttonPanel);
    }

    
}
