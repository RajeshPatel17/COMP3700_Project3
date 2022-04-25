/* Product object */
public class ProductModel {
    public int productID;
    public String name;
    public double price;
    public double quantity;

    public String toString(){
        return "Product ID: " + productID + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity;
    }
}
