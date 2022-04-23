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
        save each field to database individually as customers can edit as many fields as they want at a time
        save filled fields to database
    }
    
}
