import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddProductController implements ActionListener{
    AddProductView thisView;
    DataAccess thisDAO;
    List<ProductModel> products;
    ProductModel selectedProduct;
    int index;

    public AddProductController(AddProductView view, DataAccess dao, List<ProductModel> results){
        thisView = view;
        thisDAO = dao;
        products = results;
        index = 0;
        thisView.backButton.addActionListener(this);
        thisView.addProductButton.addActionListener(this);
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
        if(event.getSource() == thisView.addProductButton){
            addProduct();
        }
    }

    private void back(){
        thisView.setVisible(false);
        Customer.getInstance().getProductSearchView().setVisible(true);
        Customer.getInstance().getMCCOrderController().displayProduct();
    }

    private void displayProduct(int i){
        try{
            ProductModel thisProduct = products.get(i);
            thisView.productIDText.setText(String.valueOf(thisProduct.productID));
            thisView.productNameText.setText(thisProduct.name);
            thisView.costText.setText(String.valueOf(thisProduct.price));
            thisView.maxQuantityText.setText(String.valueOf(thisProduct.quantity));
            thisView.selectedQuantityText.setText("");
        }catch(Exception e){
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

    private void addProduct(){
        try{
            ProductModel thisProduct = products.get(index%products.size());
            int selectedQuantity = Integer.parseInt(thisView.selectedQuantityText.getText());
            if(selectedQuantity>thisProduct.quantity || selectedQuantity<1){
                throw new Exception("Invalid Quantity");
            }
            thisProduct.quantity = selectedQuantity;
            Customer.getInstance().getMCCOrderController().addProduct(thisProduct);
            back();
            JOptionPane.showMessageDialog(null,"Product Added");
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();
        }

    }
}
