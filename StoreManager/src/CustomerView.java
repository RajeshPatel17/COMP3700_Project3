import javax.swing.*;
import java.awt.*;

/* View for interacting with Customer Objects */

public class CustomerView extends JFrame {

    public JTextField CustomerIDText = new JTextField(30);
    public JTextField CustomerNameText = new JTextField(30);
    public JTextField CustomerDOBText = new JTextField(30);
    public JTextField CustomerAddrText = new JTextField(30);

    public JButton retrieveCustomerButton = new JButton("Retrieve Customer");
    public JButton uploadCustomerButton = new JButton("Upload Customer");

    public CustomerView() {

        this.setTitle("Customer View");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Customer ID"));
        line1.add(CustomerIDText);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Customer Name"));
        line2.add(CustomerNameText);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("Date Of Birth (yyyy-mm-dd)"));
        line3.add(CustomerDOBText);
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel();
        line4.add(new JLabel("Customer Address"));
        line4.add(CustomerAddrText);
        this.getContentPane().add(line4);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(retrieveCustomerButton);
        buttonPanel.add(uploadCustomerButton);
        this.getContentPane().add(buttonPanel);

    }

}
