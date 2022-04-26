import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderHistoryController implements ActionListener{

    OrderHistoryView thisView;
    DataAccess thisDAO;

    public OrderHistoryController(OrderHistoryView view, DataAccess dao){
        thisView = view;
        thisDAO = dao;
        thisView.backButton.addActionListener(this);
        getOrderHistory();
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.backButton){
            back();
        }
    }

    private void getOrderHistory(){
        String orderHist = "";
        for(OrderModel order: thisDAO.getOrderHistory(Customer.getInstance().getCustomerModel().customerID)){
            orderHist += "Order ID: " + order.orderID + ", Order Date: " + order.orderDate + ", Total Cost: " + order.totalCost;
            for(ProductModel product: thisDAO.loadProductsInOrder(order.orderID)){
                orderHist += "\t" + product.toString();
            }
        }
        thisView.orderHistoryArea.setText(orderHist);
        //gets order history of user
    }

    private void back(){
        Customer.getInstance().getOrderHistoryView().setVisible(false);
        Customer.getInstance().getOrderMenuView().setVisible(true);
    }
    
}
