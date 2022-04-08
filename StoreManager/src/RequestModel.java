public class RequestModel {

    static final public int EXIT_REQUEST = 0;
    static final public int CUSTOMER_REQUEST = 1;
    static final public int ORDER_REQUEST = 2;
    static final public int LOAD_PRODUCT_REQUEST = 3;
    static final public int SAVE_PRODUCT_REQUEST = 33;
    static final public int USER_REQUEST = 4;
    static final public int LOAD_NOTE_REQUEST = 5;
    static final public int SAVE_NOTE_REQUEST = 55;
    static final public int SEARCH_NOTE_REQUEST = 555;

    public int code;
    public String body;
}
