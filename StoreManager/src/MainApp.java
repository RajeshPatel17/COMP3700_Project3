import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        StoreManager.getInstance().getMainMenuView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StoreManager.getInstance().getMainMenuView().setVisible(true);
    }
}
