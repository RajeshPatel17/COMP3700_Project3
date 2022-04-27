import java.sql.*;
import java.sql.Date;
import java.util.*;
/* Class that connects and interacts directly with database */
public class SQLiteDataAdapter implements DataAccess {
    Connection conn = null;

    @Override
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:store.db";

            conn = DriverManager.getConnection(url);
            
            if (conn == null) {
                System.out.println("Cannot make the connection!!!");
            } else {
                System.out.println("The connection object is " + conn);
            }
            System.out.println("Connection to SQLite has been established.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean saveProduct(ProductModel product) {
        try {

            Statement stmt = conn.createStatement();

            if (loadProduct(product.productID) == null) {           // this is a new product!
                stmt.execute("INSERT INTO Product(productID, name, price, quantity) VALUES ("
                        + product.productID + ","
                        + '\'' + product.name + '\'' + ","
                        + product.price + ","
                        + product.quantity + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Product SET "
                        + "productID = " + product.productID + ","
                        + "name = " + '\'' + product.name + '\'' + ","
                        + "price = " + product.price + ","
                        + "quantity = " + product.quantity +
                        " WHERE productID = " + product.productID
                );

            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductModel loadProduct(int productID) {
        ProductModel product = null;
        try {
            
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE ProductID = " + productID);
            if (rs.next()) {
                product = new ProductModel();
                product.productID = rs.getInt(1);
                product.name = rs.getString(2);
                product.price = rs.getDouble(3);
                product.quantity = rs.getDouble(4);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean saveOrder(OrderModel order) {
        try {

            Statement stmt = conn.createStatement();

            if (loadOrder(order.orderID) == null) {           // this is a new order!
                stmt.execute("INSERT INTO Orders(OrderID, OrderDate, CustomerID, TotalCost, TotalTax) VALUES ("
                        + order.orderID + ","
                        + '\'' + order.orderDate + '\'' + ","
                        + order.customerID + ","
                        + order.totalCost + "," 
                        + order.totalTax + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Orders SET "
                        + "OrderID = " + order.orderID + ","
                        + "OrderDate = "  + '\'' + order.orderDate + '\'' + ","
                        + "CustomerID = " + order.customerID + ","
                        + "TotalCost = " + order.totalCost + ","
                        + "TotalTax = " + order.totalTax +
                        " WHERE OrderID = " + order.orderID
                );

            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public OrderModel loadOrder(int orderID) {
        OrderModel order = null;
        try {
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders WHERE OrderID = " + orderID);
            if (rs.next()) {
                order = new OrderModel();
                order.orderID = rs.getInt(1);
                order.orderDate = rs.getString(2);
                order.customerID = rs.getInt(3);
                order.totalCost = rs.getDouble(4);
                order.totalTax = rs.getDouble(5);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean saveCustomer(CustomerModel customer){
        try {

            Statement stmt = conn.createStatement();

            if (loadCustomer(customer.customerID) == null) {           // this is a new customer!
                stmt.execute("INSERT INTO Customers(CustomerID, CustomerName, DateOfBirth, Address) VALUES ("
                        + customer.customerID + ","
                        + '\'' + customer.customerName + '\'' + ","
                        + '\'' + customer.dateOfBirth + '\'' + ","
                        + '\'' + customer.address + '\'' + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Customers SET "
                        + "CustomerID = " + customer.customerID + ","
                        + "CustomerName = "  + '\'' + customer.customerName + '\'' + ","
                        + "DateOfBirth = " + '\'' + customer.dateOfBirth + '\'' + ","
                        + "Address = " + '\'' + customer.address + '\'' +
                        " WHERE CustomerID = " + customer.customerID
                );

            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public CustomerModel loadCustomer(int customerID){
        CustomerModel customer = null;
        try {
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers WHERE CustomerID = " + customerID);
            if (rs.next()) {
                customer = new CustomerModel();
                customer.customerID = rs.getInt(1);
                customer.customerName = rs.getString(2);
                customer.dateOfBirth = rs.getString(3);
                customer.address = rs.getString(4);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    @Override
    public int loginUser(String username, String password){
        try{
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT UserID, Password FROM USER WHERE UserName = " + '\'' + username + '\'');
            if(rs.next()){
                System.out.println(rs.getInt(1));
                if(password.equals(rs.getString(2))){
                    return rs.getInt(1);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public String getPassword(int userID){
        try{
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT Password FROM User WHERE UserID = " + userID);
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean setPassword(int userID, String password){
        try{
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("UPDATE User SET "
            + "Password = " + '\'' +  password + '\'' //+ ","
            + " WHERE UserID = " + userID);

            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustName(int custID, String name){
        try{
            Statement stmt = conn.createStatement();
            
            stmt.executeUpdate("UPDATE Customers SET " 
            + "CustomerName = " + '\'' + name + '\''
            + " WHERE CustomerID = " + custID);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDisplayName(int userID, String name){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE DisplayName = " + '\'' + name + '\'');
            if(rs.next()){
                throw new Exception("Display Name taken");
            }
            stmt.executeUpdate("UPDATE User SET " 
            + "DisplayName = " + '\'' + name + '\''
            + " WHERE UserID = " + userID);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDateOfBirth(int custID, String dateOfBirth){
        try{
            Statement stmt = conn.createStatement();
            
            stmt.executeUpdate("UPDATE Customers SET " 
            + "DateOfBirth = " + '\'' + dateOfBirth + '\''
            + " WHERE CustomerID = " + custID);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAddress(int custID, String address){
        try{
            Statement stmt = conn.createStatement();
            
            stmt.executeUpdate("UPDATE Customers SET " 
            + "Address = " + '\'' + address + '\''
            + " WHERE CustomerID = " + custID);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int getCustIDOfOrder(int orderID){
        try{
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT CustomerID FROM Orders WHERE OrderID = " + orderID);
            if(rs.next()){
                return rs.getInt(1);
            }
            return -1;
        } catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public List<ProductModel> loadProductsInOrder(int orderID){
        List<ProductModel> products = new ArrayList<ProductModel>();
        try{
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM OrderLine WHERE OrderID = " + orderID);
            while(rs.next()){
                ProductModel p = new ProductModel();
                p.productID = rs.getInt(3);
                p.price = rs.getDouble(5);
                p.quantity = rs.getDouble(4);
                products.add(p);
            }
            for(ProductModel p : products){
                rs = stmt.executeQuery("SELECT Name FROM Product WHERE ProductID = " + p.productID);
                p.name = rs.getString(1);
            }

            return products;
        } catch (Exception e){
            e.printStackTrace();
            return null;	
        }
    }

    public boolean saveOrder(int custID, List<ProductModel> products){
        double totalCost = 0.00;
        try{

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT OrderID FROM Orders ORDER BY OrderID ASC");

            int orderID = 0;
            while(rs.next() && rs.getInt(1) == ++orderID);

            for(ProductModel product: products){
                totalCost += product.quantity * product.price;
                stmt.execute("INSERT INTO OrderLine (OrderID, ProductID, Quantity, Cost) VALUES ("
                + orderID + ","
                + product.productID + ","
                + product.quantity + ","
                + product.price + ")");
            }

            stmt.execute("INSERT INTO Orders (OrderID, OrderDate, CustomerID, TotalCost, TotalTax) VALUES ("
            + orderID + ","
            + '\'' + (new java.sql.Date(System.currentTimeMillis()).toString()) + '\'' /*+ /*String.valueOf(new java.sql.Date(System.currentTimeMillis()))*/ + ","
            + custID + ","
            + totalCost + ","
            + totalCost*.09 + ")");

            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false; 
        }
    }

    public boolean updateOrder(int orderID, List<ProductModel> products){
        try{
            int custID = getCustIDOfOrder(orderID);
            cancelOrder(orderID);
            saveOrder(custID, products);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }


        //compare products to what is in table and update table to reflect list
    }

    public boolean cancelOrder(int orderID){
        try{
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM OrderLine WHERE OrderID = " + orderID);
            stmt.execute("DELETE FROM Orders WHERE OrderID = " + orderID);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<OrderModel> getOrderHistory(int custID){
        List<OrderModel> orderHistory = new ArrayList<OrderModel>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders WHERE CustomerID = " + custID);

            while(rs.next()){
                OrderModel om = new OrderModel();
                om.orderID = rs.getInt(1);
                om.orderDate = rs.getString(2);
                om.customerID = rs.getInt(3);
                om.totalCost = rs.getDouble(4);
                om.totalTax = rs.getDouble(5);
                orderHistory.add(om);
            }
            return orderHistory;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ProductModel> getLikeProducts(String keyword){
        List<ProductModel> products = new ArrayList<ProductModel>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE Name LIKE " + "'%" + keyword + "%'");
            while(rs.next()){
                ProductModel p = new ProductModel();
                p.productID = rs.getInt(1);
                p.name = rs.getString(2);
                p.price = rs.getDouble(3);
                p.quantity = rs.getDouble(4);
                products.add(p);
            }
            return products;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean saveNewCustomer(String userName, String password, String dispName, String custName, String dateOfBirth, String address){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM User");
            rs.next();
            int userID = rs.getInt("COUNT(*)")+1;

            rs = stmt.executeQuery("SELECT COUNT(*) FROM User WHERE UserName = " + '\'' + userName + '\'');
            rs.next();
            boolean taken = rs.getInt("COUNT(*)") > 0;
            if(taken){
                throw new Exception("User Name taken");
            }
            stmt.execute("INSERT INTO User (UserID, UserName, Password, DisplayName) VALUES (" 
            + userID + "," 
            + '\'' + userName + '\'' + ","
            + '\'' + password + '\'' + ","
            + '\'' + dispName + '\'' + ")");

            stmt.execute("INSERT INTO Customers (CustomerID, CustomerName, DateOfBirth, Address) VALUES ("
            + userID + "," 
            + '\'' + custName + '\'' + "," 
            + '\'' + dateOfBirth + '\'' + ","
            + '\'' + address + '\'' + ")");
            return true;

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginMana(String username, String password){
        try{
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT IsManager FROM User WHERE UserName = " + '\'' + username + '\'' + " AND Password = " + '\'' + password + '\'');
            if(rs.next()){
                return rs.getBoolean(1);
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
