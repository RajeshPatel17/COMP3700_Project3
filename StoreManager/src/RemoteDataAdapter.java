import com.google.gson.Gson;
import java.util.*;
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
                    System.out.println("Name = " + customerModel.customerName);                        
                    System.out.println("Price = " + customerModel.dateOfBirth);
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
                    System.out.println("Name = " + customerModel.customerName);                        
                    System.out.println("Price = " + customerModel.dateOfBirth);
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

    public int loginUser(String username, String password){
        RequestModel req = new RequestModel();
        req.code = RequestModel.LOGIN_USER;
        String[] body = new String[]{username, password};
        req.body = gson.toJson(body);


        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return -1;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a User with that UserName!");
                    return -1;
                } else {
                    System.out.println("User " + Integer.valueOf(res.body) + "logged in successfully");
                    return Integer.valueOf(res.body);
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1; 
    }

    public String getPassword(int userID){
        RequestModel req = new RequestModel();
        req.code = RequestModel.GET_PASSWORD;
        req.body = String.valueOf(userID);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find an User with that ID!");
                    return null;
                } else {
                    System.out.println("Retrieved password of User " + userID);
                    return res.body;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null; 
    }

    public boolean setPassword(int userID, String password){
        RequestModel req = new RequestModel();
        req.code = RequestModel.SET_PASSWORD;
        String[] body = new String[]{String.valueOf(userID), password};
        req.body = gson.toJson(body);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not set password!");
                    return false;
                } else {
                    System.out.println("Set password of User " + userID);
                    return true;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false; 
    }

    public boolean updateCustName(int custID, String name){
        RequestModel req = new RequestModel();
        req.code = RequestModel.UPDATE_CUSTOMER_NAME;
        String[] body = new String[]{String.valueOf(custID), name};
        req.body = gson.toJson(body);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not update customer name!");
                    return false;
                } else {
                    System.out.println("Updated Customer Name of User " + custID);
                    return true;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    } 
    
    public boolean updateDisplayName(int userID, String name){
        RequestModel req = new RequestModel();
        req.code = RequestModel.UPDATE_DISPLAY_NAME;
        String[] body = new String[]{String.valueOf(userID), name};
        req.body = gson.toJson(body);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not update display name!");
                    return false;
                } else {
                    System.out.println("Updated Display Name of User " + userID);
                    return true;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateDateOfBirth(int custID, String dateOfBirth){
        RequestModel req = new RequestModel();
        req.code = RequestModel.UPDATE_DATE_OF_BIRTH;
        String[] body = new String[]{String.valueOf(custID), dateOfBirth};
        req.body = gson.toJson(body);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not update date of birth!");
                    return false;
                } else {
                    System.out.println("Updated Date Of Birth of User " + custID);
                    return true;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;    
    }

    public boolean updateAddress(int custID, String address){
        RequestModel req = new RequestModel();
        req.code = RequestModel.UPDATE_ADDRESS;
        String[] body = new String[]{String.valueOf(custID), address};
        req.body = gson.toJson(body);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not update address!");
                    return false;
                } else {
                    System.out.println("Updated Address of User " + custID);
                    return true;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false; 
    }

    public int getCustIDOfOrder(int orderID){
        RequestModel req = new RequestModel();
        req.code = RequestModel.GET_CUST_ID_OF_ORDER;
        req.body = String.valueOf(orderID);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return -1;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not get customer of the order!");
                    return -1;
                } else {
                    int custID = Integer.valueOf(res.body);
                    System.out.println("Order: "+ orderID +" placed by Customer: " + custID);
                    return custID;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public List<ProductModel> loadProductsInOrder(int orderID){
        RequestModel req = new RequestModel();
        req.code = RequestModel.LOAD_PRODUCTS_IN_ORDER;
        req.body = String.valueOf(orderID);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not get products of the order!");
                    return null;
                } else {
                    List<ProductModel> products = Arrays.asList(gson.fromJson(res.body, ProductModel[].class));
                    System.out.println("Recieved products in order");
                    return products;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean saveOrder(int custID, List<ProductModel> products){
        RequestModel req = new RequestModel();
        req.code = RequestModel.SAVE_ORDER;
        req.body = gson.toJson(custID);
        req.body1 = gson.toJson(products);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not save the order!");
                    return false;
                } else {
                    System.out.println("Saved the order!");
                    return true;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateOrder(int orderID, List<ProductModel> products){
        RequestModel req = new RequestModel();
        req.code = RequestModel.UPDATE_ORDER;
        req.body = gson.toJson(orderID);
        req.body1 = gson.toJson(products);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not update the order!");
                    return false;
                } else {
                    System.out.println("Updated the order!");
                    return true;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean cancelOrder(int orderID){
        RequestModel req = new RequestModel();
        req.code = RequestModel.CANCEL_ORDER;
        req.body = Integer.toString(orderID);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not cancel the order!");
                    return false;
                } else {
                    System.out.println("Cancelled the order!");
                    return true;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    } 
    
    public List<OrderModel> getOrderHistory(int custID){
        RequestModel req = new RequestModel();
        req.code = RequestModel.GET_ORDER_HISTORY;
        req.body = Integer.toString(custID);

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not get the order history of user" +custID);
                    return null;
                } else {
                    System.out.println("Retrieved order history for user " +custID);
                    return Arrays.asList(gson.fromJson(res.body, OrderModel[].class));
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<ProductModel> getLikeProducts(String keyword){
        RequestModel req = new RequestModel();
        req.code = RequestModel.GET_LIKE_PRODUCTS;
        req.body = keyword;

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not get products similar to " +keyword);
                    return null;
                } else {
                    System.out.println("Retrieved products similar to " +keyword);
                    return Arrays.asList(gson.fromJson(res.body, ProductModel[].class));
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean saveNewCustomer(String userName, String password, String dispName, String custName, String dateOfBirth, String address){
        RequestModel req = new RequestModel();
        req.code = RequestModel.SAVE_NEW_CUSTOMER;
        req.body = gson.toJson(new String[]{userName, password, dispName, custName, dateOfBirth, address});

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not save the new customer");
                    return false;
                } else {
                    System.out.println("Saved new customer");
                    return true;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean loginMana(String username, String password){
        RequestModel req = new RequestModel();
        req.code = RequestModel.LOGIN_MANAGER;
        req.body = gson.toJson(new String[]{username, password});

        String json = gson.toJson(req);
        try{
            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return false;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not login the manager");
                    return false;
                } else {
                    System.out.println("Logged in manager");
                    return true;
                }
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
