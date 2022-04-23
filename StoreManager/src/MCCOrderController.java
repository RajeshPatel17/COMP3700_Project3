import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MCCOrderController implements ActionListener{

    MCCOrderView thisView;
    DataAccess thisDAO;

    MCCOrderController(MCCOrderView view, DataAccess access){
        thisView = view;
        thisDAO = access;
        thisView.backButton.addActionListener(this);
        thisView.loadButton.addActionListener(this);
        thisView.saveButton.addActionListener(this);
        thisView.cancelButton.addActionListener(this);
        thisView.addRemoveProductButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.backButton){
            back();
        }
        if(event.getSource() == thisView.loadButton){
            loadOrder();
        }
        if(event.getSource() == thisView.saveButton){
            saveOrder();
        }
        if(event.getSource() == thisView.cancelButton){
            cancelOrder();
        }
        if(event.getSource() == thisView.addRemoveProductButton){
            addRemoveProduct();
        }
    }

    private void back(){
        Customer.getInstance().getMCCOrderView().setVisible(false);
        Customer.getInstance().getOrderMenuView().setVisible(true);
    }

    private void loadOrder(){
        take order id and load all orderline objects w orderid into product area field, ensure order is placed by customer
    }

    private void saveOrder(){
        if order id is filled, then replace that order if placed by customer or placed new order
    }

    private void cancelOrder(){
        delete order of order id if placed by customer
    }

    private void addRemoveProduct(){
        
    }
    
}
