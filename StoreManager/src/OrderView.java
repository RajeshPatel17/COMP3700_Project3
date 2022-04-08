import javax.swing.*;
import java.awt.*;
/* View that interacts with orders */
public class OrderView extends JFrame{

    public JTextField orderIDText = new JTextField(30);
    public JTextField orderDateText = new JTextField(30);
    public JTextField CustomerText = new JTextField(30);
    public JTextField TotalCostText = new JTextField(30);
    public JTextField TotalTaxText = new JTextField(30);


    public JButton retrieveOrderButton = new JButton("Retrieve Order");
    public JButton uploadOrderButton = new JButton("Upload Order");


    public OrderView(){

        this.setTitle("Order View");
        this.setSize(new Dimension(600,300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Order ID"));
        line1.add(orderIDText);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Order Date (yyyy-mm-dd)"));
        line2.add(orderDateText);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("Customer ID"));
        line3.add(CustomerText);
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel();
        line4.add(new JLabel("Total Cost"));
        line4.add(TotalCostText);
        this.getContentPane().add(line4);

        JPanel line5 = new JPanel();
        line5.add(new JLabel("Total Tax"));
        line5.add(TotalTaxText);
        this.getContentPane().add(line5);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(retrieveOrderButton);
        buttonPanel.add(uploadOrderButton);
        this.getContentPane().add(buttonPanel);

    }
}