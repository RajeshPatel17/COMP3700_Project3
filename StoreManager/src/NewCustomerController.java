import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewCustomerController implements ActionListener{
    
    NewCustomerView thisView;
    DataAccess thisDAO;

    public NewCustomerController(NewCustomerView view, DataAccess dataAccess){
        thisView = view;
        thisDAO = dataAccess;
        thisView.saveButton.addActionListener(this);
        thisView.backButton.addActionListener(this);
        thisView.clearButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.saveButton){
            saveNewCustomer();
        }
        if(event.getSource() == thisView.clearButton){
            clear();
        }
        if(event.getSource() == thisView.backButton){
            back();
        }
    }

    private void back(){
        Customer.getInstance().getNewCustomerView().setVisible(false);
        Customer.getInstance().getCustomerLoginView().setVisible(true);
    }

    private void clear(){
        thisView.addressText.setText("");
        thisView.dateOfBirthText.setText("");
        thisView.customerNameText.setText("");
        thisView.displayNameText.setText("");
        thisView.passwordText.setText("");
        thisView.userNameText.setText("");
    }

    private void saveNewCustomer() {
        try{
            String userName = thisView.userNameText.getText();
            String password = new String(thisView.passwordText.getPassword());
            String dispName = thisView.displayNameText.getText();
            String custName = thisView.customerNameText.getText();
            String dateOfBirth = thisView.dateOfBirthText.getText();
            String address = thisView.addressText.getText();
    
            boolean success = thisDAO.saveNewCustomer(userName, password, dispName, custName, dateOfBirth, address);
            
            if(success){
                JOptionPane.showMessageDialog(null, "Created New Customer Successfully");
                back();
            } else {
                throw new Exception("Failed to Create New Customer");
            }
    
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        //save to user table and customer table
    }
}
