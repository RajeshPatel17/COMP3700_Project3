public class Customer {


    private static Customer instance = null;

    private RemoteDataAdapter dao;

    private ProductView productView = null;
    private CustLoginView custLoginView = null;
    private CustChangeView custChangeView = null;
    private OrderView orderView = null;
    private CustomerView customerView = null;


    public ProductView getProductView() {
        return productView;
    }

    public CustLoginView getCustomerLoginView() {
        return custLoginView;
    }

    public CustChangeView getCustomerPassChangeView() {
        return custChangeView;
    }

    public OrderView getOrderView() {
        return orderView;
    }

    public CustomerView getCustomerView() {
        return customerView;
    }

    private ProductController productController = null;
    private OrderController orderController = null;
    private CustLoginController custLoginController = null;
    private CustChangeController custChangeController = null;
    private CustomerController customerController = null;

    public static Customer getInstance() {
    if (instance == null)
        instance = new Customer("SQLite");
        return instance;
    }

    public RemoteDataAdapter getDataAccess() {
        return dao;
    }

    private Customer(String db) {
    // do some initialization here!!!
        dao = new RemoteDataAdapter();
        dao.connect();
        productView = new ProductView();
        productController = new ProductController(productView, dao);
        orderView = new OrderView();
        orderController = new OrderController(orderView, dao);
        customerView = new CustomerView();
        customerController = new CustomerController(customerView, dao);
        custChangeView = new CustChangeView();
        custChangeController = new custChangeController(custChangeView, dao);
        custLoginView = new CustLoginView();
        custLoginController = new custLoginController(custLoginView, dao);
    }
}
