import javax.swing.*;
import java.awt.*;


public class SearchView extends JFrame{
    
    public JTextField keywordField = new JTextField(30);
    public JTextArea results =  new JTextArea(30, 50);

    public JButton searchButton = new JButton("Search Note");
    public JButton clearButton = new JButton("Clear Fields");

    public SearchView(){

        this.setTitle("Search View");
        this.setSize(new Dimension(600,300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout


        JPanel line1 = new JPanel();
        line1.add(new JLabel("Keyword"));
        line1.add(keywordField);
        this.getContentPane().add(line1);


        JPanel line2 = new JPanel();
        line2.add(new JLabel("Results"));
        line2.add(results);
        this.getContentPane().add(line2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);
        this.getContentPane().add(buttonPanel);

    }
}
