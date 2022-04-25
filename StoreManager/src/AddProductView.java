import javax.swing.*;
import java.awt.*;

public class AddProductView extends JFrame{
    public JTextField productIDText = new JTextField(30);
    public JTextField productNameText = new JTextField(30);
    public JTextField costText = new JTextField(30);
    public JTextField maxQuantityText = new JTextField(30);

    public JTextField selectedQuantityText = new JTextField(30);
    public JButton backButton = new JButton("Back");
    public JButton addProductButton = new JButton("Add Product");
    public JButton nextProductButton = new JButton("Next Product");
    public JButton prevProductButton = new JButton("Previous Product");

    public AddProductView(){
        this.setTitle("Add Product");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel();
        line1.add(new JLabel("Product ID"));
        productIDText.setEditable(false);
        line1.add(productIDText);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Product Name"));
        productNameText.setEditable(false);
        line2.add(productNameText);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("Product Cost"));
        costText.setEditable(false);
        line3.add(costText);
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel();
        line4.add(new JLabel("Available Quantity"));
        maxQuantityText.setEditable(false);
        line4.add(maxQuantityText);
        this.getContentPane().add(line4);


        JPanel line5 = new JPanel();
        line5.add(new JLabel("Selected Quantity"));
        line5.add(selectedQuantityText);
        this.getContentPane().add(line5);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(prevProductButton);
        buttonPanel.add(addProductButton);
        buttonPanel.add(nextProductButton);
        
    }
}
