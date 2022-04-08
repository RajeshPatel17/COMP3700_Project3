import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * This class responds to actions done within the Customer view 
 */
public class CustomerController implements ActionListener{
    
    CustomerView thisView;
    DataAccess thisDAO;

    public CustomerController(CustomerView view, DataAccess dataAccess){
        thisView = view;
        thisDAO = dataAccess;
        thisView.retrieveCustomerButton.addActionListener(this);
        thisView.uploadCustomerButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.retrieveCustomerButton){
            loadCustomerandDisplay();
        }
        if(event.getSource() == thisView.uploadCustomerButton){
            saveCustomer();
        }
    }
    
    private void saveCustomer(){

        CustomerModel customerModel = new CustomerModel();
        try{
            customerModel.customerID = Integer.parseInt(thisView.CustomerIDText.getText());
            customerModel.customerName = thisView.CustomerNameText.getText();
            customerModel.dateOfBirth = thisView.CustomerDOBText.getText();
            customerModel.address = thisView.CustomerAddrText.getText();

            boolean saved = thisDAO.saveCustomer(customerModel);

            if(saved){
                JOptionPane.showMessageDialog(null, "Customer Saved Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Customer Not Saved!");
                throw new Exception("Customer Not Saved");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Inputs");
            e.printStackTrace();
        }

    }

    private void loadCustomerandDisplay(){
        try{
            CustomerModel customerModel = thisDAO.loadCustomer(Integer.parseInt(thisView.CustomerIDText.getText()));

            if(customerModel == null){
                throw new IllegalStateException();
            }
            thisView.CustomerNameText.setText(customerModel.customerName);
            thisView.CustomerDOBText.setText(customerModel.dateOfBirth);
            thisView.CustomerAddrText.setText(customerModel.address);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid CustomerID");
            e.printStackTrace();
        }
    }
}
