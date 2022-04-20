import javax.swing.*;

public class MainApp {

    private static MainApp instance = null;

    private MainMenuView mainMenuView = null;
    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    private MainMenuController mainMenuController = null;
    public static MainApp getInstance() {
        if (instance == null)
            instance = new MainApp();
        return instance;
    }
    private MainApp(){
        mainMenuView = new MainMenuView();
        mainMenuController = new MainMenuController(mainMenuView);
    }
    public static void main(String[] args) {
        MainApp.getInstance().getMainMenuView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainApp.getInstance().getMainMenuView().setVisible(true);
    }
}
