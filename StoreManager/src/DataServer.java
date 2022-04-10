// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

import com.google.gson.Gson;


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