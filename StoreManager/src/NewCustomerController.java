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
        save to user table and customer table
    }
}
