//remove note views and controllers and add product, order, and cust views and controllers
public class StoreManager {

    private static StoreManager instance = null;

    private RemoteDataAdapter dao;

    private MainMenuView mainMenuView = null;
    private OrderView orderView = null;
    private CustomerView customerView = null;
    private ProductView productView = null;

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    public ProductView getProductView() {
        return productView;
    }

    public OrderView getOrderView() {
        return orderView;
    }

    public CustomerView getCustomerView() {
        return customerView;
    }

    private MainMenuController mainMenuController = null;
    private OrderController orderController = null;
    private CustomerController customerController = null;
    private ProductController productController = null;

    public static StoreManager getInstance() {
        if (instance == null)
            instance = new StoreManager("SQLite");
        return instance;
    }

    public RemoteDataAdapter getDataAccess() {
        return dao;
    }

    private StoreManager(String db) {
        // do some initialization here!!!
        dao = new RemoteDataAdapter();
        dao.connect();
        mainMenuView = new MainMenuView();
        mainMenuController = new MainMenuController(mainMenuView);
        orderView = new OrderView();
        orderController = new OrderController(orderView, dao);
        customerView = new CustomerView();
        customerController = new CustomerController(customerView, dao);
        productView = new ProductView();
        productController = new ProductController(productView, dao);
    }
}
