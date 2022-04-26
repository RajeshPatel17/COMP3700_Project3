import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustEditController implements ActionListener{

    CustEditView thisView;
    DataAccess thisDAO;

    public CustEditController(CustEditView view, DataAccess dao){
        thisView = view;
        thisDAO = dao;
        thisView.backButton.addActionListener(this);
        thisView.clearButton.addActionListener(this);
        thisView.saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.backButton){
            back();
        }
        if(event.getSource() == thisView.clearButton){
            clear();
        }
        if(event.getSource() == thisView.saveButton){
            saveEdits();
        }
    }

    private void back(){
        Customer.getInstance().getCustomerEditView().setVisible(false);
        Customer.getInstance().getCustomerMenuView().setVisible(true);
    }

    private void clear(){
        thisView.customerNameText.setText("");
        thisView.displayNameText.setText("");
        thisView.dateOfBirthText.setText("");
        thisView.addressText.setText("");
    }

    private void saveEdits(){
        try{
            boolean success = true;
            if(!thisView.customerNameText.getText().isEmpty()){
               success = success && thisDAO.updateCustName(Customer.getInstance().getCustomerModel().customerID, thisView.customerNameText.getText());
                if(!success){
                    throw new Exception("Failed To Update Customer Name");
                }
            }
            if(!thisView.displayNameText.getText().isEmpty()){
                success = success && thisDAO.updateDisplayName(Customer.getInstance().getCustomerModel().customerID, thisView.displayNameText.getText());
                if(!success){
                    throw new Exception("Failed To Update Display Name");
                }
            }
            if(!thisView.dateOfBirthText.getText().isEmpty()){
                success = success && thisDAO.updateDateOfBirth(Customer.getInstance().getCustomerModel().customerID, thisView.dateOfBirthText.getText());
                if(!success){
                    throw new Exception("Failed To Update Date Of Birth");
                }
            }
            if(!thisView.addressText.getText().isEmpty()){
                success = success && thisDAO.updateAddress(Customer.getInstance().getCustomerModel().customerID, thisView.addressText.getText());
                throw new Exception("Failed To Update Address");
            }
            if(success){
                JOptionPane.showMessageDialog(null, "Information Updated Successfully");
                Customer.getInstance().getCustomerEditView().setVisible(false);
                Customer.getInstance().getCustomerMenuView().setVisible(true);
            }
            //save each field to database individually as customers can edit as many fields as they want at a time
            //save filled fields to database
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

    }
    
}
