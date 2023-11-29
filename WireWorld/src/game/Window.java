package game;

import javax.swing.JFrame;

public class Window extends JFrame{
    private MainMenu mainMenu;
    private SettingsMenu settingsMenu;
    private GameUI gameUI;

    public Window(Options options, GameFieldMatrix matrix) {
        mainMenu = new MainMenu(this, matrix);
        settingsMenu = new SettingsMenu(this, options, matrix);
        gameUI = new GameUI(this, matrix);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("WireWorld");

        add(mainMenu.getMainPanel());
    }
    
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public SettingsMenu getSettingsMenu() {
        return settingsMenu;
    }

    public GameUI getGameUI() {
        return gameUI;
    }
}
