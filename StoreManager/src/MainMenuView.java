import javax.swing.*;
import java.awt.*;

/* Main view that gets displayed when program is executed.
 * Allows user to toggle between input menus
*/
public class MainMenuView extends JFrame {

    public JButton ProdButton = new JButton("Product");
    public JButton CustButton = new JButton("Customer");
    public JButton OrdButton = new JButton("Order");

    public MainMenuView(){

        this.setTitle("Main Menu");
        this.setSize(new Dimension(400,100));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel ButtonPanel = new JPanel();
        ButtonPanel.add(ProdButton);
        ButtonPanel.add(CustButton);
        ButtonPanel.add(OrdButton);

        this.getContentPane().add(ButtonPanel);
    }
}