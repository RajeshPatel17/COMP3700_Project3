/* Interface for interactions with SQL database */
public interface DataAccess {
    void connect();

    boolean saveProduct(ProductModel product);

    ProductModel loadProduct(int productID);

    boolean saveOrder(OrderModel order);

    OrderModel loadOrder(int orderID);
    
    boolean saveCustomer(CustomerModel customer);

    CustomerModel loadCustomer(int customerID);

    int loginCustomer(String userName, byte[] encryptedPassword, String secretKey, byte[] initializationVector);


}
