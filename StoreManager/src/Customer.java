public class Customer {


    private static Customer instance = null;
    private CustomerModel customerModel = null;
    
    public void setCustomerModel(CustomerModel cm){
        customerModel = cm;
    }
    private RemoteDataAdapter dao;

    private ProductView productView = null;
    private CustLoginView custLoginView = null;
    private CustEditView custEditView = null;
    private OrderView orderView = null;
    private CustomerView customerView = null;
    private NewCustomerView newCustomerView = null;
    private CustMenuView custMenuView = null;
    private CustChangePassView custChangePassView = null;
    private OrderMenuView orderMenuView = null;
    private ProductSearchView productSearchView = null;
    private OrderHistoryView orderHistoryView = null;
    private MCCOrderView mccOrderView = null;
    private AddProductView addProductView = null;
    private RemoveProductView removeProductView = null;

    public CustomerModel getCustomerModel(){
        return customerModel;
    }

    public ProductView getProductView() {
        return productView;
    }

    public CustLoginView getCustomerLoginView() {
        return custLoginView;
    }

    public CustEditView getCustomerEditView() {
        return custEditView;
    }

    public OrderView getOrderView() {
        return orderView;
    }

    public CustomerView getCustomerView() {
        return customerView;
    }

    public NewCustomerView getNewCustomerView() {
        return newCustomerView;
    }

    public CustMenuView getCustomerMenuView() {
        return custMenuView;
    }

    public CustChangePassView getCustChangePassView() {
        return custChangePassView;
    }

    public OrderMenuView getOrderMenuView() {
        return orderMenuView;
    }

    public ProductSearchView getProductSearchView() {
        return productSearchView;
    }

    public OrderHistoryView getOrderHistoryView(){
        return orderHistoryView;
    }

    public MCCOrderView getMCCOrderView() {
        return mccOrderView;
    }

    public AddProductView getAddProductView() {
        return addProductView;
    }

    public RemoveProductView getRemoveProductView() {
        return removeProductView;
    }

    private ProductController productController = null;
    private OrderController orderController = null;
    private CustLoginController custLoginController = null;
    private CustEditController custEditController = null;
    private CustomerController customerController = null;
    private NewCustomerController newCustomerController = null;
    private CustMenuController custMenuController = null;
    private CustChangePassController custChangePassController = null;
    private OrderMenuController orderMenuController = null;
    private ProductSearchController productSearchController = null;
    private OrderHistoryController orderHistoryController = null;
    private MCCOrderController mccOrderController = null;
    private AddProductController addProductController = null;
    private RemoveProductController removeProductController = null;

    public ProductSearchController getProductSearchController(){return productSearchController;}

    public AddProductController getAddProductController() {
        return addProductController;
    }

    public RemoveProductController getRemoveProductController() {
        return removeProductController;
    }

    public MCCOrderController getMCCOrderController() {
        return mccOrderController;
    }

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

        custEditView = new CustEditView();
        custEditController = new CustEditController(custEditView, dao);

        custLoginView = new CustLoginView();
        custLoginController = new CustLoginController(custLoginView, dao);

        newCustomerView = new NewCustomerView();
        newCustomerController = new NewCustomerController(newCustomerView, dao);

        custMenuView = new CustMenuView();
        custMenuController = new CustMenuController(custMenuView, dao);

        custChangePassView = new CustChangePassView();
        custChangePassController = new CustChangePassController(custChangePassView, dao);

        orderMenuView = new OrderMenuView();
        orderMenuController = new OrderMenuController(orderMenuView, dao);

        productSearchView = new ProductSearchView();
        productSearchController = new ProductSearchController(productSearchView, dao);
    
        orderHistoryView = new OrderHistoryView();
        orderHistoryController = new OrderHistoryController(orderHistoryView, dao);
    
        mccOrderView = new MCCOrderView();
        mccOrderController = new MCCOrderController(mccOrderView, dao);
    }
}
