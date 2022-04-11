public class RequestModel {

    static final public int EXIT_REQUEST = 0;
    static final public int LOAD_CUSTOMER_REQUEST = 7;
    static final public int SAVE_CUSTOMER_REQUEST = 77;
    static final public int LOAD_ORDER_REQUEST = 8;
    static final public int SAVE_ORDER_REQUEST = 88;
    static final public int LOAD_PRODUCT_REQUEST = 9;
    static final public int SAVE_PRODUCT_REQUEST = 99;


    public int code;
    public String body;
}
