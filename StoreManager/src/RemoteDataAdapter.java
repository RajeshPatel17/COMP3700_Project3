import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class RemoteDataAdapter implements DataAccess {
    Gson gson = new Gson();
    Socket s = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;

    @Override
    public void connect() {
        try {
            s = new Socket("localhost", 5056);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public ProductModel loadProduct(int productID){
        RequestModel req = new RequestModel();
        req.code = RequestModel.LOAD_PRODUCT_REQUEST;
        req.body = Integer.toString(productID);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a Product with that ID!");
                    return null;
                } else {
                    ProductModel model = gson.fromJson(res.body, ProductModel.class);
                    System.out.println("Receiving a ProductModel object");
                    System.out.println("ProductID = " + model.productID);
                    System.out.println("Name = " + model.name);
                    System.out.println("Price = " + model.price);
                    System.out.println("Quantity = " + model.quantity);
                    return model; // found this product and return!!!
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveProduct(ProductModel product){
        RequestModel req = new RequestModel();
        req.code = RequestModel.SAVE_PRODUCT_REQUEST;
        req.body = gson.toJson(product);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if(res.code == ResponseModel.UNKNOWN_REQUEST){
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if(res.code == ResponseModel.DATA_NOT_FOUND){
                    System.out.println("The Server could not save the Product!");
                    return false;
                } else {
                    ProductModel savedProduct = gson.fromJson(res.body, ProductModel.class);
                    System.out.println("Saved the following Product!");
                    System.out.println("ProductID = " + savedProduct.productID);
                    System.out.println("Name = " + savedProduct.name);
                    System.out.println("Price = " + savedProduct.price);
                    System.out.println("Quantity = " + savedProduct.quantity);
                    return true;
                }
            }
        } catch (Exception ex) {
            
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveCustomer(CustomerModel customer) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.SAVE_CUSTOMER_REQUEST;
        req.body = gson.toJson(customer);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);
                
            if (res.code == ResponseModel.UNKNOWN_REQUEST){
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if(res.code == ResponseModel.DATA_NOT_FOUND){
                    System.out.println("The Server could not save the Customer!");
                    return false;
                } else {
                    CustomerModel customerModel = gson.fromJson(res.body, CustomerModel.class);
                    System.out.println("Saved the following Customer!");
                    System.out.println("CustomerID = " + customerModel.customerID);
                    System.out.println("Name = " + customerModel.customerName);                        System.out.println("Price = " + customerModel.dateOfBirth);
                    System.out.println("Quantity = " + customerModel.address);
                    return true;
                }
            }
        } catch (Exception ex) {                
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public CustomerModel loadCustomer(int customerID){
        RequestModel req = new RequestModel();
        req.code = RequestModel.LOAD_CUSTOMER_REQUEST;
        req.body = Integer.toString(customerID);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a Customer with that ID!");
                    return null;
                } else {
                    CustomerModel customerModel = gson.fromJson(res.body, CustomerModel.class);
                    System.out.println("Receiving a CustomerModel object");
                    System.out.println("CustomerID = " + customerModel.customerID);
                    System.out.println("Name = " + customerModel.customerName);                        System.out.println("Price = " + customerModel.dateOfBirth);
                    System.out.println("Quantity = " + customerModel.address);
                    return customerModel; // found this customer and return!!!
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;    
    }

    @Override
    public boolean saveOrder(OrderModel order){
        RequestModel req = new RequestModel();
        req.code = RequestModel.SAVE_ORDER_REQUEST;
        req.body = gson.toJson(order);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);
                
            if (res.code == ResponseModel.UNKNOWN_REQUEST){
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if(res.code == ResponseModel.DATA_NOT_FOUND){
                    System.out.println("The Server could not save the Customer!");
                    return false;
                } else {
                    OrderModel orderModel = gson.fromJson(res.body, OrderModel.class);
                    System.out.println("Saved the following Order!");
                    System.out.println("OrderID = " + orderModel.orderID);
                    System.out.println("Date = " + orderModel.orderDate);
                    System.out.println("CustomerID = " + orderModel.customerID);
                    System.out.println("Total Cost = " + orderModel.totalCost);
                    System.out.println("Total Tax = " + orderModel.totalTax);
                    return true;
                }
            }
        } catch (Exception ex) {                
            ex.printStackTrace();
            return false;
        }    
    }

    @Override
    public OrderModel loadOrder(int orderID){
        RequestModel req = new RequestModel();
        req.code = RequestModel.LOAD_ORDER_REQUEST;
        req.body = Integer.toString(orderID);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find an Order with that ID!");
                    return null;
                } else {
                    OrderModel orderModel = gson.fromJson(res.body, OrderModel.class);
                    System.out.println("OrderID = " + orderModel.orderID);
                    System.out.println("Date = " + orderModel.orderDate);
                    System.out.println("CustomerID = " + orderModel.customerID);
                    System.out.println("Total Cost = " + orderModel.totalCost);
                    System.out.println("Total Tax = " + orderModel.totalTax);
                    return orderModel;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;           
    }

}
