import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class RemoveProductController implements ActionListener{
    RemoveProductView thisView;
    DataAccess thisDAO;
    List<ProductModel> products;
    ProductModel selectedProduct;
    int index;

    public RemoveProductController(RemoveProductView view, DataAccess dao, List<ProductModel> results){
        thisView = view;
        thisDAO = dao;
        products = results;
        index = 0;
        thisView.backButton.addActionListener(this);
        thisView.removeProductButton.addActionListener(this);
        thisView.nextProductButton.addActionListener(this);
        thisView.prevProductButton.addActionListener(this);
        displayProduct(index);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.backButton){
            back();
        }
        if(event.getSource() == thisView.nextProductButton){
            nextProduct();
        }
        if(event.getSource() == thisView.prevProductButton){
            prevProduct();
        }
        if(event.getSource() == thisView.removeProductButton){
            removeProduct();
        }
    }

    private void back(){
        thisView.setVisible(false);
        Customer.getInstance().getMCCOrderView().setVisible(true);
        Customer.getInstance().getMCCOrderController().displayProduct();
    }

    private void displayProduct(int i){
        try{
            ProductModel thisProduct = products.get(i);
            thisView.productIDText.setText(String.valueOf(thisProduct.productID));
            thisView.productNameText.setText(thisProduct.name);
            thisView.costText.setText(String.valueOf(thisProduct.price));
            thisView.selectedQuantityText.setText(String.valueOf(thisProduct.quantity));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void nextProduct(){
        index++;
        displayProduct(index%products.size());
    }

    private void prevProduct(){
        index--;
        displayProduct(index%products.size());
    }

    private void removeProduct(){
        try{
            ProductModel thisProduct = products.get(index%products.size());
            Customer.getInstance().getMCCOrderController().removeProduct(thisProduct);
            back();
            JOptionPane.showMessageDialog(null,"Product Removed");
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();
        }

    }
}
