import java.sql.*;
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

}
