import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame{

    public JButton loadSaveButton = new JButton("Load/Save");
    public JButton searchButton = new JButton("Search");

    public MainMenuView() {

        this.setTitle("Main Menu");
        this.setSize(new Dimension(300,150));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel line1 = new JPanel();
        line1.add(loadSaveButton);
        line1.add(searchButton);
        this.getContentPane().add(line1);


    }
}