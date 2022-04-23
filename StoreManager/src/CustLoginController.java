import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustLoginController implements ActionListener{
    
    CustLoginView thisView;
    DataAccess thisDAO;

    public CustLoginController(CustLoginView customerView, DataAccess dataAccess){
        thisView = customerView;
        thisDAO = dataAccess;
        thisView.logInButton.addActionListener(this);
        thisView.newCustomerButton.addActionListener(this);
        thisView.backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.newCustomerButton){
            newCustomer();
        }
        if(event.getSource() == thisView.logInButton){
            customerLogin();
        }
        if(event.getSource() == thisView.backButton){
            back();
        }
    }

    private void back(){
        Customer.getInstance().getCustomerLoginView().setVisible(false);
        MainApp.getInstance().getMainMenuView().setVisible(true);
    }
    
    private void newCustomer(){
        Customer.getInstance().getCustomerLoginView().setVisible(false);
        Customer.getInstance().getNewCustomerView().setVisible(true);
    }

    private void customerLogin() {
        
        try{
            String userName = thisView.CustUserNameText.getText();
            String password = new String(thisView.CustomerPassText.getPassword());

            javax.crypto.SecretKey secretKey = Encryption.createAESKey();
            byte[] initializationVector = Encryption.createInitializationVector();
            byte[] encryptedPassword = Encryption.do_AESEncryption(password, secretKey, initializationVector);
            
            int customerID = thisDAO.loginCustomer(userName, encryptedPassword, secretKey, initializationVector);

            if(customerID == -1){
                throw new Exception("User does not exist!");
            } else {
                CustomerModel customer = thisDAO.loadCustomer(customerID);
                Customer.getInstance().setCustomerModel(customer);
                Customer.getInstance().getCustomerLoginView().setVisible(false);
                Customer.getInstance().getCustomerMenuView().setVisible(true);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
