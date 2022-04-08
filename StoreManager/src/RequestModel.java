//remove note request nums and add load, save, and update for cust, order, and product
public class RequestModel {

    static final public int EXIT_REQUEST = 0;
    static final public int LOAD_CUSTOMER_REQUEST = 1;
    static final public int SAVE_CUSTOMER_REQUEST = 11;
    static final public int LOAD_ORDER_REQUEST = 2;
    static final public int SAVE_ORDER_REQUEST = 22;
    static final public int LOAD_PRODUCT_REQUEST = 3;
    static final public int SAVE_PRODUCT_REQUEST = 33;


    public int code;
    public String body;
}
