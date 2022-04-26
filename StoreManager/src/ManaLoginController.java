import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManaLoginController implements ActionListener{
    
    ManaLoginView thisView;
    DataAccess thisDAO;

    public ManaLoginController(ManaLoginView view, DataAccess access){
        thisView = view;
        thisDAO = access;
        thisView.backButton.addActionListener(this);
        thisView.loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.backButton){
            back();
        }
        if(event.getSource() == thisView.loginButton){
            manaLogin();
        }
    }

    private void back(){
        StoreManager.getInstance().getManaLoginView().setVisible(false);
        MainApp.getInstance().getMainMenuView().setVisible(true);
    }

    private void manaLogin(){
        try{
            String userName = thisView.manaUserNameText.getText();
            String password = new String(thisView.manaPassText.getPassword());
            boolean manager = thisDAO.loginMana(userName, password);
            if(!manager) {
                throw new Exception("Login failed");
            } else {
                StoreManager.getInstance().getManaLoginView().setVisible(false);
                StoreManager.getInstance().getProductView().setVisible(true);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


        //log manager into system similar to customer and switch to product view
    }
}
