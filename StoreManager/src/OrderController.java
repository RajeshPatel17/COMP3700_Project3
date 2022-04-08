import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* Controls interactions within OrderView */
public class OrderController implements ActionListener {
    
    OrderView thisView;
    DataAccess thisDAO;

    public OrderController(OrderView view, DataAccess dataAccess) {
        thisView = view;
        thisDAO = dataAccess;
        thisView.retrieveOrderButton.addActionListener(this);
        thisView.uploadOrderButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.retrieveOrderButton){
            loadOrderandDisplay();
        }
        if(event.getSource() == thisView.uploadOrderButton){
            saveOrder();
        }

    }

    private void saveOrder(){

        OrderModel orderModel = new OrderModel();
        try{
            orderModel.orderID = Integer.parseInt(thisView.orderIDText.getText());
            orderModel.orderDate = thisView.orderDateText.getText();
            orderModel.customerID = Integer.parseInt(thisView.CustomerText.getText());
            orderModel.totalCost = Double.parseDouble(thisView.TotalCostText.getText());
            orderModel.totalTax = Double.parseDouble(thisView.TotalTaxText.getText());

            boolean saved = thisDAO.saveOrder(orderModel);

            if(saved) {
                JOptionPane.showMessageDialog(null, "Order Saved Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Order Not Saved!");
                throw new Exception("Order Not Saved");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Inputs");
            e.printStackTrace();
        }

    }

    private void loadOrderandDisplay(){
        try{
            OrderModel orderModel = thisDAO.loadOrder(Integer.parseInt(thisView.orderIDText.getText()));
            if(orderModel==null){
                throw new IllegalStateException();
            }
            thisView.orderDateText.setText(orderModel.orderDate);
            thisView.CustomerText.setText(Integer.toString(orderModel.customerID));
            thisView.TotalCostText.setText(Double.toString(orderModel.totalCost));
            thisView.TotalTaxText.setText(Double.toString(orderModel.totalTax));
        
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Invalid OrderID");
            e.printStackTrace();

        }
    }
}
