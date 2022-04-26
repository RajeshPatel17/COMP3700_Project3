/* Interface for interactions with SQL database */
import java.util.*;
public interface DataAccess {
    void connect();

    boolean saveProduct(ProductModel product);

    ProductModel loadProduct(int productID);

    boolean saveOrder(OrderModel order);

    OrderModel loadOrder(int orderID);
    
    boolean saveCustomer(CustomerModel customer);

    CustomerModel loadCustomer(int customerID);

    int loginUser(String userName, String password);

    String getPassword(int userID); //custID == userID

    boolean setPassword(int userID, String password); //custID == userID

    boolean updateCustName(int custID, String name); 

    boolean updateDisplayName(int userID, String name);

    boolean updateDateOfBirth(int custID, String dateOfBirth);

    boolean updateAddress(int custID, String address);

    int getCustIDOfOrder(int orderID);

    List<ProductModel> loadProductsInOrder(int orderID);

    boolean saveOrder(int custID, List<ProductModel> products);

    boolean updateOrder(int orderID, List<ProductModel> products);

    boolean cancelOrder(int orderID);

    List<OrderModel> getOrderHistory(int custID);

    List<ProductModel> getLikeProducts(String keyword);

}
