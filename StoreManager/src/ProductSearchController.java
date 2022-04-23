import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductSearchController implements ActionListener{

    ProductSearchView thisView;
    DataAccess thisDAO;

    public ProductSearchController(ProductSearchView view, DataAccess access) {
        thisView = view;
        thisDAO = access;
        thisView.backButton.addActionListener(this);
        thisView.searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == thisView.searchButton){
            search all products in database with name like keyword and return set
        }
        if(event.getSource() == thisView.backButton){
            Customer.getInstance().getProductSearchView().setVisible(false);
            Customer.getInstance().getCustomerMenuView().setVisible(true);
        }
    }
    
}
