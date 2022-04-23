import javax.swing.*;
import java.awt.*;

public class ProductSearchView extends JFrame{

    public JTextField keywordText = new JTextField(30);
    public JTextArea resultArea = new JTextArea(30,30);

    public JButton searchButton = new JButton("Search");
    public JButton backButton = new JButton("Back");

    public ProductSearchView(){

        this.setTitle("Product Search");
        this.setSize(new Dimension(600, 300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));


        JPanel line1 = new JPanel();
        line1.add(new JLabel("Keyword(s)"));
        line1.add(keywordText);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(resultArea);
        this.getContentPane().add(line2);
    }

}