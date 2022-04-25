import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* Controls actions committed in main view */
public class MainMenuController implements ActionListener{
    
    MainMenuView thisMainView;

    public MainMenuController(MainMenuView view){
        thisMainView = view;
        thisMainView.ManaButton.addActionListener(this);
        thisMainView.CustButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == thisMainView.CustButton){
            Customer.getInstance().getCustomerLoginView().setVisible(true);
            MainApp.getInstance().getMainMenuView().setVisible(false);
        }
        if(event.getSource() == thisMainView.ManaButton){
            StoreManager.getInstance().getManaLoginView().setVisible(true);
            MainApp.getInstance().getMainMenuView().setVisible(false);
        }
    }

}
