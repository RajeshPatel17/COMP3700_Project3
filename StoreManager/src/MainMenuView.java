import javax.swing.*;
import java.awt.*;

/* Main view that gets displayed when program is executed.
 * Allows user to toggle between input menus
*/
public class MainMenuView extends JFrame {

    public JButton ManaButton = new JButton("Manager");
    public JButton CustButton = new JButton("Customer");


    public MainMenuView(){

        this.setTitle("Main Menu");
        this.setSize(new Dimension(400,100));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel ButtonPanel = new JPanel();
        JPanel line1 = new JPanel();
        line1.add(new JLabel("Are you a: "));
        this.getContentPane().add(line1);

        ButtonPanel.add(ManaButton);
        ButtonPanel.add(CustButton);


        this.getContentPane().add(ButtonPanel);
    }
}