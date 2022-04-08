import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* Controls actions committed in main view */
public class MainMenuController implements ActionListener{
    
    MainMenuView thisMainView;

    public MainMenuController(MainMenuView view){
        thisMainView = view;
        thisMainView.ProdButton.addActionListener(this);
        thisMainView.CustButton.addActionListener(this);
        thisMainView.OrdButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == thisMainView.CustButton){
            StoreManager.getInstance().getCustomerView().setVisible(true);
        }
        if(event.getSource() == thisMainView.OrdButton){
            StoreManager.getInstance().getOrderView().setVisible(true);
        }
        if(event.getSource() == thisMainView.ProdButton){
            StoreManager.getInstance().getProductView().setVisible(true);
        }
    }

}
