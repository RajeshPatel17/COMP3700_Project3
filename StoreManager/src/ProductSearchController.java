import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ProductSearchController implements ActionListener{

    ProductSearchView thisView;
    DataAccess thisDAO;
    List<ProductModel> results;

    public ProductSearchController(ProductSearchView view, DataAccess access) {
        thisView = view;
        thisDAO = access;
        results = new ArrayList<ProductModel>();
        thisView.backButton.addActionListener(this);
        thisView.searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.searchButton){
            search();
        }
        if(event.getSource() == thisView.backButton){
            back();
        }
    }

    private void search(){
        String keyword = thisView.keywordText.getText();
        results = thisDAO.getLikeProducts(keyword);
        AddProductView prodView = new AddProductView();
        AddProductController prodController = new AddProductController(prodView, thisDAO, results);
        

        //search all products in database with name like keyword and return set
    }

    private void back(){
        Customer.getInstance().getProductSearchView().setVisible(false);
        Customer.getInstance().getCustomerMenuView().setVisible(true);
    }
    
}
