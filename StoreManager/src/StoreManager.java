//remove note views and controllers and add product, order, and cust views and controllers
public class StoreManager {

    private static StoreManager instance = null;

    private RemoteDataAdapter dao;

    private ProductView productView = null;
    private ManaLoginView manaLoginView = null;

    public ProductView getProductView() {
        return productView;
    }
    
    public ManaLoginView getManaLoginView() {
        return manaLoginView;
    }

    private ProductController productController = null;
    private ManaLoginController manaLoginController = null;

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
        productView = new ProductView();
        productController = new ProductController(productView, dao);
        manaLoginView = new ManaLoginView();
        manaLoginController = new ManaLoginController(manaLoginView, dao);
    }
}
