import javax.swing.*;
import javax.xml.crypto.Data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustMenuController implements ActionListener{

    CustMenuView thisView;
    DataAccess thisDAO;

    public CustMenuController(CustMenuView view, DataAccess access){
        thisView = view;
        thisDAO = access;
        thisView.editInfoButton.addActionListener(this);
        thisView.changePasswordButton.addActionListener(this);
        thisView.orderButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.editInfoButton){
            editCustInfoView();
        }
        if(event.getSource() == thisView.changePasswordButton){
            changePassView();
        }
        if(event.getSource() == thisView.orderButton){
            orderMenuView();
        }
    }

    private void editCustInfoView(){
        Customer.getInstance().getCustomerMenuView().setVisible(false);
        Customer.getInstance().getCustomerEditView().setVisible(true);
    }

    private void changePassView(){
        Customer.getInstance().getCustomerMenuView().setVisible(false);
        Customer.getInstance().getCustChangePassView().setVisible(true);
    }

    private void orderMenuView(){
        Customer.getInstance().getCustomerMenuView().setVisible(false);
        Customer.getInstance().getOrderMenuView().setVisible(true);
    }
    

    
}
