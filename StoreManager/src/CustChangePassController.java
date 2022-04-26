import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustChangePassController implements ActionListener{
    
    CustChangePassView thisView;
    DataAccess thisDAO;

    public CustChangePassController(CustChangePassView view, DataAccess dao){
        thisView = view;
        thisDAO = dao;
        thisView.backButton.addActionListener(this);
        thisView.saveButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.backButton){
            back();
        }
        if(event.getSource() == thisView.saveButton){
            changePass();
        }
    }

    private void back(){
        Customer.getInstance().getCustChangePassView().setVisible(false);
        Customer.getInstance().getCustomerMenuView().setVisible(true);
    }

    private void changePass(){
        try{
            String storedPass = thisDAO.getPassword(Customer.getInstance().getCustomerModel().customerID);
            if(storedPass.equals(new String(thisView.oldPasswordText.getPassword()))){
                boolean success = thisDAO.setPassword(Customer.getInstance().getCustomerModel().customerID, new String(thisView.newPasswordText.getPassword()));
                if(!success){
                    throw new Exception("Password Save Failed");
                }
                JOptionPane.showMessageDialog(null,"Password Saved Successfully");
                Customer.getInstance().getCustChangePassView().dispose();
                Customer.getInstance().getCustomerLoginView().setVisible(true);
            } else {
                throw new Exception("Pasword Mismatch");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        //compare old password to database and save new password in database
    }
}
