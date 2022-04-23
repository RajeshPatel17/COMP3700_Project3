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
        compare old password to database and save new password in database
    }
}
