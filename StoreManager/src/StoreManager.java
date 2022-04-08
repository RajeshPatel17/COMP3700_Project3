public class StoreManager {

    private static StoreManager instance = null;

    private RemoteDataAdapter dao;

    private NoteView noteView = null;

    private SearchView searchView = null;

    private MainMenuView mainMenuView = null;

    public NoteView getNoteView() {
        return noteView;
    }

    public SearchView getSearchView() {
        return searchView;
    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    private NoteController noteController = null;

    private SearchController searchController = null;

    private MainMenuController mainMenuController = null;

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
        noteView = new NoteView();
        noteController = new NoteController(noteView, dao);
        searchView = new SearchView();
        searchController = new SearchController(searchView, dao);
    }
}
