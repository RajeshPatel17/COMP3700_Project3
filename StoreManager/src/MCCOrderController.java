import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MCCOrderController implements ActionListener{

    MCCOrderView thisView;
    DataAccess thisDAO;
    private List<ProductModel> products;

    MCCOrderController(MCCOrderView view, DataAccess access){
        thisView = view;
        thisDAO = access;
        products = new ArrayList<ProductModel>();
        thisView.backButton.addActionListener(this);
        thisView.orderIDText.addActionListener(this);
        thisView.loadButton.addActionListener(this);
        thisView.saveButton.addActionListener(this);
        thisView.cancelButton.addActionListener(this);
        thisView.addProductButton.addActionListener(this);
        thisView.removeProductButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.backButton){
            back();
        }
        if(event.getSource() == thisView.loadButton || event.getSource() == thisView.orderIDText){
            loadOrder();
        }
        if(event.getSource() == thisView.saveButton){
            saveOrder();
        }
        if(event.getSource() == thisView.cancelButton){
            cancelOrder();
        }
        if(event.getSource() == thisView.addProductButton){
            addProduct();
        }
        if(event.getSource() == thisView.removeProductButton){
            removeProduct();
        }
    }
    


    private void back(){
        Customer.getInstance().getMCCOrderView().setVisible(false);
        Customer.getInstance().getOrderMenuView().setVisible(true);
    }


    private void loadOrder(){
        try{
            if((!thisView.orderIDText.getText().isEmpty()) && thisDAO.getCustIDOfOrder(Integer.valueOf(thisView.orderIDText.getText())) == Customer.getInstance().getCustomerModel().customerID){
                products = thisDAO.loadProductsInOrder(Integer.parseInt(thisView.orderIDText.getText()));
                if(products == null){
                    throw new Exception("Order not found");
                }
                String productText = "";
                for(ProductModel product : products){
                    productText += product.toString();
                }
                thisView.productArea.setText(productText);
            } else {
                throw new Exception("Order Information Denied");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();
        }

        //take order id and load all orderline objects w orderid into product area field, ensure order is placed by customer
    }

    private void saveOrder(){
        boolean success = true;
        try{
            if(thisView.orderIDText.getText().isEmpty()){
                success = success && thisDAO.saveOrder(Customer.getInstance().getCustomerModel().customerID, products);
                if(!success){
                    throw new Exception("Failed To Save Order");
                }
            } else {
                if(thisDAO.getCustIDOfOrder(Integer.valueOf(thisView.orderIDText.getText())) == Customer.getInstance().getCustomerModel().customerID){
                    success = success && thisDAO.updateOrder(Integer.valueOf(thisView.orderIDText.getText()), products);
                    if(!success){
                        throw new Exception("Failed To Update Order");
                    }
                } else {
                    throw new Exception("Invalid Order ID");
                }
            }
            if(success){
                JOptionPane.showMessageDialog(null, "Order Saved Successfully");
                thisView.orderIDText.setText("");
                thisView.productArea.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        //if order id is filled, then replace that order if placed by customer or placed new order
    }

    private void cancelOrder(){
        try{
            boolean success = true;
            if(thisView.orderIDText.getText().isEmpty()){
                success = false;
                throw new Exception("Invalid order ID");
            } else {
                if(thisDAO.getCustIDOfOrder(Integer.valueOf(thisView.orderIDText.getText())) == Customer.getInstance().getCustomerModel().customerID){
                    success = success && thisDAO.cancelOrder(Integer.valueOf(thisView.orderIDText.getText()));
                    if(!success){
                        throw new Exception("Order Not Found");
                    }
                } else {
                    success = false;
                    throw new Exception("Action Denied");
                }
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();
        }

        //delete order of order id if placed by customer
    }

    private void displayProduct(){
        String productText = "";
        for(ProductModel product: products){
            productText += product.toString() + "\n";
        }
        thisView.productArea.setText(productText);
    }

    private void addProduct(){
        Customer.getInstance().getProductSearchView().setVisible(true);
        while(Customer.getInstance().getProductSearchView().isShowing());
        //Customer.getInstance().getAddProductView().setVisible(true);
        //while(Customer.getInstance().getAddProductView().isShowing());

        displayProduct();
    }

    public void addProduct(ProductModel product){
        products.add(product);
    }

    private void removeProduct(){
        RemoveProductView view = new RemoveProductView();
        RemoveProductController controller = new RemoveProductController(view, thisDAO, products);
        while(view.isVisible());

        displayProduct();
        //create remove product view where user inputs product id and can adjust quantity or delete product of that id in their order. Must create a set method in remove product controller to pass product list.
    }

    public void removeProduct(ProductModel product){
        products.remove(product);
    }
    
}
