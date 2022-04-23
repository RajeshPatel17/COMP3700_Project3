import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderMenuController implements ActionListener{
    
    OrderMenuView thisView;
    DataAccess thisDAO;

    public OrderMenuController(OrderMenuView view, DataAccess dao){
        thisView = view;
        thisDAO = dao;
        thisView.backButton.addActionListener(this);
        thisView.MCCOrderButton.addActionListener(this);
        thisView.orderHistoryButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == thisView.backButton){
            back();
        }
        if(event.getSource() == thisView.MCCOrderButton){
            MCCOrder();
        }
        if(event.getSource() == thisView.orderHistoryButton){
            orderHistory();
        }
    }

    private void back(){
        Customer.getInstance().getOrderMenuView().setVisible(false);
        Customer.getInstance().getCustomerMenuView().setVisible(true);
    }
    private void MCCOrder(){
        Customer.getInstance().getOrderMenuView().setVisible(false);
        Customer.getInstance().getMCCOrderView().setVisible(true);
    }
    private void orderHistory() {
        Customer.getInstance().getOrderMenuView().setVisible(true);
        Customer.getInstance().getOrderHistoryView().setVisible(true);
    }
}
