//remove note implementation and insert load, save, update to cust, order, and product
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
                    System.out.println("The Server could not find a note with that ID!");
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
    public void saveProduct(ProductModel product){
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
                return ;
            } else {
                if(res.code == ResponseModel.DATA_NOT_FOUND){
                    System.out.println("The Server could not save the Product!");
                } else {
                    ProductModel savedProduct = gson.fromJson(res.body, ProductModel.class);
                    System.out.println("Saved the following Product!");
                    System.out.println("ProductID = " + savedProduct.productID);
                    System.out.println("Name = " + savedProduct.name);
                    System.out.println("Price = " + savedProduct.price);
                    System.out.println("Quantity = " + savedProduct.quantity);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
}
