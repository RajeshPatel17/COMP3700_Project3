 public class RequestModel {

    static final public int EXIT_REQUEST = 0;
    static final public int LOAD_CUSTOMER_REQUEST = 7;
    static final public int SAVE_CUSTOMER_REQUEST = 77;
    static final public int LOAD_ORDER_REQUEST = 8;
    static final public int SAVE_ORDER_REQUEST = 88;
    static final public int LOAD_PRODUCT_REQUEST = 9;
    static final public int SAVE_PRODUCT_REQUEST = 99;
    static final public int LOGIN_USER = 1;
    static final public int GET_PASSWORD = 11;
    static final public int SET_PASSWORD = 111;
    static final public int UPDATE_CUSTOMER_NAME = 2;
    static final public int UPDATE_DISPLAY_NAME = 22;
    static final public int UPDATE_DATE_OF_BIRTH = 222;
    static final public int UPDATE_ADDRESS = 2222;
    static final public int GET_CUST_ID_OF_ORDER = 3;
    static final public int LOAD_PRODUCTS_IN_ORDER = 33;
    static final public int SAVE_ORDER = 4;
    static final public int UPDATE_ORDER = 44;
    static final public int CANCEL_ORDER = 444;
    static final public int GET_ORDER_HISTORY = 5;
    static final public int GET_LIKE_PRODUCTS = 55;
    static final public int SAVE_NEW_CUSTOMER = 22222;
    static final public int LOGIN_MANAGER = 10;

    public int code;
    public String body;
    public String body1;
}
