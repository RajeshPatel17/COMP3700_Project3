// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;


// Server class
public class DataServer
{
    public static void main(String[] args) throws IOException
    {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);

        // running infinite loop for getting
        // client request

        System.out.println("Starting server program!!!");

        int nClients = 0;

        while (true)
        {
            Socket s = null;

            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();

                nClients++;
                System.out.println("A new client is connected : " + s + " client number: " + nClients);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}


// ClientHandler class
class ClientHandler extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    Gson gson = new Gson();
    DataAccess dao = new SQLiteDataAdapter();

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        dao.connect();
    }

    @Override
    public void run()
    {
        String received;
        boolean exit = false;
        while (!exit) {
            try {
                // receive the answer from client
                received = dis.readUTF();

                System.out.println("Message from client " + received);

                RequestModel req = gson.fromJson(received, RequestModel.class);
                ResponseModel res = new ResponseModel();
                
                switch(req.code){

                    case RequestModel.EXIT_REQUEST:
                        System.out.println("Client " + this.s + " sends exit...");
                        System.out.println("Closing this connection.");
                        this.s.close();
                        System.out.println("Connection closed");
                        exit = true;
                        break;
                    
                    case RequestModel.LOAD_CUSTOMER_REQUEST:
                        int customerID = Integer.parseInt(req.body);
                        System.out.println("The client asks for a customer with ID of " + customerID);
                        CustomerModel customerModelLoad = dao.loadCustomer(customerID);
                        if(customerModelLoad != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(customerModelLoad);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;
                    
                    case RequestModel.SAVE_CUSTOMER_REQUEST:
                        CustomerModel customerModelSave = gson.fromJson(req.body, CustomerModel.class);
                        System.out.println("The client asks to store the customer" + gson.toJson(customerModelSave));
                        dao.saveCustomer(customerModelSave);
                        CustomerModel confirmCustomer = dao.loadCustomer(customerModelSave.customerID);
                        if(confirmCustomer != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(confirmCustomer);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;
                        
                    case RequestModel.LOAD_ORDER_REQUEST:
                        int orderID = Integer.parseInt(req.body);
                        System.out.println("The client asks for an order with ID of " + orderID);
                        OrderModel orderModelLoad = dao.loadOrder(orderID);
                        if(orderModelLoad != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(orderModelLoad);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;                    
                    
                    case RequestModel.SAVE_ORDER_REQUEST:
                        OrderModel orderModelSave = gson.fromJson(req.body, OrderModel.class);
                        System.out.println("The client asks to store the order" + gson.toJson(orderModelSave));
                        dao.saveOrder(orderModelSave);
                        OrderModel orderConfirm = dao.loadOrder(orderModelSave.orderID);
                        if(orderConfirm != null) {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(orderConfirm);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;

                    case RequestModel.LOAD_PRODUCT_REQUEST:
                        int productID = Integer.parseInt(req.body);
                        System.out.println("The client asks for a product with ID of " + productID);
                        ProductModel productModelLoad = dao.loadProduct(productID);
                        if(productModelLoad != null) {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(productModelLoad);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;

                    case RequestModel.SAVE_PRODUCT_REQUEST:
                        ProductModel productModelSave = gson.fromJson(req.body, ProductModel.class);
                        System.out.println("The client asks to store the product " + gson.toJson(productModelSave));
                        dao.saveProduct(productModelSave);
                        ProductModel productConfirm = dao.loadProduct(productModelSave.productID);
                        if(productConfirm != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(productConfirm);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;

                    case RequestModel.LOGIN_USER:
                        String[] creds = gson.fromJson(req.body, String[].class);
                        System.out.println("The client asks to log in user: " + creds[0]);
                        int custID = dao.loginUser(creds[0], creds[1]);
                        if(custID!=-1) {
                            res.code = ResponseModel.OK;
                            res.body = String.valueOf(custID);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;

                    case RequestModel.GET_PASSWORD:
                        System.out.println("The client asks for the password of user " + Integer.valueOf(req.body) + ".");
                        String password = dao.getPassword(Integer.valueOf(req.body));
                        if(password != null){
                            res.code = ResponseModel.OK;
                            res.body = password;
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;
                    
                    case RequestModel.SET_PASSWORD:
                        String[] setPass = gson.fromJson(req.body, String[].class);
                        System.out.println("The client asks to set the password for user " + setPass[0]);
                        if(dao.setPassword(Integer.valueOf(setPass[0]), setPass[1])){
                            res.code = ResponseModel.OK;
                            res.body = "";
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;

                    case RequestModel.UPDATE_CUSTOMER_NAME:
                        String[] updateCustName = gson.fromJson(req.body, String[].class);
                        System.out.println("The client asks to update the customer name of customer " + updateCustName[0] + ".");
                        if(dao.updateCustName(Integer.valueOf(updateCustName[0]), updateCustName[1])){
                            res.code = ResponseModel.OK;
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                        }
                        res.body = "";
                        break;
                    
                    case RequestModel.UPDATE_DISPLAY_NAME:
                        String[] updateDispName = gson.fromJson(req.body, String[].class);
                        System.out.println("The client asks to update the display name of user " + updateDispName[0] + ".");
                        if(dao.updateDisplayName(Integer.valueOf(updateDispName[0]), updateDispName[1])){
                            res.code = ResponseModel.OK;
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                        }
                        res.body = "";
                        break;             
                        
                    
                    case RequestModel.UPDATE_DATE_OF_BIRTH:
                        String[] updateDOB = gson.fromJson(req.body, String[].class);
                        System.out.println("The client asks to update the DOB of user " + updateDOB[0] + ".");
                        if(dao.updateDateOfBirth(Integer.valueOf(updateDOB[0]), updateDOB[1])){
                            res.code = ResponseModel.OK;
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                        }
                        res.body = "";
                        break; 

                    case RequestModel.UPDATE_ADDRESS:
                        String[] updateAddr = gson.fromJson(req.body, String[].class);
                        System.out.println("The client asks to update the Address of user " + updateAddr[0] + ".");
                        if(dao.updateAddress(Integer.valueOf(updateAddr[0]), updateAddr[1])){
                            res.code = ResponseModel.OK;
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                        }
                        res.body = "";
                        break;
                    
                    case RequestModel.GET_CUST_ID_OF_ORDER:
                        int getCustOrderID = Integer.parseInt(req.body);
                        System.out.println("The client asks for the customer id of order" + getCustOrderID);
                        int getCustCustID = dao.getCustIDOfOrder(getCustOrderID);
                        if(getCustCustID == -1){
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        } else {
                            res.code = ResponseModel.OK;
                            res.body = String.valueOf(getCustCustID);
                        }
                        break;

                    case RequestModel.LOAD_PRODUCTS_IN_ORDER:
                        int lpiOrderID = Integer.valueOf(req.body);
                        System.out.println("The client asks for the products in order" + lpiOrderID);
                        List<ProductModel> products = dao.loadProductsInOrder(lpiOrderID);
                        if(products == null){
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        } else {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(products);
                        }
                        break;

                    case RequestModel.SAVE_ORDER:
                        int soCustID = gson.fromJson(req.body, Integer.class);
                        List<ProductModel> soProducts = Arrays.asList(gson.fromJson(req.body1, ProductModel[].class));
                        System.out.println("The client asks to save an order with: " + soProducts);
                        if(!dao.saveOrder(soCustID, soProducts)){
                            res.code = ResponseModel.DATA_NOT_FOUND;
                        } else {
                            res.code = ResponseModel.OK;
                        }
                        res.body = "";
                        break;

                    case RequestModel.UPDATE_ORDER:
                        int uoOrderID = gson.fromJson(req.body, Integer.class);
                        List<ProductModel> uoProducts = Arrays.asList(gson.fromJson(req.body1, ProductModel[].class));
                        System.out.println("The client asks to update order: " + uoOrderID);
                        if(!dao.updateOrder(uoOrderID, uoProducts)){
                            res.code = ResponseModel.DATA_NOT_FOUND;
                        } else {
                            res.code = ResponseModel.OK;
                        }
                        res.body = "";
                        break;
                    
                    case RequestModel.CANCEL_ORDER:
                        int coOrderID = Integer.valueOf(req.body);
                        System.out.println("The client asks to cancel order: " + coOrderID);
                        if(!dao.cancelOrder(coOrderID)){
                            res.code = ResponseModel.DATA_NOT_FOUND;
                        } else {
                            res.code = ResponseModel.OK;
                        }
                        res.body = "";
                        break;
                    
                    case RequestModel.GET_ORDER_HISTORY:
                        int ohCustID = Integer.valueOf(req.body);
                        System.out.println("The client asks to get the order history for user: " + ohCustID);
                        List<OrderModel> orderHistory = dao.getOrderHistory(ohCustID);
                        if(orderHistory==null){
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        } else {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(orderHistory);
                        }
                        break;
                    
                    case RequestModel.GET_LIKE_PRODUCTS:
                        String keyword = req.body;
                        System.out.println("The client asks to get products like " + keyword);
                        List<ProductModel> likeProducts = dao.getLikeProducts(keyword);
                        if(likeProducts==null){
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        } else {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(likeProducts);
                        }
                        break;
                    
                    case RequestModel.SAVE_NEW_CUSTOMER:
                        System.out.println("The client asks to save a new customer");
                        String[] newCust = gson.fromJson(req.body, String[].class);
                        if(!dao.saveNewCustomer(newCust[0], newCust[1], newCust[2], newCust[3], newCust[4], newCust[5])){
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        } else {
                            res.code = ResponseModel.OK;
                            res.body = "";
                        }
                        break;
                    
                    case RequestModel.LOGIN_MANAGER:
                        System.out.println("The client asks login a manager");
                        String[] manaCreds = gson.fromJson(req.body, String[].class);
                        if(!dao.loginMana(manaCreds[0], manaCreds[1])){
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        } else {
                            res.code = ResponseModel.OK;
                            res.body = "";
                        }
                        break;
                    default:
                        res.code = ResponseModel.UNKNOWN_REQUEST;
                        res.body = "";
                }

                String json = gson.toJson(res);
                System.out.println("JSON object of ResponseModel: " + json);

                dos.writeUTF(json);
                dos.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}